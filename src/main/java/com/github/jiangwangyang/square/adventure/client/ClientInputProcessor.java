package com.github.jiangwangyang.square.adventure.client;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.github.jiangwangyang.square.adventure.common.Application;
import org.springframework.web.client.RestClient;

public class ClientInputProcessor implements InputProcessor {

    private final RestClient restClient = RestClient.create();
    private final String id = Application.INSTANCE.getId();

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.ENTER) {
            restClient.post()
                    .uri("http://localhost:8080/prepare/%s".formatted(id))
                    .body(i)
                    .retrieve()
                    .toEntity(String.class);
        }
        restClient.post()
                .uri("http://localhost:8080/input/%s".formatted(id))
                .body(i)
                .retrieve()
                .toEntity(String.class);
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
