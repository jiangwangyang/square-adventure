package com.github.jiangwangyang.square.adventure.cell.effect;

import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.Cell;


public abstract class Effect implements Cell {

    // 图片 横向数量 纵向数量
    private final long startMillis = System.currentTimeMillis();
    private final TextureDrawer drawer;
    private final double x, y, scale;

    public Effect(TextureDrawer drawer, double x, double y, double scale) {
        this.drawer = drawer;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public void draw() {
        if (remove()) {
            return;
        }
        drawer.draw(x, y, startMillis, scale, false);
    }

    @Override
    public void act() {
    }

    @Override
    public boolean remove() {
        return (int) (System.currentTimeMillis() - startMillis) / TextureDrawer.FRAME_MILLIS >= drawer.length();
    }

}
