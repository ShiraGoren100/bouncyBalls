/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass5
 */

package moveable;
import biuoop.DrawSurface;

/**
 *  This is an interface representing a sprite, for drawing on screen.
 * @author Shira Goren.
 * @version 2
 * @since 2021-04-16
 */
public interface Sprite {

    /**
     * This method draws object on screen.
     * @param d - surface to draw on.
     * */
    void drawOn(DrawSurface d);

    /**
     * This method notifies sprite that time has passed.
     * */
    void timePassed();
}
