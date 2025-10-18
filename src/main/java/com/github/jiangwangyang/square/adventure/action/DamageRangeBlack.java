package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect519;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public class DamageRangeBlack implements Action {

    public static final DamageRangeBlack INSTANCE = new DamageRangeBlack();

    private DamageRangeBlack() {
    }

    @Override
    public String name() {
        return "恶火蔓延";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        for (int i = 1; i < 9; i++) {
            int x = current.x;
            int y = current.y;
            switch (current.direction) {
                case Entity.LEFT -> x -= i;
                case Entity.RIGHT -> x += i;
                case Entity.UP -> y += i;
                case Entity.DOWN -> y -= i;
                default -> throw new IllegalStateException("Unexpected value: " + current.direction);
            }
            current.damageRange(x, y, 1, current.damage * 0.4);
            Application.INSTANCE.getGame().getEffects().add(new Effect519(x, y, 2));
        }
    }

}
