package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1646;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DamageRemoteSpike implements Action {

    public static final DamageRemoteSpike INSTANCE = new DamageRemoteSpike();

    private DamageRemoteSpike() {
    }

    @Override
    public String name() {
        return "尖刺";
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
            Game.INSTANCE.getEffects().add(new Effect1646(nearestEntity.x, nearestEntity.y, 3));
        } else {
            current.damageRange(current.x, current.y, Math.sqrt(2), current.damage * 1.1);
            Game.INSTANCE.getEffects().add(new Effect1646(current.x, current.y, 3));
        }
    }
}
