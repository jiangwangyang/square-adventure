package com.github.jiangwangyang.square.adventure.common;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public final class TextureDrawerFactory {

    public static final TextureDrawerFactory INSTANCE = new TextureDrawerFactory();

    private Map<String, TextureDrawer> textureDrawerMap;

    private TextureDrawerFactory() {
    }

    public void init() {
        textureDrawerMap = new HashMap<>();
        textureDrawerMap.put("Effect515", new TextureDrawer(new Texture("effect/515.png"), 4, 4));
        textureDrawerMap.put("Effect519", new TextureDrawer(new Texture("effect/519.png"), 7, 3));
        textureDrawerMap.put("Effect520", new TextureDrawer(new Texture("effect/520.png"), 5, 5));
        textureDrawerMap.put("Effect522", new TextureDrawer(new Texture("effect/522.png"), 5, 5));
        textureDrawerMap.put("Effect1641", new TextureDrawer(new Texture("effect/1641.png"), 5, 4));
        textureDrawerMap.put("Effect1644", new TextureDrawer(new Texture("effect/1644.png"), 5, 4));
        textureDrawerMap.put("Effect1645", new TextureDrawer(new Texture("effect/1645.png"), 5, 4));
        textureDrawerMap.put("Effect1646", new TextureDrawer(new Texture("effect/1646.png"), 5, 4));
        textureDrawerMap.put("Effect1647", new TextureDrawer(new Texture("effect/1647.png"), 5, 4));
        textureDrawerMap.put("Effect1648", new TextureDrawer(new Texture("effect/1648.png"), 5, 4));
        textureDrawerMap.put("Effect1649", new TextureDrawer(new Texture("effect/1649.png"), 5, 4));
        textureDrawerMap.put("Effect1651", new TextureDrawer(new Texture("effect/1651.png"), 5, 4));
        textureDrawerMap.put("Effect1652", new TextureDrawer(new Texture("effect/1652.png"), 5, 4));
        textureDrawerMap.put("Effect1653", new TextureDrawer(new Texture("effect/1653.png"), 5, 4));
        textureDrawerMap.put("AIPlayer", new TextureDrawer(new Texture("entity/Monkey.png"), 1, 1));
        textureDrawerMap.put("InputPlayer", new TextureDrawer(new Texture("entity/Monkey.png"), 1, 1));
        textureDrawerMap.put("Item01", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box01.png"), 1, 1));
        textureDrawerMap.put("ItemOpen01", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box01.png"), 1, 1));
        textureDrawerMap.put("Item02", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box02.png"), 1, 1));
        textureDrawerMap.put("ItemOpen02", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box02.png"), 1, 1));
        textureDrawerMap.put("Item03", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box03.png"), 1, 1));
        textureDrawerMap.put("ItemOpen03", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box03.png"), 1, 1));
        textureDrawerMap.put("Item04", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box04.png"), 1, 1));
        textureDrawerMap.put("ItemOpen04", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box04.png"), 1, 1));
        textureDrawerMap.put("Item05", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box05.png"), 1, 1));
        textureDrawerMap.put("ItemOpen05", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box05.png"), 1, 1));
        textureDrawerMap.put("Item06", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box06.png"), 1, 1));
        textureDrawerMap.put("ItemOpen06", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box06.png"), 1, 1));
        textureDrawerMap.put("Item07", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box07.png"), 1, 1));
        textureDrawerMap.put("ItemOpen07", new TextureDrawer(new Texture("item/ChallengeTower_raffle_box07.png"), 1, 1));
        textureDrawerMap.put("PlotFire", new TextureDrawer(new Texture("plot/campfire_fire.png"), 1, 8));
        textureDrawerMap.put("PlotGranite", new TextureDrawer(new Texture("plot/stone_granite_smooth.png"), 1, 1));
        textureDrawerMap.put("PlotLava", new TextureDrawer(new Texture("plot/lava_still.png"), 1, 20));
        textureDrawerMap.put("PlotRedSand", new TextureDrawer(new Texture("plot/red_sand.png"), 1, 1));
        textureDrawerMap.put("PlotSand", new TextureDrawer(new Texture("plot/sand.png"), 1, 1));
        textureDrawerMap.put("PlotSandstone", new TextureDrawer(new Texture("plot/sandstone_normal.png"), 1, 1));
        textureDrawerMap.put("PlotSandstone2", new TextureDrawer(new Texture("plot/sandstone_bottom.png"), 1, 1));
        textureDrawerMap.put("PlotSoulFire", new TextureDrawer(new Texture("plot/soul_campfire_fire.png"), 1, 8));
        textureDrawerMap.put("PlotWater", new TextureDrawer(new Texture("plot/water_still.png"), 1, 32));
    }

    public TextureDrawer get(String name) {
        if (textureDrawerMap == null) {
            throw new IllegalStateException("TextureDrawerFactory not init");
        }
        return textureDrawerMap.get(name);
    }

}
