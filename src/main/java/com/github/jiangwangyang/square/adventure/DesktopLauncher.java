package com.github.jiangwangyang.square.adventure;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

public final class DesktopLauncher {

    static void main(String[] args) {
        new Lwjgl3Application(Application.INSTANCE, Application.INSTANCE.getConfiguration());
    }

}
