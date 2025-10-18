package com.github.jiangwangyang.square.adventure.cell.plot;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public final class PlotSandstone extends Plot {

    public static final Texture TEXTURE = new Texture("plot/sandstone_normal.png");

    public PlotSandstone(int x, int y) {
        super(new TextureDrawer(TEXTURE, 1, 1), x, y);
    }

}
