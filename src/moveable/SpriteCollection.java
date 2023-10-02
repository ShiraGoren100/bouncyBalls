/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass5
 */

package moveable;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class representing a sprite collection.
 *
 * @author Shira Goren.
 * @version 2
 * @since 2021-04-16
 */
public class SpriteCollection {

    /**
     * This is a list of all sprites.
     */
    private final List<Sprite> spriteList;

    /**
     * This constructor initializes the list of sprites.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * This method adds sprite object to sprite list.
     *
     * @param s - sprite to add to sprite list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * This method removes sprite from list.
     *
     * @param s sprite to remove
     */
    public void removeASprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * This method calls timePassed method on all sprites.
     */
    public void notifyAllTimePassed() {

        /* Make a copy of the Sprites before iterating over them.*/
        List<Sprite> temp = new ArrayList<Sprite>(this.spriteList);

        /*run through all sprites and call timePassed for each one.*/
        for (Sprite current : temp) {
            current.timePassed();
        }
    }

    /**
     * This method calls drawOn method on all sprites.
     *
     * @param d - surface to draw all sprites on.
     */
    public void drawAllOn(DrawSurface d) {

        /*run through all sprites and call drawOn for each one.*/
        for (Sprite current : this.spriteList) {
            current.drawOn(d);
        }
    }
}
