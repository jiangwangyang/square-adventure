package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1653;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class Attack implements Action {

    public static final Attack INSTANCE = new Attack();

    private Attack() {
    }

    @Override
    public String name() {
        return "攻击";
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public void act(Entity current) {
        double x = current.x;
        double y = current.y;
        switch (current.direction) {
            case Entity.LEFT:
                x -= 0.5;
                break;
            case Entity.RIGHT:
                x += 0.5;
                break;
            case Entity.UP:
                y += 0.5;
                break;
            case Entity.DOWN:
                y -= 0.5;
                break;
        }
        current.damageRange(x, y, Math.sqrt(2), current.damage);
        Game.INSTANCE.getEffects().add(new Effect1653(x, y, 3));
    }
}
