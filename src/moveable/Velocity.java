/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass5
 */

package moveable;
import graphics.Point;

/**
 *  This is a class representing Velocity.
 * @author Shira Goren.
 * @version 2
 * @since 2021-04-1
 */
public class Velocity {
    public static final int POWER = 2;

    /*these vars represent the differance in x and y coordinates of a point.*/
    private final double dx;
    private final double dy;

    /**
     * this constructor creates a velocity object from two doubles.
     * @param dx - double type differance of x coordinate.
     * @param dy - double type differance of y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this method creates a velocity object from two doubles,
     * an angle and speed.
     * @param angle - double type of the angle in witch the point is moving.
     * @param speed - double type of speed in witch the point is moving.
     * @return velocity - the velocity object created from calculating the
     *                     differance in x and y coordinates based on angle
     *                     and speed given.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /*calculating dx and dy coordinate based on the law of sines.*/
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.PI / POWER - Math.toRadians(angle));

        /*
         * change dy to negative value so that
         * animation goes in right direction.
         * */
        return new Velocity(dx, -dy);
    }

    /**
     * this method returns dx value.
     * @return dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * this method returns dy value.
     * @return dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this method returns distance the point is going
     * to move based on the velocity.
     * @return distance - double value of distance to new point.
     */
    public double distance() {

        /*
         * Distance is calculated based on distance equation-
         * (x^2 + y^2) ^0.5.
         * */
        return Math.sqrt((Math.pow(this.dx, POWER))
                + (Math.pow(this.dy, POWER)));
    }

    /**
     * this method applies velocity to point and moves it accordingly.
     * @param p - point to move.
     * @return new point of coordinates after moving p given.
     */
    public Point applyToPoint(Point p) {

        /*
         * return a new point in witch the dx was added
         *  x coordinate of point, and dy was added to y
         * coordinate of point.
         * */
        return new Point(this.dx + p.getX(), this.dy + p.getY());
    }
}
