package com.github.jiangwangyang.square.adventure.cell.plot;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class PlotSoulFire extends Plot {

    public PlotSoulFire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void act() {
        for (Entity entity : Game.INSTANCE.getEntities()) {
            if (entity.x == x && entity.y == y) {
                entity.health -= 3;
            }
        }
    }
}
