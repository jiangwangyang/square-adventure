package com.github.jiangwangyang.square.adventure.cell.item;

import com.github.jiangwangyang.square.adventure.TextureDrawer;
import com.github.jiangwangyang.square.adventure.cell.Cell;

public abstract class Item implements Cell {

    public final int x, y;
    private final TextureDrawer closedDrawer, openDrawer;
    public volatile boolean open = false;

    public Item(TextureDrawer closedDrawer, TextureDrawer openDrawer, int x, int y) {
        this.closedDrawer = closedDrawer;
        this.openDrawer = openDrawer;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        if (open) {
            openDrawer.draw(x, y);
        } else {
            closedDrawer.draw(x, y);
        }
    }

    @Override
    public boolean remove() {
        return open;
    }
}
