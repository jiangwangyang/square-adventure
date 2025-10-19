package com.github.jiangwangyang.square.adventure.action;

import com.github.jiangwangyang.square.adventure.util.PackageLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ActionPool {

    public static final List<Action> ACTION_POOL = new ArrayList<>();

    static {
        List<Class<?>> actionClassList = PackageLoader.load("com.github.jiangwangyang.square.adventure.action");
        try {
            for (Class<?> clazz : actionClassList) {
                if (Action.class.isAssignableFrom(clazz) && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
                    Field field = clazz.getField("INSTANCE");
                    Action action = (Action) field.get(null);
                    for (int i = 0; i < action.weight(); i++) {
                        ACTION_POOL.add(action);
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ActionPool() {
    }

    public static Action randomAction() {
        return ACTION_POOL.get(ThreadLocalRandom.current().nextInt(ACTION_POOL.size()));
    }

}
