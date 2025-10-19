package com.github.jiangwangyang.square.adventure.cell.item;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.Player;
import com.github.jiangwangyang.square.adventure.common.Game;
import com.github.jiangwangyang.square.adventure.util.ActionUtil;

import java.util.concurrent.ThreadLocalRandom;

public final class ItemRandom extends Item {

    public ItemRandom(int x, int y) {
        this.x = x;
        this.y = y;
        int i = ThreadLocalRandom.current().nextInt(1, 7);
        this.closedDrawerName = "Item%02d".formatted(i);
        this.openDrawerName = "ItemOpen%02d".formatted(i);
    }

    @Override
    public void act() {
        for (Entity entity : Game.INSTANCE.getEntities()) {
            if (entity instanceof Player player && x == player.x && y == player.y) {
                open = true;
                if (player.actions.size() < 9) {
                    player.actions.add(ActionUtil.randomAction());
                }
            }
        }
    }
}
