package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect515;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

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
        current.damageRange(x, y, Math.sqrt(2), current.damage * 1.2);
        Application.INSTANCE.getGame().getEffects().add(new Effect515(x, y, 3));
    }
}
