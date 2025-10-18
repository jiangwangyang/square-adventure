package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect519 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/519.png");

    public Effect519(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 7, 3), x, y, scale);
    }

}
