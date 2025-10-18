package com.github.jiangwangyang.square.adventure.cell.plot;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public final class PlotWater extends Plot {

    public static final Texture TEXTURE = new Texture("plot/water_still.png");

    public PlotWater(int x, int y) {
        super(new TextureDrawer(TEXTURE, 1, 32), x, y);
    }
    
}
