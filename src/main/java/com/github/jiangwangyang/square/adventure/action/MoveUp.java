package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class MoveUp implements Action {

    public static final MoveUp INSTANCE = new MoveUp();

    @Override
    public String name() {
        return "↑";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity entity) {
        entity.direction = Entity.UP;
        entity.move(1);
    }

}
