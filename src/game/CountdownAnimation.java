/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import moveable.SpriteCollection;
import java.awt.Color;

/**
 * This is a class representing an count down.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class CountdownAnimation implements Animation {
    public static final int TEXT_WIDTH = 390;
    public static final int TEXT_HEIGHT = 350;
    public static final int TEXT_SIZE = 40;

    /**
     * These vars hold count down vars.
     */
    private final double seconds;
    private final int start;
    private int currentCount;
    private final SpriteCollection gScreen;
    private final Sleeper sleeper;

    /**
     * This constructor initializes countdown vars.
     *
     * @param numOfSeconds num of seconds to count
     * @param countFrom    num to start count from
     * @param gameScreen   all objects to show on screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.start = countFrom;
        this.currentCount = countFrom;
        this.gScreen = gameScreen;
        this.sleeper = new Sleeper();
    }

    /**
     * This method prints out count down to screen.
     *
     * @param d draw surface to print count down to
     */
    public void countDown(DrawSurface d) {

        /*create text*/
        String billBoard = "" + this.currentCount;
        d.setColor(Color.YELLOW);
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, billBoard, TEXT_SIZE);

        /*decrees counter*/
        this.currentCount -= 1;
    }

    /**
     * This method prints count down on game screen.
     *
     * @param d draw surface of game
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        /*draw all objects and notify them time passed*/
        this.gScreen.drawAllOn(d);
        this.countDown(d);

    }

    /**
     * This method adds time to count down, and stops count down when ends.
     * @return if animation should stop.
     */
    @Override
    public boolean shouldStop() {
        if (this.currentCount != this.start) {
            long startTime = System.currentTimeMillis(); // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (long) ((this.seconds / this.start) - usedTime);
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        return this.currentCount == 0;
    }
}