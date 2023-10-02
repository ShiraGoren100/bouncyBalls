/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;
import graphics.Rectangle;
import graphics.Point;
import moveable.Velocity;
import moveable.Ball;

/**
 *  This is an interface representing collidable things.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public interface Collidable {

    /**
     * This method will return the "collision shape" of the object.
     * @return rectangle shape.
     * */
    Rectangle getCollisionRectangle();

    /**
     * This method will notify the object that we collided with it
     * at collisionPoint with a given velocity.
     * @param hitter ball that hit block.
     * @param collisionPoint - point of collision.
     * @param currentVelocity - current velocity of moving object.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}