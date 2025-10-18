package com.github.jiangwangyang.square.adventure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jiangwangyang.square.adventure.cell.Cell;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.AIPlayer;
import com.github.jiangwangyang.square.adventure.cell.entity.player.InputPlayer;
import com.github.jiangwangyang.square.adventure.cell.entity.player.Player;
import com.github.jiangwangyang.square.adventure.cell.item.Item;
import com.github.jiangwangyang.square.adventure.cell.item.ItemDrop;
import com.github.jiangwangyang.square.adventure.cell.item.ItemRandom;
import com.github.jiangwangyang.square.adventure.cell.plot.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class Game implements Runnable {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 60;

    private final Plot[][] grid = new Plot[WIDTH][HEIGHT];
    private final List<Plot> plots = new CopyOnWriteArrayList<>();
    private final List<Entity> entities = new CopyOnWriteArrayList<>();
    private final List<Item> items = new CopyOnWriteArrayList<>();
    private final List<Effect> effects = new CopyOnWriteArrayList<>();
    private final Player currentPlayer = new InputPlayer();
    private volatile boolean running = true;

    public Game() {
        // 初始化plot列表
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Plot plot = switch (ThreadLocalRandom.current().nextInt(7)) {
                    case 0 -> new PlotWater(i, j);
                    case 1 -> new PlotLava(i, j);
                    case 2 -> new PlotGranite(i, j);
                    case 3 -> new PlotRedSand(i, j);
                    case 4 -> new PlotSand(i, j);
                    case 5 -> new PlotSandstone(i, j);
                    case 6 -> new PlotSandstone2(i, j);
                    default -> throw new IllegalStateException("Unexpected value");
                };
                grid[i][j] = plot;
            }
        }
        // 初始化entity列表
        for (int i = 0; i < 50; i++) {
            entities.add(new AIPlayer());
        }
        entities.add(currentPlayer);
        // 初始化item列表
        for (int i = 0; i < 50; i++) {
            items.add(new ItemRandom(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT)));
        }
    }

    @Override
    public void run() {
        while (running) {
            // 执行
            Arrays.stream(grid).flatMap(Arrays::stream).filter(cell -> !cell.remove()).forEach(Plot::act);
            plots.stream().filter(cell -> !cell.remove()).forEach(Plot::act);
            entities.stream().filter(cell -> !cell.remove()).forEach(Entity::act);
            items.stream().filter(cell -> !cell.remove()).forEach(Item::act);
            effects.stream().filter(cell -> !cell.remove()).forEach(Effect::act);
            // 移除
            entities.stream().filter(Entity::remove).forEach(entity -> items.add(new ItemDrop(entity.x, entity.y, entity.getActions())));
            plots.removeIf(Plot::remove);
            entities.removeIf(Entity::remove);
            items.removeIf(Item::remove);
            effects.removeIf(Effect::remove);
            if (entities.size() <= 1) {
                stop();
            }
        }
    }

    public void draw() {
        // 清空屏幕
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // 获取变量
        Viewport worldViewport = Application.INSTANCE.getWorldViewport();
        Viewport hudViewport = Application.INSTANCE.getHudViewport();
        Batch batch = Application.INSTANCE.getBatch();
        ShapeRenderer shapeRenderer = Application.INSTANCE.getShapeRenderer();
        BitmapFont bitmapFont = Application.INSTANCE.getBitmapFont();
        // 地图信息
        worldViewport.getCamera().position.set(currentPlayer.x * Cell.SIZE, currentPlayer.y * Cell.SIZE, 0);
        worldViewport.apply();
        shapeRenderer.setProjectionMatrix(worldViewport.getCamera().combined);
        batch.setProjectionMatrix(worldViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        Arrays.stream(grid).flatMap(Arrays::stream).forEach(Plot::draw);
        plots.forEach(Plot::draw);
        entities.forEach(Entity::draw);
        items.forEach(Item::draw);
        effects.forEach(Effect::draw);
        batch.end();
        shapeRenderer.end();
        // HUD信息
        shapeRenderer.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        shapeRenderer.setColor(Color.WHITE);
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.draw(batch, "LEFT: %d".formatted(entities.size()), 100, Application.HEIGHT - 15);
        bitmapFont.draw(batch, "KILL: %d".formatted(currentPlayer.kill), 100, Application.HEIGHT - 35);
        int cardWidth = 140, cardHeight = 60;
        for (int i = 0; i < 9; i++) {
            shapeRenderer.rect(240 + i * cardWidth, Application.HEIGHT - cardHeight, cardWidth, cardHeight);
            bitmapFont.draw(batch, Integer.valueOf(i + 1).toString(), 240 + i * cardWidth + 5, Application.HEIGHT - 5);
        }
        for (int i = 0; i < currentPlayer.getActions().size(); i++) {
            bitmapFont.draw(batch, currentPlayer.getActions().get(i).name(), 260 + i * cardWidth, Application.HEIGHT - 25);
        }
        batch.end();
        shapeRenderer.end();
    }

    public void stop() {
        running = false;
    }

}
