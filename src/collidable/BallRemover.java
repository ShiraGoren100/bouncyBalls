/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;

import game.GameLevel;
import general.Counter;
import moveable.Ball;
import moveable.Block;

/**
 * This is a class in charge of removing balls from the gameLevel,
 * as well as keeping count of the number of balls that remain.
 *
 * @version 2
 * @since 2021-06-16
 */
public class BallRemover implements HitListener {
    public static final int ONE = 1;

    /**
     * These are the gameLevel and the counter objects.
     */
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * This constructor creates a block remover.
     *
     * @param g                gameLevel.
     * @param remainderOfBalls num of balls remaining.
     */
    public BallRemover(GameLevel g, Counter remainderOfBalls) {
        this.gameLevel = g;
        this.remainingBalls = remainderOfBalls;
    }

    /**
     * This method removes block from gameLevel and updates counter of blocks.
     *
     * @param beingHit block to remove.
     * @param hitter   ball that hit block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        /*remove ball from gameLevel.*/
        hitter.removeFromGame(this.gameLevel);

        /*update counter after removing ball.*/
        this.remainingBalls.decrease(ONE);
    }
}
