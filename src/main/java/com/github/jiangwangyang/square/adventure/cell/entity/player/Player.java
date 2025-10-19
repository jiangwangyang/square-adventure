package com.github.jiangwangyang.square.adventure.cell.entity.player;

import com.github.jiangwangyang.square.adventure.action.ActionPool;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Player extends Entity {

    public Player() {
        this.x = ThreadLocalRandom.current().nextInt(Game.WIDTH);
        this.y = ThreadLocalRandom.current().nextInt(Game.HEIGHT);
        health = 100;
        maxHealth = 100;
        damage = 5;
        for (int i = 0; i < 3; i++) {
            actions.add(ActionPool.randomAction());
        }
    }

}
