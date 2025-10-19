package com.github.jiangwangyang.square.adventure.cell.effect;

import com.github.jiangwangyang.square.adventure.cell.AbstractCell;
import com.github.jiangwangyang.square.adventure.util.TextureDrawerFactory;


public abstract class Effect extends AbstractCell {

    // 图片 横向数量 纵向数量
    public long startMillis = System.currentTimeMillis();
    public double scale;

    @Override
    public void draw() {
        if (remove()) {
            return;
        }
        TextureDrawerFactory.get(getClass().getSimpleName()).draw(x, y, startMillis, scale, false, false);
    }

    @Override
    public void act() {
    }

    @Override
    public boolean remove() {
        return System.currentTimeMillis() - startMillis >= 1000;
    }

}
