package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect522;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotSoulFire;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class DamageAllSoul implements Action {

    public static final DamageAllSoul INSTANCE = new DamageAllSoul();

    private DamageAllSoul() {
    }

    @Override
    public String name() {
        return "苦痛轮回";
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
                current.damageRange(x, y, Math.sqrt(2), current.damage * 1.2);
                Game.INSTANCE.getPlots().removeIf(plot -> plot.x == x && plot.y == y);
                Game.INSTANCE.getPlots().add(new PlotSoulFire(x, y));
                Game.INSTANCE.getEffects().add(new Effect522(x, y, 3));
            }
        }
    }
}
