package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1652;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class Heal implements Action {

    public static final Heal INSTANCE = new Heal();

    private Heal() {
    }

    @Override
    public String name() {
        return "治疗";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        int x = current.x;
        int y = current.y;
        current.healed(15);
        Game.INSTANCE.getEffects().add(new Effect1652(x, y, 3));
    }
}
