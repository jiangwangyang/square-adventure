package com.github.jiangwangyang.square.adventure.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameData {

    private byte[] grid;
    private byte[] plots;
    private byte[] entities;
    private byte[] items;
    private byte[] effects;

}
