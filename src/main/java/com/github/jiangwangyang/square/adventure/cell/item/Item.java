package com.github.jiangwangyang.square.adventure.cell.item;

import com.github.jiangwangyang.square.adventure.cell.AbstractCell;
import com.github.jiangwangyang.square.adventure.common.TextureDrawer;
import com.github.jiangwangyang.square.adventure.util.TextureDrawerFactory;

public abstract class Item extends AbstractCell {

    public volatile String closedDrawerName, openDrawerName;
    public volatile boolean open = false;

    @Override
    public void draw() {
        TextureDrawer drawer;
        if (open) {
            drawer = TextureDrawerFactory.get(openDrawerName);
        } else {
            drawer = TextureDrawerFactory.get(closedDrawerName);
        }
        drawer.draw(x, y);
    }

    @Override
    public boolean remove() {
        return open;
    }
}
