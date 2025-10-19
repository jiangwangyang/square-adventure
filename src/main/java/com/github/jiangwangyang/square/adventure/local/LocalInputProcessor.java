package com.github.jiangwangyang.square.adventure.local;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.github.jiangwangyang.square.adventure.action.*;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.InputPlayer;
import com.github.jiangwangyang.square.adventure.common.Application;
import com.github.jiangwangyang.square.adventure.common.Game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalInputProcessor implements InputProcessor {

    private final String id = Application.INSTANCE.getId();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public LocalInputProcessor() {
        if (!Game.INSTANCE.isRunning()) {
            Game.INSTANCE.init();
            Game.INSTANCE.getEntities().add(new InputPlayer(id));
            executor.execute(Game.INSTANCE);
        }
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.ENTER) {
            if (Game.INSTANCE.isRunning()) {
                return false;
            }
            Game.INSTANCE.init();
            Game.INSTANCE.getEntities().add(new InputPlayer(id));
            executor.execute(Game.INSTANCE);
        }
        Entity entity = Game.INSTANCE.getEntities()
                .stream()
                .filter(e -> id.equals(e.id))
                .findFirst()
                .orElse(null);
        if (!(entity instanceof InputPlayer current)) {
            return false;
        }
        switch (i) {
            case Input.Keys.W -> current.getInputAction().offer(MoveUp.INSTANCE);
            case Input.Keys.A -> current.getInputAction().offer(MoveLeft.INSTANCE);
            case Input.Keys.S -> current.getInputAction().offer(MoveDown.INSTANCE);
            case Input.Keys.D -> current.getInputAction().offer(MoveRight.INSTANCE);
            case Input.Keys.J -> current.getInputAction().offer(Attack.INSTANCE);
            case Input.Keys.SPACE -> current.getInputAction().offer(None.INSTANCE);
            case Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5,
                 Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8, Input.Keys.NUM_9 -> {
                int index = i - Input.Keys.NUM_1;
                if (index < current.actions.size()) {
                    Action action = current.actions.get(index);
                    if (current.getInputAction().offer(action)) {
                        current.actions.remove(index);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
