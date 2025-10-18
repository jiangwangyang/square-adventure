package com.github.jiangwangyang.square.adventure.cell.plot;

import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.Cell;

public abstract class Plot implements Cell {

    public final int x, y;
    private final TextureDrawer drawer;

    public Plot(TextureDrawer drawer, int x, int y) {
        this.drawer = drawer;
        this.x = x;
        this.y = y;
    }

    @Override
    public void act() {
    }

    @Override
    public void draw() {
        drawer.draw(x, y);
    }

    @Override
    public boolean remove() {
        return false;
    }

}
