package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.cell.entity.Entity;

public interface Action {

    String name();

    int weight();

    void act(Entity entity);

}
