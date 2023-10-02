/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;
import moveable.Block;
import moveable.Sprite;

/**
 * This is a class representing the level of the game.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-05-27
 */
public class LevelIndicator implements Sprite {
    public static final int ZERO = 0;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 25;
    public static final int TEXT_WIDTH = 500;
    public static final int TEXT_HEIGHT = 20;
    public static final int TEXT_SIZE = 20;

    /**
     * This variable represents the game score.
     */
    private final LevelInformation level;

    /**
     * This constructor saves score counter given.
     *
     * @param l game level.
     */
    public LevelIndicator(LevelInformation l) {
        this.level = l;
    }

    @Override
    public void drawOn(DrawSurface d) {

        /*create panel sprite*/
        Block panel = new Block(new Rectangle(
                new Point(ZERO, ZERO), WIDTH, HEIGHT));
        panel.setColor(java.awt.Color.WHITE);
        panel.drawOn(d);

        /*create text*/
        String billBoard = "Level Name: " + this.level.levelName();
        d.setColor(java.awt.Color.BLACK);
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, billBoard, TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
