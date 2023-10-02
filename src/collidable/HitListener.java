/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;
import moveable.Block;
import moveable.Ball;

/**
 *  This is an interface representing things that are notified of hits.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit..
     * @param beingHit block that was hit.
     * @param hitter is the Ball that's doing the hitting.
     * */
    void hitEvent(Block beingHit, Ball hitter);
}
