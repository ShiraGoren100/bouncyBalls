/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;
import graphics.Point;

/**
 *  This is a class representing the collision info of collidable objects.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class CollisionInfo {

    /**these vars hold the collidable object, the
     * point at which the collision occurs, and the velocity
     * at witch collision happened.
     * */
    private final Point collidableCollisionPoint;
    private final Collidable collidableObject;

    /**
     * this constructor constructs a collision.
     * <p>
     *       This function assigns values of collision point
     *       and collidable object eith witch collision took
     *       place.
     * </p>
     * @param collision - collision point.
     * @param collidedWith - collidable object with witch
     *                     collision took place.
     */
    public CollisionInfo(Point collision, Collidable collidedWith) {
        this.collidableCollisionPoint = collision;
        this.collidableObject = collidedWith;
    }

    /**
     * this method returns the collision point.
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collidableCollisionPoint;
    }

    /**
     * this method returns the collidable object
     * with witch the collision happened.
     * @return collidable object.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
