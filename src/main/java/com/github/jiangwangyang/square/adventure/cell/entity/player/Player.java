package com.github.jiangwangyang.square.adventure.cell.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.Game;
import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Player extends Entity {

    public static final Texture TEXTURE = new Texture("entity/Monkey.png");

    public Player() {
        super(new TextureDrawer(TEXTURE, 1, 1));
        this.x = ThreadLocalRandom.current().nextInt(Game.WIDTH);
        this.y = ThreadLocalRandom.current().nextInt(Game.HEIGHT);
        health = 100;
        maxHealth = 100;
        damage = 5;
        for (int i = 0; i < 3; i++) {
            actions.add(randomAction());
        }
    }

}
