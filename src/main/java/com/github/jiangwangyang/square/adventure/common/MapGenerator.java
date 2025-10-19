package com.github.jiangwangyang.square.adventure.common;

import com.github.jiangwangyang.square.adventure.cell.plot.Plot;
import com.github.jiangwangyang.square.adventure.util.PackageLoader;
import lombok.SneakyThrows;

import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class MapGenerator {

    private static final int SEED_COUNT = 20;
    private static final double SPREAD_PROB = 0.75;

    private static final List<Class<?>> BIOMES = PackageLoader
            .load("com.github.jiangwangyang.square.adventure.cell.plot")
            .stream()
            .filter(clazz -> Plot.class.isAssignableFrom(clazz) && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()))
            .filter(clazz -> !"PlotFire".equals(clazz.getSimpleName()) && !"PlotSoulFire".equals(clazz.getSimpleName()))
            .toList();

    public static void generate(Plot[][] grid) {
        int width = grid.length;
        int height = grid[0].length;
        for (Plot[] plots : grid) {
            Arrays.fill(plots, null);
        }

        // 1.2 随机撒种子
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < SEED_COUNT; i++) {
            int x = ThreadLocalRandom.current().nextInt(width);
            int y = ThreadLocalRandom.current().nextInt(height);
            Plot biome = newPlotRandom(x, y);
            grid[x][y] = biome;
            queue.add(new int[]{x, y, biome.hashCode()});
        }

        // 1.3 BFS 扩散
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1], bioID = cur[2];
            Plot bio = grid[cx][cy];

            for (int[] d : dirs) {
                int nx = cx + d[0], ny = cy + d[1];
                if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                    continue;
                }
                if (grid[nx][ny] != null) {
                    continue;
                }
                if (ThreadLocalRandom.current().nextDouble() > SPREAD_PROB) {
                    continue;
                }
                grid[nx][ny] = newPlot(bio.getClass(), nx, ny);
                queue.add(new int[]{nx, ny, bioID});
            }
        }

        // 补洞：把剩余 null 填成周围最多群系
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y] == null) {
                    grid[x][y] = mostCommonNeighbor(grid, x, y);
                }
            }
        }
    }

    private static Plot mostCommonNeighbor(Plot[][] grid, int x, int y) {
        int width = grid.length;
        int height = grid[0].length;
        Map<Class<?>, Integer> freq = new HashMap<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                continue;
            }
            Plot p = grid[nx][ny];
            if (p == null) {
                continue;
            }
            freq.merge(p.getClass(), 1, Integer::sum);
        }
        // 找不到邻居就随机一种
        if (freq.isEmpty()) {
            return newPlotRandom(x, y);
        }
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(clazz -> newPlot(clazz, x, y))
                .orElse(newPlotRandom(x, y));
    }

    @SneakyThrows
    private static Plot newPlot(Class<?> clazz, int x, int y) {
        return (Plot) clazz.getConstructors()[0].newInstance(x, y);
    }

    private static Plot newPlotRandom(int x, int y) {
        return newPlot(BIOMES.get(ThreadLocalRandom.current().nextInt(BIOMES.size())), x, y);
    }
}
