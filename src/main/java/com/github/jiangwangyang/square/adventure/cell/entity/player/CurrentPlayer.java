package com.github.jiangwangyang.square.adventure.cell.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.cell.Cell;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public final class CurrentPlayer extends Player {

    private final BlockingQueue<Action> currentAction = new SynchronousQueue<>();

    @Override
    public void draw() {
        super.draw();
        // 血量
        ShapeRenderer shapeRenderer = Application.INSTANCE.getShapeRenderer();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect((float) ((x - 0.5) * Cell.SIZE) - 1, (float) ((y + 0.5) * Cell.SIZE), Cell.SIZE + 2, 6);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect((float) ((x - 0.5) * Cell.SIZE), (float) ((y + 0.5) * Cell.SIZE) + 1, (float) (Cell.SIZE * health / 100), 1);
        shapeRenderer.rect((float) ((x - 0.5) * Cell.SIZE), (float) ((y + 0.5) * Cell.SIZE) + 2, (float) (Cell.SIZE * health / 100), 1);
        shapeRenderer.rect((float) ((x - 0.5) * Cell.SIZE), (float) ((y + 0.5) * Cell.SIZE) + 3, (float) (Cell.SIZE * health / 100), 1);
        shapeRenderer.rect((float) ((x - 0.5) * Cell.SIZE), (float) ((y + 0.5) * Cell.SIZE) + 4, (float) (Cell.SIZE * health / 100), 1);
    }

    @Override
    public void act() {
        try {
            Action action = currentAction.take();
            action.act(this);
        } catch (InterruptedException ignored) {
        } finally {
            if (ThreadLocalRandom.current().nextInt(100) < 10 && actions.size() < 9) {
                actions.add(randomAction());
            }
        }
    }

}
