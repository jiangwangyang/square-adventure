package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect515;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class JumpAttackBoom implements Action {

    public static final JumpAttackBoom INSTANCE = new JumpAttackBoom();

    private JumpAttackBoom() {
    }

    @Override
    public String name() {
        return "炎击";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        List<Entity> nearestEntityList = current.nearestEntityList();
        Entity nearestEntity = nearestEntityList.isEmpty() ? null : nearestEntityList.get(ThreadLocalRandom.current().nextInt(nearestEntityList.size()));
        if (nearestEntity != null && current.distance(nearestEntity.x, nearestEntity.y) <= 6) {
            current.moveTo(nearestEntity);
        } else {
            current.move(5);
        }
        int x = current.x;
        int y = current.y;
        current.damageRange(x, y, Math.sqrt(2), current.damage * 1.2);
        Game.INSTANCE.getEffects().add(new Effect515(x, y, 3));
    }
}
