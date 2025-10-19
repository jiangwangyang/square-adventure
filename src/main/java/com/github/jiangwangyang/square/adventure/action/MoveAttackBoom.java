package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1641;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

public final class MoveAttackBoom implements Action {

    public static final MoveAttackBoom INSTANCE = new MoveAttackBoom();

    private MoveAttackBoom() {
    }

    @Override
    public String name() {
        return "蓄意冲拳";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        for (int i = 0; i < 5; i++) {
            current.move(1);
            current.damageRange(current.x, current.y, 1, current.damage * 0.2);
            Game.INSTANCE.getEffects().add(new Effect1641(current.x, current.y, 2));
        }
        current.move(1);
        current.damageRange(current.x, current.y, Math.sqrt(2), current.damage * 1.1);
        Game.INSTANCE.getEffects().add(new Effect1641(current.x, current.y, 4));
    }
}
