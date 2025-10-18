package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect520 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/520.png");

    public Effect520(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 5, 5), x, y, scale);
    }

}
