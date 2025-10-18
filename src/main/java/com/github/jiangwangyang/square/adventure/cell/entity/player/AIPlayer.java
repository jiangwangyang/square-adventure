package com.github.jiangwangyang.square.adventure.cell.entity.player;

import com.github.jiangwangyang.square.adventure.action.*;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class AIPlayer extends Player {

    @Override
    public void act() {
        try {
            if (!actions.isEmpty()) {
                actions.removeFirst().act(this);
                return;
            }
            List<Entity> nearestEntityList = nearestEntityList();
            Entity nearestEntity = nearestEntityList.isEmpty() ? null : nearestEntityList.get(ThreadLocalRandom.current().nextInt(nearestEntityList.size()));
            if (nearestEntity != null && distance(nearestEntity.x, nearestEntity.y) <= Math.sqrt(2)) {
                JumpAttack.INSTANCE.act(this);
            } else if (nearestEntity != null) {
                if (y < nearestEntity.y) {
                    MoveUp.INSTANCE.act(this);
                } else if (y > nearestEntity.y) {
                    MoveDown.INSTANCE.act(this);
                } else if (x < nearestEntity.x) {
                    MoveRight.INSTANCE.act(this);
                } else if (x > nearestEntity.x) {
                    MoveLeft.INSTANCE.act(this);
                } else {
                    JumpAttack.INSTANCE.act(this);
                }
            } else {
                JumpAttack.INSTANCE.act(this);
            }
        } finally {
            if (ThreadLocalRandom.current().nextInt(100) < 10 && actions.size() < 9) {
                actions.add(randomAction());
            }
        }
    }

}
