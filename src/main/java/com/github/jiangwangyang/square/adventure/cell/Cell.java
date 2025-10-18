package com.github.jiangwangyang.square.adventure.cell;

public interface Cell {

    int SIZE = 32;

    void draw();

    void act();

    boolean remove();

}
