/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import general.ScreenCoordinates;
import graphics.Point;
import graphics.Rectangle;
import moveable.Block;
import java.awt.Color;

/**
 * This is a class representing the end screen.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class EndScreen implements Animation {

    /*text sizes*/
    private static final int TEXT_WIDTH = 60;
    private static final int TEXT_HEIGHT = 300;
    private static final int TEXT_SIZE = 50;

    /*screen sizes*/
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int SCREEN_START = 0;

    /**
     * These are the end screen vars.
     */
    private final boolean isAWinner;
    private final int score;
    private final ScreenCoordinates screenCoordinates;

    /**
     * This constructor finds out if winne screen or loser screen is needed.
     *
     * @param didWin       flag if game was won or lost
     * @param scoreManaged score got from all levels
     */
    public EndScreen(boolean didWin, int scoreManaged) {
        this.isAWinner = didWin;
        this.screenCoordinates = new ScreenCoordinates(SCREEN_WIDTH,
                SCREEN_HEIGHT, SCREEN_START);
        this.score = scoreManaged;

    }

    /**
     * This is the end screen for losers.
     *
     * @param screen block to draw on
     * @param d      draw surface to draw on
     */
    public void loser(Block screen, DrawSurface d) {
        screen.setColor(Color.BLACK);
        screen.drawOn(d);

        /*create text*/
        String billBoard = "Game Over. Your score is " + this.score;
        d.setColor(Color.RED);
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, billBoard, TEXT_SIZE);
    }

    /**
     * This is the end screen for winners.
     *
     * @param screen block to draw on
     * @param d      draw surface to draw on
     */
    public void winner(Block screen, DrawSurface d) {
        screen.setColor(Color.PINK);
        screen.drawOn(d);

        /*create text*/
        String billBoard = "You Win! Your score is " + this.score;
        d.setColor(new Color(128, 0, 128));
        d.drawText(TEXT_WIDTH, TEXT_HEIGHT, billBoard, TEXT_SIZE);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Block endScreen = new Block(new Rectangle(new Point(
                this.screenCoordinates.getStart(),
                this.screenCoordinates.getStart()),
                this.screenCoordinates.getScreenWidth(),
                this.screenCoordinates.getScreenHeight()));

        if (this.isAWinner) {
            this.winner(endScreen, d);
        } else {
            this.loser(endScreen, d);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
