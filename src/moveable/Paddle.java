/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package moveable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.CollisionInfo;
import collidable.Collidable;
import game.GameLevel;
import graphics.Rectangle;
import graphics.Point;
import graphics.Line;
import graphics.StraightEquation;
import general.ScreenCoordinates;
import java.awt.Color;

/**
 * This is a class representing a moving paddle in the game.
 *
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-16
 */
public class Paddle implements Sprite, Collidable {
    public static final int ONE = 1;
    public static final int NOT_FOUND = -1;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 10;
    public static final int MOVE_ONE_STEP = 20;
    public static final int DEFAULT_HEIGHT = 595;
    public static final int DEFAULT_WIDTH = 795;
    public static final int ZERO = 0;

    /*these vars are the number of regions*/
    public static final int REGION_NUM = 5;
    public static final int FIRST_REGION = 1;
    public static final int SECOND_REGION = 2;
    public static final int THIRD_REGION = 3;
    public static final int FOURTH_REGION = 4;
    public static final int FIFTH_REGION = 5;

    /*these vars are the degrees to add at each region*/
    public static final int FIRST_REGION_DEGREES = 300;
    public static final int SECOND_REGION_DEGREES = 330;
    public static final int FOURTH_REGION_DEGREES = 30;
    public static final int FIFTH_REGION_DEGREES = 60;
    public static final int DEGREES_TO_AD = 90;

    /*these vars represent the side of where collision happened.*/
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;
    public static final int LEFT = 4;

    /**
     * These are the private vars of the class representing the paddle.
     */
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private CollisionInfo collisionOccurred;
    private ScreenCoordinates screen;
    private int speed;
    private Color color;

    /**
     * This constructor creates a default paddle for the game.
     *
     * @param key - keyboard sensor for screen, to use for paddle.
     * @param c color of paddle
     */
    public Paddle(biuoop.KeyboardSensor key, Color c) {
        this.keyboard = key;
        this.rect = new Rectangle(new Point(ZERO, DEFAULT_HEIGHT), WIDTH, HEIGHT);
        this.color = c;
        this.rect.setColor(this.color);
        this.screen = new ScreenCoordinates(DEFAULT_WIDTH, DEFAULT_HEIGHT, ZERO);
        this.speed = MOVE_ONE_STEP;
    }


    /**
     * This function returns screen coordinates of paddle.
     *
     * @return screen coordinates.
     */
    public ScreenCoordinates getScreen() {
        return screen;
    }

    /**
     * This fucntion sets screen coordinates.
     *
     * @param s - screen to set.
     */
    public void setScreen(ScreenCoordinates s) {
        this.screen = s;
    }

    /**
     * This fucntion sets paddle color.
     *
     * @param c - color to apply.
     */
    public void setColor(Color c) {
        this.rect.setColor(c);
    }

    /**
     * This method moves paddle to the left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX()
                - this.speed >= this.screen.getStart()) {
            this.rect = new Rectangle(
                    new Point(this.rect.getUpperLeft().getX()
                            - this.speed, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        } else {

            /*move to end of screen.*/
            this.rect = new Rectangle(new Point(
                    this.screen.getStart(),
                    this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }

        /*set new rectangles color*/
        this.rect.setColor(this.color);
    }

    /**
     * This method moves paddle to the right.
     */
    public void moveRight() {
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth()
                + this.speed <= this.screen.getScreenWidth()) {
            this.rect = new Rectangle(new Point(
                    this.rect.getUpperLeft().getX()
                            + this.speed, this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        } else {

            /*move to end of screen.*/
            this.rect = new Rectangle(new Point(
                    this.screen.getScreenWidth() - this.rect.getWidth(),
                    this.rect.getUpperLeft().getY()),
                    this.rect.getWidth(), this.rect.getHeight());
        }

        /*set new rectangles color*/
        this.rect.setColor(this.color);
    }

    /**
     * This method checks if left and right key's are
     * pressed down and moves paddle accordingly.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * This method draws paddle on screen.
     *
     * @param d - surface to draw paddle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());

        /*draw border of paddle*/
        this.rect.drawBorders(d);
    }

    /**
     * This method returns rectangle of paddle.
     *
     * @return rectangle of paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method will checks if intersection point is vertical.
     * <p>
     * check if intersecting happened on top or bottom of rectangle,
     * since that means intersection was vertical.
     * </p>
     *
     * @param collisionPoint - point of collision.
     * @return TOPe - if intersection is on top side,
     * BOTTOM if intersect was on bottom,
     * and not found otherwise.
     */
    public int checkIfIntersectVertical(Point collisionPoint) {

        /*create rectangle line equations.*/
        StraightEquation topEQ = new StraightEquation(this.rect.getTopLine());
        StraightEquation bottomEQ
                = new StraightEquation(this.rect.getBottomLine());

        /*
         * return true if intersection point is on
         * top or bottom of rectangle, since that
         *  means collision was vertical.
         * */
        if (topEQ.pointInEquation(collisionPoint)) {
            return TOP;
        }
        if (bottomEQ.pointInEquation(collisionPoint)) {
            return BOTTOM;
        }
        return NOT_FOUND;
    }

    /**
     * This method will checks if intersection point is on right or left.
     * <p>
     * check if intersecting happened on  of rectangle,
     * since that means intersection was horizontal.
     * </p>
     *
     * @param collisionPoint - point of collision.
     * @return RIGHT- if intersection is on right side, LEFT if on left side.
     * otherwise - NOT_FOUND.
     */
    public int checkIfIntersectHorizontal(Point collisionPoint) {

        /*create rectangle line equations.*/
        StraightEquation rightEQ = new StraightEquation(this.rect.getRightLine());
        StraightEquation leftEQ
                = new StraightEquation(this.rect.getLeftLine());

        /*
         * return true if intersection point is on
         * right or left of rectangle, since that
         *  means collision was horizontal.
         * */
        if (rightEQ.pointInEquation(collisionPoint)) {
            return RIGHT;
        }
        if (leftEQ.pointInEquation(collisionPoint)) {
            return LEFT;
        }
        return NOT_FOUND;
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
     * This method finds region of where point hit paddle.
     *
     * @param paddleSide - horizontal line that was hit.
     * @param collision  - collision point.
     * @param length     - length of paddle side.
     * @return i - region of collision. or -1 if not found.
     */
    public int findHorizontalRegionOfCollision(Line paddleSide, Point collision,
                                               int length) {
        StraightEquation sideEQ = new StraightEquation(paddleSide);
        for (int i = ONE; i <= REGION_NUM; i++) {
            if (sideEQ.isPointInRange(paddleSide.start(),
                    sideEQ.findPointByX(((int)
                            (i * length / REGION_NUM))
                            + paddleSide.start().getX()), collision)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method finds region of where point hit paddle.
     *
     * @param paddleSide - vertical line that was hit.
     * @param collision  - collision point.
     * @param length     - length of paddle side.
     * @return i - region of collision. or -1 if not found.
     */
    public int findVerticalRegionOfCollision(Line paddleSide, Point collision,
                                             int length) {
        StraightEquation sideEQ = new StraightEquation(paddleSide);
        for (int i = ONE; i <= REGION_NUM; i++) {
            if (sideEQ.isPointInRange(paddleSide.start(),
                    sideEQ.findPointByY((int)
                            (i * length / REGION_NUM)
                            + paddleSide.start().getY()), collision)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method calculates the distance point made to collision.
     * <p>
     * This method finds previous point by subtracting
     * the dx and dy values from the collision point
     * and finds the distance point made.
     * </p>
     *
     * @param collision - collision point.
     * @param currentV  - current velocity of point.
     * @return double of the distance made.
     */
    public double findDistancePointMade(Point collision, Velocity currentV) {
        Point previousPoint = new Point(collision.getX() - currentV.getDy(),
                collision.getY() - currentV.getDy());
        return previousPoint.distance(collision);
    }

    /**
     * This method assigns new angle to ball and returns new velocity.
     * <p>
     * This method assigns an angle to ball
     * based on collision region, and returns
     * new velocity based on angle and distance
     * point made to get to collision for horizontal
     * sides of rectangle.
     * </p>
     *
     * @param region          - region of collision.
     * @param currentVelocity - current velocity of point.
     * @param distance        - distance point made to get to collision.
     * @return new velocity calculated for point.
     */
    public Velocity findNewVelocityHorizontal(int region, Velocity currentVelocity,
                                              double distance) {

        /*assign degrees of new velocity based on collision region.*/
        int addDegrees;
        if (region == FIRST_REGION) {
            addDegrees = FIRST_REGION_DEGREES;
        } else if (region == SECOND_REGION) {
            addDegrees = SECOND_REGION_DEGREES;
        } else if (region == THIRD_REGION) {

            /*return velocity with change of dy.*/
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        } else if (region == FOURTH_REGION) {
            addDegrees = FOURTH_REGION_DEGREES;
        } else if (region == FIFTH_REGION) {
            addDegrees = FIFTH_REGION_DEGREES;
        } else {
            /*should never reach*/
            return currentVelocity;
        }

        /*
         * return new velocity based on collision
         * region angle return and speed of point.
         * */
        return Velocity.fromAngleAndSpeed(addDegrees, distance);
    }

    /**
     * This method assigns new angle to ball and returns new velocity.
     * <p>
     * This method assigns an angle to ball
     * based on collision region, and returns
     * new velocity based on angle and distance
     * point made to get to collision for vertical
     * sides of rectangle.
     * </p>
     *
     * @param region   - region of collision.
     * @param currentV - current velocity of point.
     * @param distance - distance point made to get to collision.
     * @return new velocity calculated for point.
     */
    public Velocity findNewVelocityVertical(int region, Velocity currentV,
                                            double distance) {

        /*assign degrees of new velocity based on collision region.*/
        int addDegrees;
        if (region == FIRST_REGION) {
            addDegrees = FIRST_REGION_DEGREES;
        } else if (region == SECOND_REGION) {
            addDegrees = SECOND_REGION_DEGREES;
        } else if (region == THIRD_REGION) {

            /*return velocity with change of dy.*/
            return new Velocity(currentV.getDx(),
                    -currentV.getDy());
        } else if (region == FOURTH_REGION) {
            addDegrees = FOURTH_REGION_DEGREES;
        } else if (region == FIFTH_REGION) {
            addDegrees = FIFTH_REGION_DEGREES;
        } else {
            /*should never reach*/
            return currentV;
        }

        /*add 90 degrees because side is vertical*/
        addDegrees += DEGREES_TO_AD;

        /*
         * return new velocity based on collision
         * region angle return and speed of point.
         * */
        return Velocity.fromAngleAndSpeed(addDegrees, distance);
    }

    /**
     * This method will notify the object that we collided with it
     * at collisionPoint with a given velocity.
     *
     * @param hitter          ball that hit.
     * @param collisionPoint  - point of collision.
     * @param currentVelocity - current velocity of moving object.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        /*create new velocity*/
        Velocity newVelocity = currentVelocity;
        int region = NOT_FOUND;
        double distance;

        /*find where collision occurred*/
        int collidedVertically
                = this.checkIfIntersectVertical(collisionPoint);
        int collidedHorizontally
                = this.checkIfIntersectHorizontal(collisionPoint);

        /*notify object of collision.*/
        this.collisionOccurred
                = new CollisionInfo(collisionPoint, this);

        /*
         * check on witch line the collision accord.
         * If collided vertically- change dx, if
         * collided horizontally- change dy.*/
        if (collidedVertically != NOT_FOUND) {
            if (collidedVertically == TOP) {
                region = this.findHorizontalRegionOfCollision(
                        this.rect.getTopLine(), collisionPoint,
                        (int) this.rect.getWidth());
            } else if (collidedVertically == BOTTOM) {
                region = this.findHorizontalRegionOfCollision(
                        this.rect.getBottomLine(), collisionPoint,
                        (int) this.rect.getWidth());
            }

            /*
             * find distance point should make and
             * calculate new velocity.
             * */
            distance = this.findDistancePointMade(
                    collisionPoint, currentVelocity);
            newVelocity = this.findNewVelocityHorizontal(
                    region, currentVelocity, distance);
        } else if (this.isIntersectHorizontal(collisionPoint)) {
            newVelocity = new Velocity(-newVelocity.getDx(),
                    newVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * This method adds paddle to the game.
     *
     * @param g - game to add paddle to.
     */
    public void addToGame(GameLevel g) {

        /*add paddle as collidable and as sprite.*/
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This method returns the collision info of the paddle.
     *
     * @return collision info.
     */
    public CollisionInfo getCollisionOccurred() {
        return this.collisionOccurred;
    }

    /**
     * This method sets paddle.
     *
     * @param rec - rectangle to set paddle to be.
     */
    public void setPaddle(Rectangle rec) {
        this.rect = rec;
    }

    /**
     * This method sets paddle speed.
     *
     * @param newSpeed - speed of paddle.
     */
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
}
