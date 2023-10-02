/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package graphics;

/**
 *  This is a class representing a point on a coordinate system.
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-16
 */
public class Point {
    public static final int TWO = 2;
    public static final int TEN = 10;
    public static final int NEGATIVE_TEN = -10;

    /** These vars are the x and y coordinates of the point.*/
    private final double x;
    private final double y;

    /**
     * this constructor constructs a points coordinates.
     * <p>
     *       This function assigns values of x and y.
     * </p>
     * @param x - the coordinate on the x scale.
     * @param y - the coordinate in the y scale.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns x coordinate of point.
     * @return x - coordinate of point on x axis
     */
    public double getX() {
        return this.x;
    }

    /**
     * This method returns y coordinate of point.
     * @return y - coordinate of point on y axis
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method represents the differance to other points.
     * <p>
     *       This function calculates the distance to another
     *       point by using the distance equation-
     *       sqrt((x1-x2)^2 + (y1-y2)^2).
     *       Resulting in returning the distance calculated.
     * </p>
     * @param other - type Point, of witch wanted the distance from.
     * @return square root of sum, witch is the distance from this
     *         point to other point given.
     */
    public double distance(Point other) {
        // calculate the x and y variables of the equation.
        double xVar = Math.pow((this.x - other.x), TWO);
        double yVar = Math.pow((this.y - other.y), TWO);
        // add xVar and yVar of the equation.
        double sum = xVar + yVar;
        // return square root of calculation.
        return Math.sqrt(sum);
    }

    /**
     * this method checks if two doubles are in
     * range of small epsilon of each other.
     * @param xValue - first double.
     * @param yValue - second double.
     * @return true- if is in range, false otherwise.
     */
    public boolean isInRangeOfEpsilon(double xValue, double yValue) {
        double epsilon = Math.pow(TEN, NEGATIVE_TEN);
        return Math.abs(xValue - yValue) < epsilon;
    }

    /**
     * This method checks if point given is equal to this point.
     * <p>
     *       This function checks if this points x and y coordinates
     *       are equal to the x and y coordinates of other point
     *       given. If so- returns true. otherwise- returns false
     * </p>
     * @param other - type Point, of witch wanted to check if
     *              is the same as this point.
     * @return true -if are the same point
     *         false- otherwise.
     */
    public boolean equals(Point other) {
        return isInRangeOfEpsilon(this.getX(), other.getX())
                && isInRangeOfEpsilon(this.getY(), other.getY());
    }
}
