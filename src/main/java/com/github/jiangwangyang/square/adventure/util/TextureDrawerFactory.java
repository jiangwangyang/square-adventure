package com.github.jiangwangyang.square.adventure.util;

import com.badlogic.gdx.graphics.Texture;
import com.github.jiangwangyang.square.adventure.common.TextureDrawer;

import java.util.HashMap;
import java.util.Map;

public final class TextureDrawerFactory {

    private static final Map<String, TextureDrawer> TEXTURE_DRAWER_MAP = new HashMap<>();

    private TextureDrawerFactory() {
    }

    public static void init() {
        TEXTURE_DRAWER_MAP.put("Effect515", new TextureDrawer(new Texture("effect/515.png"), 4, 4));
        TEXTURE_DRAWER_MAP.put("Effect519", new TextureDrawer(new Texture("effect/519.png"), 7, 3));
        TEXTURE_DRAWER_MAP.put("Effect520", new TextureDrawer(new Texture("effect/520.png"), 5, 5));
        TEXTURE_DRAWER_MAP.put("Effect522", new TextureDrawer(new Texture("effect/522.png"), 5, 5));
        TEXTURE_DRAWER_MAP.put("Effect1641", new TextureDrawer(new Texture("effect/1641.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1644", new TextureDrawer(new Texture("effect/1644.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1645", new TextureDrawer(new Texture("effect/1645.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1646", new TextureDrawer(new Texture("effect/1646.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1647", new TextureDrawer(new Texture("effect/1647.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1648", new TextureDrawer(new Texture("effect/1648.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1649", new TextureDrawer(new Texture("effect/1649.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1651", new TextureDrawer(new Texture("effect/1651.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1652", new TextureDrawer(new Texture("effect/1652.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("Effect1653", new TextureDrawer(new Texture("effect/1653.png"), 5, 4));
        TEXTURE_DRAWER_MAP.put("AIPlayer", new TextureDrawer(new Texture("entity/Monkey.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("InputPlayer", new TextureDrawer(new Texture("entity/Monkey.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item01", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box01.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen01", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box01.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item02", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box02.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen02", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box02.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item03", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box03.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen03", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box03.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item04", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box04.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen04", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box04.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item05", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box05.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen05", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box05.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item06", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box06.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen06", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box06.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("Item07", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box07.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("ItemOpen07", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box07.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotFire", new TextureDrawer(new Texture("plot/campfire_fire.png"), 1, 8));
        TEXTURE_DRAWER_MAP.put("PlotGranite", new TextureDrawer(new Texture("plot/stone_granite_smooth.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotLava", new TextureDrawer(new Texture("plot/lava_still.png"), 1, 20));
        TEXTURE_DRAWER_MAP.put("PlotRedSand", new TextureDrawer(new Texture("plot/red_sand.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotSand", new TextureDrawer(new Texture("plot/sand.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotSandstone", new TextureDrawer(new Texture("plot/sandstone_normal.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotSandstone2", new TextureDrawer(new Texture("plot/sandstone_bottom.png"), 1, 1));
        TEXTURE_DRAWER_MAP.put("PlotSoulFire", new TextureDrawer(new Texture("plot/soul_campfire_fire.png"), 1, 8));
        TEXTURE_DRAWER_MAP.put("PlotWater", new TextureDrawer(new Texture("plot/water_still.png"), 1, 32));
    }

    public static TextureDrawer get(String name) {
        if (TEXTURE_DRAWER_MAP.isEmpty()) {
            throw new IllegalStateException("TextureDrawerFactory not init");
        }
        return TEXTURE_DRAWER_MAP.get(name);
    }

}
