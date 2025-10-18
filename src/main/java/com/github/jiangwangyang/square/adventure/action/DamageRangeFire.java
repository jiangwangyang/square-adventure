package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.Game;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect1647;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotFire;
import com.github.jiangwangyang.square.adventure.cell.plot.PlotWater;

public class DamageRangeFire implements Action {

    public static final DamageRangeFire INSTANCE = new DamageRangeFire();

    private DamageRangeFire() {
    }

    @Override
    public String name() {
        return "遍地火海";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        for (int i = -2; i <= 2; i++) {
            int range = Math.abs(2 - Math.abs(i));
            for (int j = -range; j <= range; j++) {
                int x = current.x + i;
                int y = current.y + j;
                if (x < 0 || x >= Game.WIDTH || y < 0 || y >= Game.HEIGHT) {
                    continue;
                }
                if (!(Application.INSTANCE.getGame().getGrid()[x][y] instanceof PlotWater)) {
                    current.damageRange(x, y, 1, current.damage * 0.28);
                    if (x != current.x || y != current.y) {
                        Application.INSTANCE.getGame().getPlots().removeIf(plot -> plot.x == x && plot.y == y);
                        Application.INSTANCE.getGame().getPlots().add(new PlotFire(x, y));
                    }
                } else {
                    current.damageRange(x, y, 1, current.damage * 0.16);
                }
                Application.INSTANCE.getGame().getEffects().add(new Effect1647(x, y, 3));
            }
        }
    }

}
