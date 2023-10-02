/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;

/**
 * This is an interface representing an animation loop.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public interface Animation {

    /**
     * This method draws all objects on frame.
     *
     * @param d draw surface to draw objects on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This method signals when animation should stop.
     *
     * @return true or false if should stop.
     */
    boolean shouldStop();
}
