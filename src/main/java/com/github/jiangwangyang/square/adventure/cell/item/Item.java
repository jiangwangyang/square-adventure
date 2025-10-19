package com.github.jiangwangyang.square.adventure.cell.item;

import com.github.jiangwangyang.square.adventure.cell.AbstractCell;
import com.github.jiangwangyang.square.adventure.common.TextureDrawer;
import com.github.jiangwangyang.square.adventure.common.TextureDrawerFactory;

public abstract class Item extends AbstractCell {

    public volatile String closedDrawerName, openDrawerName;
    public volatile boolean open = false;

    @Override
    public void draw() {
        TextureDrawer drawer;
        if (open) {
            drawer = TextureDrawerFactory.INSTANCE.get(openDrawerName);
        } else {
            drawer = TextureDrawerFactory.INSTANCE.get(closedDrawerName);
        }
        drawer.draw(x, y);
    }

    @Override
    public boolean remove() {
        return open;
    }
}
