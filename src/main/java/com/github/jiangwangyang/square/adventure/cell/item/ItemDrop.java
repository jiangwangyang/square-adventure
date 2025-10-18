package com.github.jiangwangyang.square.adventure.cell.item;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.Player;

import java.util.List;

public final class ItemDrop extends Item {

    public static final Texture CLOSED_TEXTURE = new Texture("item/ChallengeTower_raffle_box07.png");
    public static final Texture OPEN_TEXTURE = new Texture("item/ChallengeTower_raffle_box07_on.png");

    private final List<Action> actions;

    public ItemDrop(int x, int y, List<Action> actions) {
        super(new TextureDrawer(CLOSED_TEXTURE, 1, 1), new TextureDrawer(OPEN_TEXTURE, 1, 1), x, y);
        this.actions = List.of(actions.toArray(Action[]::new));
    }

    @Override
    public void act() {
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            if (entity instanceof Player player && x == player.x && y == player.y) {
                open = true;
                for (Action action : actions) {
                    if (player.getActions().size() < 9) {
                        player.getActions().add(action);
                    }
                }
                if (player.getActions().size() < 9) {
                    player.getActions().add(player.randomAction());
                }
            }
        }
    }
}
