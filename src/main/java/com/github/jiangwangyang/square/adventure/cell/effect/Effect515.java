package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect515 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/515.png");

    public Effect515(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 4, 4), x, y, scale);
    }

}
