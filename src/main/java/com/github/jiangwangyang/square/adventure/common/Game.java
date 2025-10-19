package com.github.jiangwangyang.square.adventure.common;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.AIPlayer;
import com.github.jiangwangyang.square.adventure.cell.item.Item;
import com.github.jiangwangyang.square.adventure.cell.item.ItemDrop;
import com.github.jiangwangyang.square.adventure.cell.item.ItemRandom;
import com.github.jiangwangyang.square.adventure.cell.plot.Plot;
import com.github.jiangwangyang.square.adventure.util.MapUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Slf4j
public final class Game implements Runnable {

    public static final Game INSTANCE = new Game();

    public static final int WIDTH = 100;
    public static final int HEIGHT = 60;
    public static final int PLAYER_NUM = 20;
    public static final int ITEM_NUM = 50;

    private volatile Plot[][] grid;
    private volatile List<Plot> plots;
    private volatile List<Entity> entities;
    private volatile List<Item> items;
    private volatile List<Effect> effects;
    private volatile boolean running;

    private Game() {
        init();
    }

    public void init() {
        log.info("init game");
        // 初始化参数
        grid = new Plot[WIDTH][HEIGHT];
        plots = new CopyOnWriteArrayList<>();
        entities = new CopyOnWriteArrayList<>();
        items = new CopyOnWriteArrayList<>();
        effects = new CopyOnWriteArrayList<>();
        // 初始化grid网格
        MapUtil.generate(grid);
        // plot列表为空
        // 初始化entity列表
        for (int i = 0; i < PLAYER_NUM; i++) {
            entities.add(new AIPlayer());
        }
        // 初始化item列表
        for (int i = 0; i < ITEM_NUM; i++) {
            items.add(new ItemRandom(ThreadLocalRandom.current().nextInt(WIDTH), ThreadLocalRandom.current().nextInt(HEIGHT)));
        }
        // effect列表为空
        // running 为 false
        running = false;
    }

    @Override
    public void run() {
        log.info("start game");
        running = true;
        while (running) {
            // 执行
            Arrays.stream(grid).flatMap(Arrays::stream).filter(cell -> !cell.remove()).forEach(Plot::act);
            plots.stream().filter(cell -> !cell.remove()).forEach(Plot::act);
            entities.stream().filter(cell -> !cell.remove()).forEach(Entity::act);
            items.stream().filter(cell -> !cell.remove()).forEach(Item::act);
            effects.stream().filter(cell -> !cell.remove()).forEach(Effect::act);
            // 移除
            entities.stream().filter(Entity::remove).forEach(entity -> items.add(new ItemDrop(entity.x, entity.y, entity.actions)));
            plots.removeIf(Plot::remove);
            entities.removeIf(Entity::remove);
            items.removeIf(Item::remove);
            effects.removeIf(Effect::remove);
            if (entities.size() <= 1) {
                stop();
            }
        }
        log.info("end game");
    }

    public void stop() {
        running = false;
    }
}
