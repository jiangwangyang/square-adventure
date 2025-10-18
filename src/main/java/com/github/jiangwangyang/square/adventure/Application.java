package com.github.jiangwangyang.square.adventure;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.util.PackageLoader;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Getter
public final class Application extends ApplicationAdapter {

    public static final Application INSTANCE = new Application();
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private final List<Action> actionPool = new ArrayList<>();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    private final Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
    private final GameInputProcessor gameInputProcessor = new GameInputProcessor();
    private BitmapFont bitmapFont;
    private Viewport worldViewport;
    private Viewport hudViewport;
    private ShapeRenderer shapeRenderer;
    private Batch batch;
    private Game game;

    private Application() {
        configuration.setTitle("Square Adventure");
        configuration.setWindowedMode(WIDTH, HEIGHT);
    }

    @Override
    public void create() {
        // 类加载
        PackageLoader.load("com.github.jiangwangyang.square.adventure.cell");
        List<Class<?>> actionClassList = PackageLoader.load("com.github.jiangwangyang.square.adventure.action");
        try {
            for (Class<?> clazz : actionClassList) {
                if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
                    Field field = clazz.getField("INSTANCE");
                    Action action = (Action) field.get(null);
                    for (int i = 0; i < action.weight(); i++) {
                        actionPool.add(action);
                    }
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // 初始化中文字体
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.classpath("font/simhei.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + actionPool.stream().map(Action::name).collect(Collectors.joining());
        bitmapFont = freeTypeFontGenerator.generateFont(param);
        freeTypeFontGenerator.dispose();
        // 初始化gdx
        worldViewport = new ExtendViewport(WIDTH, HEIGHT);
        hudViewport = new ExtendViewport(WIDTH, HEIGHT);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        game = new Game();
        executor.execute(game);
        Gdx.input.setInputProcessor(gameInputProcessor);
    }

    @Override
    public void render() {
        game.draw();
    }

    @Override
    public void resize(int width, int height) {
        worldViewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        game.stop();
        bitmapFont.dispose();
        executor.shutdownNow();
        batch.dispose();
        shapeRenderer.dispose();
    }

}
