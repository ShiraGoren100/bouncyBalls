/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package moveable;

import biuoop.DrawSurface;
import general.Counter;

/**
 * This is a class representing the score of the game.
 *
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class ScoreIndicator implements Sprite {
    public static final int ZERO = 0;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 25;
    public static final int TEXT_WIDTH = 350;
    public static final int TEXT_HEIGHT = 20;
    public static final int TEXT_SIZE = 20;

    /**
     * This variable represents the game score.
     */
    private final Counter scoreCounter;

    /**
     * This constructor saves score counter given.
     *
     * @param s score counter.
     */
    public ScoreIndicator(Counter s) {
        this.scoreCounter = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        ;
        /*create text*/
        String billBoard = "Score: " + this.scoreCounter.getValue();
        d.setColor(java.awt.Color.BLACK);
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, billBoard, TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
