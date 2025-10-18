package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class MoveLeft implements Action {

    public static final MoveLeft INSTANCE = new MoveLeft();

    @Override
    public String name() {
        return "←";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity entity) {
        entity.direction = Entity.LEFT;
        entity.flip = false;
        entity.move(1);
    }

}
