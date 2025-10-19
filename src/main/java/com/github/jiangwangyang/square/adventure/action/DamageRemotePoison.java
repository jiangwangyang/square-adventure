package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.effect.Effect1649;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DamageRemotePoison implements Action {

    public static final DamageRemotePoison INSTANCE = new DamageRemotePoison();

    private DamageRemotePoison() {
    }

    @Override
    public String name() {
        return "毒液";
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
            Game.INSTANCE.getEffects().add(new Effect1649(nearestEntity.x, nearestEntity.y, 3));
        } else {
            current.damageRange(current.x, current.y, Math.sqrt(2), current.damage * 1.1);
            Game.INSTANCE.getEffects().add(new Effect1649(current.x, current.y, 3));
        }
    }
}
