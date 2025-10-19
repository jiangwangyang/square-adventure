package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1645;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotWater;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class HealWater implements Action {

    public static final HealWater INSTANCE = new HealWater();

    private HealWater() {
    }

    @Override
    public String name() {
        return "水疗";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        int x = current.x;
        int y = current.y;
        if (Game.INSTANCE.getGrid()[x][y] instanceof PlotWater) {
            current.healed(20);
        } else {
            current.healed(10);
        }
        Game.INSTANCE.getEffects().add(new Effect1645(x, y, 3));
    }
}
