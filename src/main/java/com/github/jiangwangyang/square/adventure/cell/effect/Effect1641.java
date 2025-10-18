package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect1641 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/1641.png");

    public Effect1641(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 5, 4), x, y, scale);
    }

}
