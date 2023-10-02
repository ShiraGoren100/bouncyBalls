/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package moveable;

import biuoop.DrawSurface;
import game.GameLevel;
import general.ScreenCoordinates;
import graphics.Point;
import graphics.Line;
import game.GameEnvironment;
import collidable.CollisionInfo;
import collidable.Collidable;
import java.awt.Color;

/**
 * This is a class representing a ball.
 *
 * @author Shira Goren.
 * @version 3
 * @since 2021-04-16
 */
public class Ball implements Sprite {

    /*these vars represent the limits of the screen in witch the ball can move.*/
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 1000;
    public static final int START = 1;
    public static final double EPSILON = 1;

    /**
     * these vars represent the properties of the ball:
     * the canter point, radius, velocity and movement barriers on screen.
     */
    private Point point;
    private final int r;
    private final java.awt.Color color;
    private java.awt.Color outerColor;
    private Velocity velocity;
    private ScreenCoordinates screen;
    private GameEnvironment game;


    /**
     * this constructor creates a ball object from a point, radius and color.
     *
     * @param center - center of type Point representing
     *               the center of the ball.
     * @param r      - radius of the ball.
     * @param color  - color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.r = r;
        this.color = color;
        this.outerColor = Color.BLACK;
        this.velocity = new Velocity(0, 0);

        /*
         * ball is set with automatic screen movement
         *  obligations that can later be set differently
         * */
        this.screen = new ScreenCoordinates(SCREEN_WIDTH, SCREEN_HEIGHT, START);
        this.game = new GameEnvironment();
    }

    /**
     * this constructor creates a ball object from three integers and a color.
     *
     * @param x     - integer of x value of canter point.
     * @param y     - integer of y value of canter point.
     * @param r     - radius of the ball.
     * @param color - color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
        this.outerColor = Color.BLACK;

        /*
         * ball is set with automatic screen movement
         * obligations that can later be set differently
         * */
        this.screen = new ScreenCoordinates(SCREEN_WIDTH, SCREEN_HEIGHT, START);
        this.game = new GameEnvironment();
    }

    /**
     * this method returns x value of center point of ball.
     *
     * @return x - x value of center point of ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * this method returns y value of center point of ball.
     *
     * @return y - y value of center point of ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * this method returns center point of ball.
     *
     * @return center point of ball.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * this method returns radius value of  ball.
     *
     * @return r - radius value of  ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * this method returns screen height bounder of ball.
     *
     * @return screenHeight - height bounder of ball.
     */
    public int getScreenHeight() {
        return this.screen.getScreenHeight();
    }

    /**
     * this method returns screen width bounder of ball.
     *
     * @return screenWidth - width bounder of ball.
     */
    public int getScreenWidth() {
        return this.screen.getScreenWidth();
    }

    /**
     * this method returns screen start point bounder of ball.
     *
     * @return start - start point bounder of ball.
     */
    public int getStart() {
        return this.screen.getStart();
    }

    /**
     * this method returns color of ball.
     *
     * @return color - color of ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method returns outer color of ball.
     *
     * @return color - outer color of ball.
     */
    public java.awt.Color getOuterColor() {
        return this.outerColor;
    }

    /**
     * this method returns velocity of ball.
     *
     * @return velocity - velocity of ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this method sets new screen boundaries for ball.
     *
     * @param width      - new width boundary.
     * @param height     - new height boundary.
     * @param startPoint - new start point boundary.
     */
    public void setScreen(int width, int height, int startPoint) {
        this.screen = new ScreenCoordinates(width, height, startPoint);
    }

    /**
     * this method sets center point of ball.
     *
     * @param p - point to set center of ball to be.
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * this method sets ball's velocity by velocity object given.
     *
     * @param v - object type Velocity of ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this method sets ball's velocity by two doubles given.
     *
     * @param dx - double of x difference.
     * @param dy - double of y difference.
     */
    public void setVelocity(double dx, double dy) {

        /*creates new velocity object with parameters given.*/
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method sets new game environment for ball.
     *
     * @param g - game environment for ball.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.game = g;
    }

    /**
     * this method sets color for outer part of ball.
     *
     * @param c - outer color for ball.
     */
    public void setOuterColor(Color c) {
        this.outerColor = c;
    }

    /**
     * this method draws ball on surface given.
     *
     * @param surface -drawSurface to draw ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(this.outerColor);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * This method moves ball as time passes.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * this method calculates line of movement from current center of ball
     * to center after movement.
     *
     * @return new Line calculated.
     */
    public Line computeLineTrajectory() {
        return new Line(this.point, this.velocity.applyToPoint(this.point));
    }

    /**
     * this method changes ball's velocity if will go out of screen boundaries.
     * <p>
     * If ball hits screen top or bottom - change dy
     * and if ball hits right or left of screen -
     * change dx.
     * </p>
     */
    public void changeDirectionBasedOnScreen() {
        /*
         * check if change of center will be out of
         *  boundaries of ball's screen. First check x coordinate,
         *  then y coordinate.
         * */
        if ((this.point.getX() + this.getVelocity().getDx() + this.r)
                > this.screen.getScreenWidth()
                || ((this.point.getX() + this.getVelocity().getDx() - this.r)
                < this.screen.getStart())) {
            this.setVelocity(-(this.getVelocity().getDx()),
                    this.getVelocity().getDy());
        }

        /*check y coordinates.*/
        if (this.point.getY() + this.getVelocity().getDy() + this.r
                > this.screen.getScreenHeight()
                || this.point.getY() + this.getVelocity().getDy() - this.r
                < this.screen.getStart()) {
            this.setVelocity(this.getVelocity().getDx(),
                    -(this.getVelocity().getDy()));

        }
    }

    /**
     * this method changes ball's velocity if will collide with object.
     * <p>
     * If ball will collide with block, move ball
     * till right before collision, then change
     * ball's velocity.
     * </p>
     */
    public void moveBallTillCollision() {
        Line trajectory = this.computeLineTrajectory();
        CollisionInfo collision = this.game.getClosestCollision(trajectory);

        /*
         * change velocity as long as the new
         * velocity will cause a collision.
         * */
        while (collision != null) {

            /*
             * if balls center is in the paddle - leave loop.
             * since that means paddle over rid the ball.
             * */
            if (this.game.paddleOverRide(this.getPoint())) {
                return;
            }

            /*change velocity after collision.*/
            this.setVelocity(
                    ((Collidable) collision.collisionObject(
                    )).hit(this, collision.collisionPoint(), this.getVelocity()));

            /*get new trajectory and collision point*/
            trajectory = this.computeLineTrajectory();
            collision = this.game.getClosestCollision(trajectory);
        }

        /*change ball center one step based on new velocity.*/
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * this method moves ball on screen one step.
     * <p>
     * This method moves center of ball one step
     * based on it's velocity. If moving ball will
     * be out of boundaries- changes velocity to
     * move in opposite direction.
     * </p>
     */
    public void moveOneStep() {

        /*
         * check if ball collides with collidables,
         * move ball and change velocity
         * if necessary.
         * */
        this.moveBallTillCollision();
    }

    /**
     * This method adds ball to game as a sprite.
     *
     * @param g - game to add ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * This method removes this ball from given game.
     *
     * @param g game to remove block from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}