package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class MoveRight implements Action {

    public static final MoveRight INSTANCE = new MoveRight();

    @Override
    public String name() {
        return "→";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity entity) {
        entity.direction = Entity.RIGHT;
        entity.flip = true;
        entity.move(1);
    }

}
