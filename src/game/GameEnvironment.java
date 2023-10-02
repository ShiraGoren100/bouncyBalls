/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;
import java.util.ArrayList;
import java.util.List;
import collidable.CollisionInfo;
import collidable.Collidable;
import graphics.Point;
import graphics.Line;
import moveable.Paddle;

/**
 *  This is a class representing a gameEnvironment for the bouncing balls.
 * @author Shira Goren.
 * @version 2
 * @since 2021-04-16
 */
public class GameEnvironment {

    /**This var is a list of all collidables in game.*/
    private final List<Collidable> allCollidables;
    private Paddle paddle;

    /**
     * this constructor constructs a game environment.
     */
    public GameEnvironment() {

        /*initialize the collidable arrayList.*/
        this.allCollidables = new ArrayList<Collidable>();
    }

    /**
     * This method sets paddle of the game.
     * @param p - paddle of environment.
     * */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * This method returns all collidables of game.
     * @return collidables in environment of game.
     * */
    public List<Collidable> getAllCollidables() {
        return allCollidables;
    }

    /**
     * this method returns closest collision to start of line.
     * @param trajectory - line to find collisions with.
     * @param expectedCollisions  - array of collisions of witch
     *                           to find closest to start of line given.
     * @return closest collision to start of line.
     */
    public CollisionInfo findClosestCollision(Line trajectory,
                                      List<CollisionInfo> expectedCollisions) {

        /*return null if there are no collisions expected.*/
        if (expectedCollisions == null) {
            return null;
        }

        /*create closest collision var.*/
        CollisionInfo closestCollide = null;

        /*return the closest collision to start of line given.*/
        for (CollisionInfo intersect: expectedCollisions) {

            /*
             * if closest collision is null- then this is
             * the first point we are checking.
             * */
            if (closestCollide == null) {
                closestCollide = intersect;
            } else if (trajectory.start().distance(intersect.collisionPoint())
                    < trajectory.start().distance(
                            closestCollide.collisionPoint())) {

                /*
                 * if the new intersect point is closer to
                 *  start of line than previously saved closest
                 *  point- save this intersect  as the
                 *  closest collision.
                 * */
                closestCollide = intersect;
            }

            /*otherwise- don't change closest collision.*/
        }
        return closestCollide;
    }


    /**
     * this method adds a collidable object to the game.
     * @param c - collidable object to add to game.
     */
    public void addCollidable(Collidable c) {
        this.allCollidables.add(c);
    }

    /**
     * this method finds closest collision of line with collidables of game.
     * <p>
     *     This method runs through all collidable objects
     *     in game and finds closest collision with line.
     *     Then out of all expected collisions - returns
     *     the closest collision to start of line.
     * </p>
     * @param trajectory  - line of witch to find collisions with.
     * @return closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        /*
         * create list of collisions expected between
         * line and all collidable objects.
         * */
        List<CollisionInfo> expectedCollisions = new ArrayList<CollisionInfo>();

        /*run through all collidable objects and save closest collisions.*/
        for (Collidable collidableObject : this.allCollidables) {
            Point collision
                    = trajectory.closestIntersectionToStartOfLine(
                            collidableObject.getCollisionRectangle());

            /*save collision if it happened.*/
            if (collision != null) {
                expectedCollisions.add(
                        new CollisionInfo(collision, collidableObject));
            }
        }

        /*return closest collision to start of trajectory.*/
        return this.findClosestCollision(trajectory, expectedCollisions);
    }

    /**
     * This method checks if point is in the games paddle.
     * @param p - point to check.
     * @return true if is in paddle, false otherwise.
     * */
    public boolean paddleOverRide(Point p) {

        /*check if paddle was set*/
        if (this.paddle == null) {
            return false;
        }

        return (this.paddle.getCollisionRectangle().isInRectangle(p));
    }

}
