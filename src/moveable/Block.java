/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package moveable;

import biuoop.DrawSurface;
import collidable.HitListener;
import graphics.Point;
import graphics.Rectangle;
import graphics.StraightEquation;
import collidable.CollisionInfo;
import collidable.Collidable;
import game.GameLevel;
import collidable.HitNotifier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class implementing the Collidable interface.
 * It represents a block that is collidable.
 *
 * @author Shira Goren.
 * @version 2
 * @since 2021-04-16
 */
public class Block implements Collidable, Sprite, HitNotifier {

    /*these are default sizes*/
    public static final double SIZE = 5;

    /**
     * These vars are a rectangle that is collidable,
     * and a CollisionInfo object was collided with.
     */
    private final Rectangle rect;
    private CollisionInfo collisionOccurred;
    private List<HitListener> hitListeners;

    /**
     * this constructor constructs a rectangle from a point and sizes.
     *
     * @param start  - upper left point of rectangle.
     * @param width  - rectangle width.
     * @param height - rectangle height.
     */
    public Block(Point start, double width, double height) {
        this.rect = new Rectangle(start, width, height);
        this.collisionOccurred = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * this constructor constructs a block from a rectangle.
     *
     * @param r - rectangle to add as a block
     */
    public Block(Rectangle r) {
        this.rect = r;
        this.collisionOccurred = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * this constructor constructs a default rectangle.
     */
    public Block() {
        this.rect = new Rectangle(new Point(0, 0), SIZE, SIZE);
        this.collisionOccurred = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * This method will return the "collision shape" of the object.
     *
     * @return rectangle shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method sets the block's color.
     *
     * @param color - color to change rect to.
     */
    public void setColor(java.awt.Color color) {
        this.rect.setColor(color);
    }

    /**
     * This method gets the block's color.
     * @return color of block
     */
    public Color getColor() {
        return this.rect.getColor();
    }

    /**
     * This method will checks if intersection point is vertical.
     * <p>
     * check if intersecting happened on top or bottom of rectangle,
     * since that means intersection was vertical.
     * </p>
     *
     * @param collisionPoint - point of collision.
     * @return true - if intersection is vertical, false otherwise.
     */
    public boolean isIntersectVertical(Point collisionPoint) {

        /*create rectangle line equations.*/
        StraightEquation topEQ = new StraightEquation(this.rect.getTopLine());
        StraightEquation bottomEQ
                = new StraightEquation(this.rect.getBottomLine());

        /*
         * return true if intersection point is on
         * top or bottom of rectangle, since that
         *  means collision was vertical.
         * */
        return topEQ.pointInEquation(collisionPoint)
                || bottomEQ.pointInEquation(collisionPoint);
    }

    /**
     * This method will checks if intersection point is horizontal.
     * <p>
     * check if intersecting happened on right or left of rectangle,
     * since that means intersection was horizontal.
     * </p>
     *
     * @param collisionPoint - point of collision.
     * @return true - if intersection is Horizontal, false otherwise.
     */
    public boolean isIntersectHorizontal(Point collisionPoint) {

        /*create rectangle line equations.*/
        StraightEquation rightEQ = new StraightEquation(this.rect.getRightLine());
        StraightEquation leftEQ
                = new StraightEquation(this.rect.getLeftLine());

        /*
         * return true if intersection point is on
         * right or left of rectangle, since that
         *  means collision was horizontal.
         * */
        return rightEQ.pointInEquation(collisionPoint)
                || leftEQ.pointInEquation(collisionPoint);
    }

    /**
     * This method will notify the object that we collided with it
     * at collisionPoint with a given velocity.
     *
     * @param hitter ball that hit.
     * @param collisionPoint  - point of collision.
     * @param currentVelocity - current velocity of moving object.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /*create new velocity*/
        Velocity newVelocity = currentVelocity;

        /*notify object of collision.*/
        this.collisionOccurred = new CollisionInfo(collisionPoint, this);

        /*
         * check on witch line the collision accord.
         * If collided vertically- change dx, if
         * collided horizontally- change dy.*/
        if (this.isIntersectVertical(collisionPoint)) {
            newVelocity = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (this.isIntersectHorizontal(collisionPoint)) {
            newVelocity = new Velocity(-newVelocity.getDx(),
                    newVelocity.getDy());
        }
        // notify that this is the hitting ball
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * this method draws block on surface given.
     *
     * @param d -drawSurface to draw ball on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        this.rect.drawBorders(d);
    }

    /**
     * This method does nothing as time passes.
     */
    @Override
    public void timePassed() {

    }

    /**
     * This method adds block to game as a sprite and as a collidable.
     *
     * @param g - game to add ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * This method removes this block from given gameLevel.
     *
     * @param gameLevel gameLevel to remove block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * This method adds a hot listener to list.
     * @param hl hit listener to add.
     * */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes a hit listener from list.
     * @param hl hit listener to remove.
     * */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notifies all hit listeners that ball given hot htis block.
     * @param hitter the ball that hit this block.
     * */
    private void notifyHit(Ball hitter) {

        /* Make a copy of the hitListeners before iterating over them.*/
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        /*Notify all listeners about a hit event:*/
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
