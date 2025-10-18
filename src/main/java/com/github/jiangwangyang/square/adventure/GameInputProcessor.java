package com.github.jiangwangyang.square.adventure;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.github.jiangwangyang.square.adventure.action.*;
import com.github.jiangwangyang.square.adventure.cell.entity.player.InputPlayer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public final class GameInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown(int i) {
        if (!(Application.INSTANCE.getGame().getCurrentPlayer() instanceof InputPlayer inputPlayer)) {
            return false;
        }
        BlockingQueue<Action> currentAction = inputPlayer.getInputAction();
        List<Action> actions = Application.INSTANCE.getGame().getCurrentPlayer().getActions();
        switch (i) {
            case Input.Keys.W -> currentAction.offer(MoveUp.INSTANCE);
            case Input.Keys.A -> currentAction.offer(MoveLeft.INSTANCE);
            case Input.Keys.S -> currentAction.offer(MoveDown.INSTANCE);
            case Input.Keys.D -> currentAction.offer(MoveRight.INSTANCE);
            case Input.Keys.J -> currentAction.offer(Attack.INSTANCE);
            case Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5,
                 Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8, Input.Keys.NUM_9 -> {
                int index = i - Input.Keys.NUM_1;
                if (index < actions.size()) {
                    Action action = actions.get(index);
                    if (currentAction.offer(action)) {
                        actions.remove(index);
                    }
                }
            }
        }
        return false;
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
