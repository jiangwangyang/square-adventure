package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class None implements Action {

    public static final None INSTANCE = new None();

    private None() {
    }

    @Override
    public String name() {
        return "None";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity current) {
    }
}
