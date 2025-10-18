package com.github.jiangwangyang.square.adventure.cell.item;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.Application;
import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.entity.player.Player;

import java.util.List;

public final class ItemRandom extends Item {

    public static final List<Texture> CLOSED_TEXTURE_LIST = List.of(
            new Texture("item/ChallengeTower_raffle_box01.png"),
            new Texture("item/ChallengeTower_raffle_box02.png"),
            new Texture("item/ChallengeTower_raffle_box03.png"),
            new Texture("item/ChallengeTower_raffle_box04.png"),
            new Texture("item/ChallengeTower_raffle_box05.png"),
            new Texture("item/ChallengeTower_raffle_box06.png")
    );
    public static final List<Texture> OPEN_TEXTURE_LIST = List.of(
            new Texture("item/ChallengeTower_raffle_box01_on.png"),
            new Texture("item/ChallengeTower_raffle_box02_on.png"),
            new Texture("item/ChallengeTower_raffle_box03_on.png"),
            new Texture("item/ChallengeTower_raffle_box04_on.png"),
            new Texture("item/ChallengeTower_raffle_box05_on.png"),
            new Texture("item/ChallengeTower_raffle_box06_on.png")
    );

    public ItemRandom(int x, int y) {
        int i = (int) (Math.random() * CLOSED_TEXTURE_LIST.size());
        super(new TextureDrawer(CLOSED_TEXTURE_LIST.get(i), 1, 1), new TextureDrawer(OPEN_TEXTURE_LIST.get(i), 1, 1), x, y);
    }

    @Override
    public void act() {
        for (Entity entity : Application.INSTANCE.getGame().getEntities()) {
            if (entity instanceof Player player && x == player.x && y == player.y) {
                open = true;
                if (player.getActions().size() < 9) {
                    player.getActions().add(player.randomAction());
                }
            }
        }
    }
}
