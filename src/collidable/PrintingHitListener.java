/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;
import moveable.Block;
import moveable.Ball;

/**
 *  This is a class representing a hit listener printer.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class PrintingHitListener implements HitListener {
    private int counter = 1;

    /**
     * This method prints hit.
     * @param beingHit block that was hit.
     * @param hitter ball that hit block.
     * */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit." + this.counter);
        this.counter++;
    }
}
