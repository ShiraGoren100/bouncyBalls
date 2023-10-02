/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * This is a class representing a decorator of pause for animation.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class KeyPressStoppableAnimation implements Animation {

    /**
     * These represent the animation and its decorators.
     */
    private final Animation decorated;
    private final KeyboardSensor keyBoardSensor;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * This constructor copies animation to decorate.
     *
     * @param sensor    sensor for decorating with
     * @param key       name of key to sense
     * @param animation animation to decorate
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.decorated = animation;
        this.keyBoardSensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);

        /*check if end animation was asked for by key being pressed*/
        if (this.keyBoardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
