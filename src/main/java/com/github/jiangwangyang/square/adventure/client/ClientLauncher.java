package com.github.jiangwangyang.square.adventure.client;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.item.Item;
import com.github.jiangwangyang.square.adventure.cell.plot.Plot;
import com.github.jiangwangyang.square.adventure.common.Application;
import com.github.jiangwangyang.square.adventure.common.Game;
import com.github.jiangwangyang.square.adventure.server.model.GameData;
import com.github.jiangwangyang.square.adventure.util.KryoUtil;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public final class ClientLauncher {

    private static final String id = Application.INSTANCE.getId();
    private static final WebClient webClient = WebClient.create();

    public static void main(String[] args) {
        webClient.get()
                .uri("http://localhost:8080/flux/%s".formatted(id))
                .retrieve()
                .bodyToFlux(GameData.class)
                .subscribe(gameData -> {
                    Plot[][] grid = KryoUtil.readClassAndObject(gameData.getGrid());
                    List<Plot> plots = KryoUtil.readClassAndObject(gameData.getPlots());
                    List<Entity> entities = KryoUtil.readClassAndObject(gameData.getEntities());
                    List<Item> items = KryoUtil.readClassAndObject(gameData.getItems());
                    List<Effect> effects = KryoUtil.readClassAndObject(gameData.getEffects());
                    Game.INSTANCE.setGrid(grid);
                    Game.INSTANCE.setPlots(plots);
                    Game.INSTANCE.setEntities(entities);
                    Game.INSTANCE.setItems(items);
                    Game.INSTANCE.setEffects(effects);
                });
        Application.INSTANCE.setInputProcessor(new ClientInputProcessor());
        new Lwjgl3Application(Application.INSTANCE, Application.INSTANCE.getConfiguration());
    }

}
