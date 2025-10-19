package com.github.jiangwangyang.square.adventure.local;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.github.jiangwangyang.square.adventure.common.Application;

public final class DesktopLauncher {

    public static void main(String[] args) {
        Application.INSTANCE.setInputProcessor(new LocalInputProcessor());
        new Lwjgl3Application(Application.INSTANCE, Application.INSTANCE.getConfiguration());
    }

}
