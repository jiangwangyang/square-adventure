package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class JumpAttack implements Action {

    public static final JumpAttack INSTANCE = new JumpAttack();

    private JumpAttack() {
    }

    @Override
    public String name() {
        return "闪击";
    }

    @Override
    public int weight() {
        return 15;
    }

    @Override
    public void act(Entity current) {
        List<Entity> nearestEntityList = current.nearestEntityList();
        Entity nearestEntity = nearestEntityList.isEmpty() ? null : nearestEntityList.get(ThreadLocalRandom.current().nextInt(nearestEntityList.size()));
        if (nearestEntity != null && current.distance(nearestEntity.x, nearestEntity.y) <= 4) {
            current.moveTo(nearestEntity);
        } else {
            current.move(3);
        }
        Attack.INSTANCE.act(current);
    }
}
