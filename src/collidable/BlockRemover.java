/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;
import game.GameLevel;
import moveable.Block;
import moveable.Ball;
import general.Counter;

/**
 *  This is a class in charge of removing blocks from the gameLevel,
 *  as well as keeping count of the number of blocks that remain.
 * @version 2
 * @since 2021-06-16
 */
public class BlockRemover implements HitListener {
    public static final int ONE = 1;

    /**These are the gameLevel and the counter objects.*/
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * This constructor creates a block remover.
     * @param g gameLevel.
     * @param remainderOfBlocks num of blocks remaining.
     * */
    public BlockRemover(GameLevel g, Counter remainderOfBlocks) {
        this.gameLevel = g;
        this.remainingBlocks = remainderOfBlocks;
    }

    /**
     * This method removes block from gameLevel and updates counter of blocks.
     * @param beingHit block to remove.
     * @param hitter ball that hit block.
     * */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);

        /*removing listener from block*/
        beingHit.removeHitListener(this);

        /*update counter after removing block.*/
        this.remainingBlocks.decrease(ONE);
    }
}
