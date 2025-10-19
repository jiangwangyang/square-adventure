package com.github.jiangwangyang.square.adventure.server.controller;

import com.badlogic.gdx.Input;
import com.github.jiangwangyang.square.adventure.action.*;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.InputPlayer;
import com.github.jiangwangyang.square.adventure.common.Game;
import com.github.jiangwangyang.square.adventure.server.model.GameData;
import com.github.jiangwangyang.square.adventure.util.KryoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class GameController {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final Map<String, Boolean> playerPrepareMap = new ConcurrentHashMap<>();

    private void startGame() {
        Game.INSTANCE.init();
        for (String id : playerPrepareMap.keySet()) {
            Game.INSTANCE.getEntities().add(new InputPlayer(id));
        }
        executor.execute(Game.INSTANCE);
        for (Map.Entry<String, Boolean> entry : playerPrepareMap.entrySet()) {
            entry.setValue(false);
        }
    }

    @GetMapping("/flux/{id}")
    public Flux<GameData> flux(@PathVariable("id") String id) {
        Sinks.Many<GameData> sink = Sinks.many().multicast().onBackpressureBuffer();
        executor.execute(() -> {
            log.info("start flux for {}", id);
            playerPrepareMap.putIfAbsent(id, false);
            while (true) {
                GameData gameData = new GameData(
                        KryoUtil.writeClassAndObject(Game.INSTANCE.getGrid()),
                        KryoUtil.writeClassAndObject(Game.INSTANCE.getPlots()),
                        KryoUtil.writeClassAndObject(Game.INSTANCE.getEntities()),
                        KryoUtil.writeClassAndObject(Game.INSTANCE.getItems()),
                        KryoUtil.writeClassAndObject(Game.INSTANCE.getEffects()));
                Sinks.EmitResult result = sink.tryEmitNext(gameData);
                if (result.isFailure()) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
            log.info("end flux for {}", id);
            sink.tryEmitComplete();
            Game.INSTANCE.getEntities().removeIf(e -> {
                if (id.equals(e.id)) {
                    if (e instanceof InputPlayer player) {
                        player.getInputAction().offer(None.INSTANCE);
                    }
                    return true;
                }
                return false;
            });
            playerPrepareMap.remove(id);
        });
        return sink.asFlux();
    }

    @PostMapping("/prepare/{id}")
    public String prepare(@PathVariable("id") String id) {
        if (Game.INSTANCE.isRunning()) {
            log.warn("can't prepare. game is running");
            return "fail";
        }
        log.info("prepare for {}", id);
        playerPrepareMap.put(id, true);
        if (playerPrepareMap.size() == playerPrepareMap.values().stream().filter(Boolean::booleanValue).count()) {
            startGame();
        }
        return "success";
    }

    @PostMapping("/input/{id}")
    public String input(@PathVariable("id") String id, @RequestBody Integer input) {
        Entity entity = Game.INSTANCE.getEntities().stream().filter(e -> id.equals(e.id)).findFirst().orElse(null);
        if (!(entity instanceof InputPlayer current)) {
            return "fail";
        }
        BlockingQueue<Action> currentAction = current.getInputAction();
        switch (input) {
            case Input.Keys.W -> currentAction.offer(MoveUp.INSTANCE);
            case Input.Keys.A -> currentAction.offer(MoveLeft.INSTANCE);
            case Input.Keys.S -> currentAction.offer(MoveDown.INSTANCE);
            case Input.Keys.D -> currentAction.offer(MoveRight.INSTANCE);
            case Input.Keys.J -> currentAction.offer(Attack.INSTANCE);
            case Input.Keys.SPACE -> current.getInputAction().offer(None.INSTANCE);
            case Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5,
                 Input.Keys.NUM_6, Input.Keys.NUM_7, Input.Keys.NUM_8, Input.Keys.NUM_9 -> {
                int index = input - Input.Keys.NUM_1;
                if (index < current.actions.size()) {
                    Action action = current.actions.get(index);
                    if (currentAction.offer(action)) {
                        current.actions.remove(index);
                    }
                }
            }
            default -> {
            }
        }
        return "success";
    }

}
