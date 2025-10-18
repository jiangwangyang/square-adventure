package com.github.jiangwangyang.square.adventure.cell.entity;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.Game;
import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.cell.Cell;
import com.github.jiangwangyang.square.adventure.cell.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Entity implements Cell {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    @Getter
    protected final List<Action> actions = new CopyOnWriteArrayList<>();
    private final TextureDrawer drawer;
    public volatile int x, y, direction;
    public volatile double health, maxHealth, damage;
    public volatile boolean flip = false;
    public volatile int kill;

    public Entity(TextureDrawer drawer) {
        this.drawer = drawer;
    }

    @Override
    public void draw() {
        drawer.draw(x, y, 0, 1, flip);
    }

    @Override
    public boolean remove() {
        return health <= 0;
    }

    public Action randomAction() {
        List<Action> actionPool = Application.INSTANCE.getActionPool();
        return actionPool.get(ThreadLocalRandom.current().nextInt(actionPool.size()));
    }

    public void damaged(double damage) {
        this.health -= damage;
    }

    public void healed(double health) {
        this.health += health;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    // 范围伤害
    public void damageRange(double x, double y, double range, double damage) {
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            if (entity != this) {
                if (Math.sqrt(Math.pow(x - entity.x, 2) + Math.pow(y - entity.y, 2)) <= range) {
                    boolean alive = !entity.remove();
                    entity.damaged(damage);
                    if (alive && entity.remove()) {
                        kill++;
                    }
                }
            }
        }
    }

    // 移动到Entity的旁边
    public void moveTo(Entity entity) {
        if (y < entity.y) {
            direction = UP;
        }
        if (y > entity.y) {
            direction = DOWN;
        }
        while (y < entity.y - 1) {
            move(1);
        }
        while (y > entity.y + 1) {
            move(1);
        }
        if (x < entity.x) {
            direction = RIGHT;
            flip = true;
        }
        if (x > entity.x) {
            direction = LEFT;
            flip = false;
        }
        while (x < entity.x - 1) {
            move(1);
        }
        while (x > entity.x + 1) {
            move(1);
        }
    }

    // 向自身朝向移动
    public void move(int distance) {
        for (int i = 0; i < distance; i++) {
            switch (direction) {
                case UP -> {
                    if (y == Game.HEIGHT - 1) {
                        return;
                    }
                    y++;
                    if (y > Game.HEIGHT - 1) {
                        throw new IllegalStateException("Entity moved out of the grid");
                    }
                }
                case DOWN -> {
                    if (y == 0) {
                        return;
                    }
                    y--;
                    if (y < 0) {
                        throw new IllegalStateException("Entity moved out of the grid");
                    }
                }
                case RIGHT -> {
                    if (x == Game.WIDTH - 1) {
                        return;
                    }
                    x++;
                    if (x > Game.WIDTH - 1) {
                        throw new IllegalStateException("Entity moved out of the grid");
                    }
                }
                case LEFT -> {
                    if (x == 0) {
                        return;
                    }
                    x--;
                    if (x < 0) {
                        throw new IllegalStateException("Entity moved out of the grid");
                    }
                }
                default -> throw new IllegalStateException("Unexpected direction: " + direction);
            }
        }
    }

    // 找到距离最近的Entity列表
    public List<Entity> nearestEntityList() {
        List<Entity> nearestEntityList = new ArrayList<>();
        double nearestDistance = Integer.MAX_VALUE;
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            if (entity != this) {
                double distance = distance(entity.x, entity.y);
                if (distance == nearestDistance) {
                    nearestEntityList.add(entity);
                }
                if (distance < nearestDistance) {
                    nearestEntityList.clear();
                    nearestEntityList.add(entity);
                    nearestDistance = distance;
                }
            }
        }
        return nearestEntityList;
    }

    // 找到距离最近的Item列表
    public List<Item> nearestItemList() {
        List<Item> nearestItemList = new ArrayList<>();
        double nearestDistance = Integer.MAX_VALUE;
        for (Item item : Application.INSTANCE.getGame().getItems()) {
            double distance = distance(item.x, item.y);
            if (distance == nearestDistance) {
                nearestItemList.add(item);
            }
            if (distance < nearestDistance) {
                nearestItemList.clear();
                nearestItemList.add(item);
                nearestDistance = distance;
            }
        }
        return nearestItemList;
    }

    // 计算距离
    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

}
