/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import general.ScreenCoordinates;

/**
 * This is a class representing an animation runner.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class AnimationRunner {

    /*these represent the screen sizes and showing frequency*/
    public static final int FRAME_PER_SECOND = 60;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    /**
     * the animation runner vars.
     */
    private final GUI gui;
    private final int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * This constructor creates Animation runner from input.
     *
     * @param time frame per second
     * @param s    screen coordinates to give gui frame.
     */
    public AnimationRunner(int time, ScreenCoordinates s) {
        this.framesPerSecond = time;

        /*create drawing surface for ball.*/
        this.gui = new GUI("Game", s.getScreenWidth(), s.getScreenHeight());
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * This constructor creates default AnimationRunner.
     */
    public AnimationRunner() {
        this.framesPerSecond = FRAME_PER_SECOND;

        /*create drawing surface for ball.*/
        this.gui = new GUI("Game", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * This method returns gui.
     *
     * @return gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * This method runs animation loop.
     *
     * @param animation specified animation rules for this animation.
     */
    public void run(Animation animation) {

        /*run game so long as animation shouldn't stop*/
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            /*draw all objects on surface*/
            animation.doOneFrame(d);

            /*show animation*/
            gui.show(d);

            /*time lapse*/
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}