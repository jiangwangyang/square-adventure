package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1644;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DamageRemoteStone implements Action {

    public static final DamageRemoteStone INSTANCE = new DamageRemoteStone();

    private DamageRemoteStone() {
    }

    @Override
    public String name() {
        return "落石";
    }

    @Override
    public int weight() {
        return 10;
    }

    @Override
    public void act(Entity current) {
        List<Entity> nearestEntityList = current.nearestEntityList();
        Entity nearestEntity = nearestEntityList.isEmpty() ? null : nearestEntityList.get(ThreadLocalRandom.current().nextInt(nearestEntityList.size()));
        if (nearestEntity != null && current.distance(nearestEntity.x, nearestEntity.y) <= 12) {
            current.damageRange(nearestEntity.x, nearestEntity.y, Math.sqrt(2), current.damage * 1.1);
            Game.INSTANCE.getEffects().add(new Effect1644(nearestEntity.x, nearestEntity.y, 3));
        } else {
            current.damageRange(current.x, current.y, Math.sqrt(2), current.damage * 1.1);
            Game.INSTANCE.getEffects().add(new Effect1644(current.x, current.y, 3));
        }
    }
}
