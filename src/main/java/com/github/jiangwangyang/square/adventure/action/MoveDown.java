package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class MoveDown implements Action {

    public static final MoveDown INSTANCE = new MoveDown();

    @Override
    public String name() {
        return "↓";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity entity) {
        entity.direction = Entity.DOWN;
        entity.move(1);
    }

}
