package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.Game;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect520;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

import java.util.concurrent.ThreadLocalRandom;

public final class JumpRandom implements Action {

    public static final JumpRandom INSTANCE = new JumpRandom();

    private JumpRandom() {
    }

    @Override
    public String name() {
        return "千里之外";
    }

    @Override
    public int weight() {
        return 2;
    }

    @Override
    public void act(Entity current) {
        Application.INSTANCE.getGame().getEffects().add(new Effect520(current.x, current.y, 4));
        current.x = ThreadLocalRandom.current().nextInt(Game.WIDTH);
        current.y = ThreadLocalRandom.current().nextInt(Game.HEIGHT);
        Application.INSTANCE.getGame().getEffects().add(new Effect520(current.x, current.y, 4));
    }
}
