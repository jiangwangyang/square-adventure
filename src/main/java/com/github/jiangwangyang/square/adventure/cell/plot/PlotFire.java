package com.github.jiangwangyang.square.adventure.cell.plot;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public final class PlotFire extends Plot {

    public static final Texture TEXTURE = new Texture("plot/campfire_fire.png");

    public PlotFire(int x, int y) {
        super(new TextureDrawer(TEXTURE, 1, 8), x, y);
    }

    @Override
    public void act() {
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            if (entity.x == x && entity.y == y) {
                entity.health -= 2;
            }
        }
    }
}
