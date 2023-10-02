/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This is a class representing a pause screen.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class PauseScreen implements Animation {

    /**
     * This method draws a pause screen.
     *
     * @param d draw surface for pause screen.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.MAGENTA);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * This method returns status of stopping the animation of a paused screen.
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }
}
