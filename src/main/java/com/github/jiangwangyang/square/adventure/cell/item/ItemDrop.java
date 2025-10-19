package com.github.jiangwangyang.square.adventure.cell.item;

import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.Player;
import com.github.jiangwangyang.square.adventure.common.Game;
import com.github.jiangwangyang.square.adventure.util.ActionUtil;

import java.util.ArrayList;
import java.util.List;

public final class ItemDrop extends Item {

    private final List<Action> actions = new ArrayList<>();

    public ItemDrop(int x, int y, List<Action> actions) {
        this.x = x;
        this.y = y;
        this.closedDrawerName = "Item07";
        this.openDrawerName = "ItemOpen07";
        this.actions.addAll(actions);
    }

    @Override
    public void act() {
        for (Entity entity : Game.INSTANCE.getEntities()) {
            if (entity instanceof Player player && x == player.x && y == player.y) {
                open = true;
                for (Action action : actions) {
                    if (player.actions.size() < 9) {
                        player.actions.add(action);
                    }
                }
                if (player.actions.size() < 9) {
                    player.actions.add(ActionUtil.randomAction());
                }
            }
        }
    }
}
