package com.github.jiangwangyang.square.adventure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.github.jiangwangyang.square.adventure.cell.Cell;

public final class TextureDrawer {

    public static final int FRAME_MILLIS = 40;

    private final Texture texture;
    private final int xNum, yNum;
    private final int pieceWidth, pieceHeight;

    public TextureDrawer(Texture texture, int xNum, int yNum) {
        this.texture = texture;
        this.xNum = xNum;
        this.yNum = yNum;
        this.pieceWidth = texture.getWidth() / xNum;
        this.pieceHeight = texture.getHeight() / yNum;
    }

    public void draw(double x, double y) {
        draw(x, y, 0, 1, false);
    }

    public void draw(double x, double y, long startMillis, double scale, boolean flip) {
        int i = (int) ((System.currentTimeMillis() - startMillis) / FRAME_MILLIS) % length();
        double size = Cell.SIZE * scale;
        x = x * Cell.SIZE - size / 2;
        y = y * Cell.SIZE - size / 2;
        Batch batch = Application.INSTANCE.getBatch();
        batch.draw(texture,
                (float) x, (float) y, (float) size, (float) size,
                ((i % xNum) * pieceWidth), (i / xNum) * pieceHeight, pieceWidth, pieceHeight,
                flip, false);
    }

    public int length() {
        return xNum * yNum;
    }

}
