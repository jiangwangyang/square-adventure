package com.github.jiangwangyang.square.adventure.cell.entity.player;

import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.util.ActionUtil;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class InputPlayer extends Player {

    private final BlockingQueue<Action> inputAction = new SynchronousQueue<>();

    public InputPlayer(String id) {
        this.id = id;
        drawHealth = true;
    }

    @Override
    public void act() {
        try {
            Action action = inputAction.take();
            action.act(this);
        } catch (InterruptedException ignored) {
        } finally {
            if (ThreadLocalRandom.current().nextInt(100) < 10 && actions.size() < 9) {
                actions.add(ActionUtil.randomAction());
            }
        }
    }

}
