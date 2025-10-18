package com.github.jiangwangyang.square.adventure.cell.effect;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.TextureDrawer;

public class Effect522 extends Effect {

    public static final Texture TEXTURE = new Texture("effect/522.png");

    public Effect522(double x, double y, double scale) {
        super(new TextureDrawer(TEXTURE, 5, 5), x, y, scale);
    }

}
