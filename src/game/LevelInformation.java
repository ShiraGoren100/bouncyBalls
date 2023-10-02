/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import moveable.Block;
import moveable.SpriteCollection;
import moveable.Velocity;
import java.util.List;

/**
 * This is an interface representing a game level.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public interface LevelInformation {

    /**
     * This method holds number of balls for level.
     *
     * @return number of balls in level.
     */
    int numberOfBalls();

    /**
     * This method  returns the initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of velocities for balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * This method returns paddles speed.
     * @return paddle speed
     * */
    int paddleSpeed();

    /**
     * This method returns paddles width.
     * @return paddle width
     * */
    int paddleWidth();

    /**
     * This method  returns the level name, that will be
     * displayed at the top of the screen.
     *
     * @return level name
     */
    String levelName();

    /**
     * This method returns a sprite with the background of the level.
     *
     * @return background sprite
     */
    SpriteCollection getBackground();

    /**
     * This method returns the Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return all blocks of this level
     */
    List<Block> blocks();

    /**
     * This method returns number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
