package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1648;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotWater;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class DamageAllLightning implements Action {

    public static final DamageAllLightning INSTANCE = new DamageAllLightning();

    private DamageAllLightning() {
    }

    @Override
    public String name() {
        return "天下惊雷";
    }

    @Override
    public int weight() {
        return 1;
    }

    @Override
    public void act(Entity current) {
        for (Entity entity : Game.INSTANCE.getEntities()) {
            int x = entity.x;
            int y = entity.y;
            if (current != entity) {
                if (Game.INSTANCE.getGrid()[x][y] instanceof PlotWater) {
                    current.damageRange(x, y, Math.sqrt(2), current.damage * 2.4);
                } else {
                    current.damageRange(x, y, Math.sqrt(2), current.damage * 1.2);
                }
                Game.INSTANCE.getEffects().add(new Effect1648(x, y, 3));
            }
        }
    }
}
