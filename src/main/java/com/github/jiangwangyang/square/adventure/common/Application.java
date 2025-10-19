package com.github.jiangwangyang.square.adventure.common;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.jiangwangyang.square.adventure.action.Action;
import com.github.jiangwangyang.square.adventure.cell.Cell;
import com.github.jiangwangyang.square.adventure.cell.effect.Effect;
import com.github.jiangwangyang.square.adventure.cell.entity.Entity;
import com.github.jiangwangyang.square.adventure.cell.item.Item;
import com.github.jiangwangyang.square.adventure.cell.plot.Plot;
import com.github.jiangwangyang.square.adventure.util.ActionUtil;
import com.github.jiangwangyang.square.adventure.util.TextureDrawerFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public final class Application extends ApplicationAdapter {

    public static final Application INSTANCE = new Application();
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private final String id = UUID.randomUUID().toString();
    private final Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
    private Viewport worldViewport;
    private Viewport hudViewport;
    private BitmapFont bitmapFont;
    private ShapeRenderer shapeRenderer;
    private Batch batch;
    @Setter
    private InputProcessor inputProcessor;

    private Application() {
        configuration.setTitle("Square Adventure");
        configuration.setWindowedMode(WIDTH, HEIGHT);
    }

    @Override
    public void create() {
        // 初始化资源
        TextureDrawerFactory.init();
        // 初始化中文字体
        FreeTypeFontGenerator freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.classpath("font/simhei.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + ActionUtil.allActions().stream().map(Action::name).collect(Collectors.joining());
        bitmapFont = freeTypeFontGenerator.generateFont(param);
        freeTypeFontGenerator.dispose();
        // 初始化gdx
        worldViewport = new ExtendViewport(WIDTH, HEIGHT);
        hudViewport = new ExtendViewport(WIDTH, HEIGHT);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render() {
        Entity current = Game.INSTANCE.getEntities().stream()
                .filter(e -> id.equals(e.id))
                .findFirst()
                .orElse(null);
        if (current == null) {
            return;
        }
        // 清空屏幕
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // 获取变量
        Viewport worldViewport = Application.INSTANCE.getWorldViewport();
        Viewport hudViewport = Application.INSTANCE.getHudViewport();
        Batch batch = Application.INSTANCE.getBatch();
        ShapeRenderer shapeRenderer = Application.INSTANCE.getShapeRenderer();
        BitmapFont bitmapFont = Application.INSTANCE.getBitmapFont();
        // 地图信息
        worldViewport.getCamera().position.set(current.x * Cell.SIZE, current.y * Cell.SIZE, 0);
        worldViewport.apply();
        shapeRenderer.setProjectionMatrix(worldViewport.getCamera().combined);
        batch.setProjectionMatrix(worldViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        Arrays.stream(Game.INSTANCE.getGrid()).flatMap(Arrays::stream).forEach(Plot::draw);
        Game.INSTANCE.getPlots().forEach(Plot::draw);
        Game.INSTANCE.getEntities().forEach(Entity::draw);
        Game.INSTANCE.getItems().forEach(Item::draw);
        Game.INSTANCE.getEffects().forEach(Effect::draw);
        batch.end();
        shapeRenderer.end();
        // HUD信息
        shapeRenderer.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        shapeRenderer.setColor(Color.WHITE);
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.draw(batch, "LEFT: %d".formatted(Game.INSTANCE.getEntities().size()), 100, Application.HEIGHT - 15);
        bitmapFont.draw(batch, "KILL: %d".formatted(current.kill), 100, Application.HEIGHT - 35);
        int cardWidth = 140, cardHeight = 60;
        for (int i = 0; i < 9; i++) {
            shapeRenderer.rect(240 + i * cardWidth, Application.HEIGHT - cardHeight, cardWidth, cardHeight);
            bitmapFont.draw(batch, Integer.valueOf(i + 1).toString(), 240 + i * cardWidth + 5, Application.HEIGHT - 5);
        }
        for (int i = 0; i < current.actions.size(); i++) {
            bitmapFont.draw(batch, current.actions.get(i).name(), 260 + i * cardWidth, Application.HEIGHT - 25);
        }
        batch.end();
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        worldViewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }

}
