package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect1652 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/1652.png");

    public Effect1652(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 5, 4), x, y, scale);
    }

}
