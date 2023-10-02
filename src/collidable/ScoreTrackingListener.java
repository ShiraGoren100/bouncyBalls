/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass5
 */

package collidable;
import general.Counter;
import moveable.Block;
import moveable.Ball;

/**
 *  This is a class that tracks the score of the game.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class ScoreTrackingListener implements HitListener {

    public static final int ADD_POINTS = 5;

    /**This variable is the score of the game.*/
    private final Counter currentScore;

    /**
     * This constructor saves the score of the game.
     * @param scoreCounter score of game.
     * */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method...
     * @param beingHit block that was hit.
     * @param  hitter ball that hit block.
     * */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(ADD_POINTS);
    }
}
