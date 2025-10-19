package com.github.jiangwangyang.square.adventure.cell;

import com.github.jiangwangyang.square.adventure.common.TextureDrawer;
import com.github.jiangwangyang.square.adventure.util.TextureDrawerFactory;

public abstract class AbstractCell implements Cell {

    public volatile int x, y;

    @Override
    public void draw() {
        TextureDrawer drawer = TextureDrawerFactory.get(getClass().getSimpleName());
        drawer.draw(x, y);
    }

    @Override
    public String toString() {
        return "%s{x=%d,y=%d}".formatted(getClass().getSimpleName(), x, y);
    }
}
