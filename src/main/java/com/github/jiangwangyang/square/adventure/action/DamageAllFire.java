package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect1651;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotFire;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotWater;

public final class DamageAllFire implements Action {

    public static final DamageAllFire INSTANCE = new DamageAllFire();

    private DamageAllFire() {
    }

    @Override
    public String name() {
        return "天下火雨";
    }

    @Override
    public int weight() {
        return 1;
    }

    @Override
    public void act(Entity current) {
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            int x = entity.x;
            int y = entity.y;
            if (current != entity) {
                if (!(Application.INSTANCE.getGame().getGrid()[x][y] instanceof PlotWater)) {
                    current.damageRange(x, y, Math.sqrt(2), current.damage * 1.4);
                    Application.INSTANCE.getGame().getPlots().removeIf(plot -> plot.x == x && plot.y == y);
                    Application.INSTANCE.getGame().getPlots().add(new PlotFire(x, y));
                } else {
                    current.damageRange(x, y, Math.sqrt(2), current.damage * 0.8);
                }
                Application.INSTANCE.getGame().getEffects().add(new Effect1651(x, y, 3));
            }
        }
    }
}
