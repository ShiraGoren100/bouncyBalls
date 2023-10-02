/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package graphics;

/**
 * This is a class representing a straight equation.
 *
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-16
 */
public class StraightEquation {
    public static final int PARALLEL_SLOPE = 0;
    public static final int ZERO = 0;
    public static final int TEN = 10;
    public static final int NEGATIVE_FIFTEEN = -15;
    public static final double EPSILON = Math.pow(TEN, -10);

    /*
     * These variables represent the slope, the extra
     * variable in the straight equation, and a  flag
     * marking if this equation is parallel to the Y
     * axis.
     * */
    private final double slope;
    private final double nVariable;
    private final double x;
    private final boolean isParallelToY;
    private final boolean isOnePoint;

    /**
     * this function constructs a line straight equation from a line.
     * <p>
     * This function constructs a straight equation
     * using the two points in the Line given, based on
     * the simple Y = mX + n equation.
     * </p>
     *
     * @param line - line to create a straight equation for.
     */
    public StraightEquation(Line line) {

        /*finding the slope based off the equation (Y1-Y2)/(X1-X2)*/
        double numerator = line.end().getY() - line.start().getY();
        double denominator = line.end().getX() - line.start().getX();

        /*before the division- check if the denominator is zero*/
        if (denominator == 0) {

            /*if line is only a point.*/
            if (numerator == 0) {
                this.isOnePoint = true;
                this.isParallelToY = false;
                this.nVariable = line.start().getY();
                this.slope = numerator;
                this.x = line.start().getX();
                return;
            } else {

                /*
                 * assign flag to show equation is parallel to Y axis
                 * if x is same at start and end of line.*/
                this.slope = PARALLEL_SLOPE;
                this.isOnePoint = false;
                if (line.start().getX() == line.end().getX()) {
                    this.isParallelToY = true;
                } else {
                    this.isParallelToY = false;
                }
            }

        } else {

            /*assign slope and parallel flag to not parallel.*/
            this.slope = numerator / denominator;
            this.isParallelToY = false;
            this.isOnePoint = false;
        }
        if (this.isParallelToY) {

            /*if equation is parallel assign x value to private nVariable.*/
            this.nVariable = line.end().getX();
        } else {

            /*get the nVariable based on the simple equation n = Y - mX*/
            this.nVariable = line.end().getY()
                    - (this.slope * line.end().getX());
        }
        this.x = ZERO;

    }

    /**
     * this method checks if two doubles are in
     * range of small epsilon of each other.
     *
     * @param xValue - first double.
     * @param yValue - second double.
     * @return true- if is in range, false otherwise.
     */
    public boolean isInRangeOfEpsilon(double xValue, double yValue) {
        double epsilon = Math.pow(TEN, NEGATIVE_FIFTEEN);
        return Math.abs(xValue - yValue) < epsilon;
    }

    /**
     * this method returns the equation's slope.
     *
     * @return slope of equation.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * this method returns the equation's nVariable.
     *
     * @return nVariable.
     */
    public double getNVariable() {
        return this.nVariable;
    }

    /**
     * this method returns the isParallelToY flag.
     *
     * @return isParallelToY
     */
    public boolean getIsParallelToY() {
        return this.isParallelToY;
    }

    /**
     * this method returns if line equation is for one point only.
     *
     * @return isOnePoint
     */
    public boolean getIsOnePoint() {
        return this.isOnePoint;
    }

    /**
     * this method finds point in equation based on the x coordinate.
     * <p>
     * This method assigns the x coordinate given in the
     * straight equation to find the y coordinate. Then returns
     * a new point with these coordinates.
     * </p>
     *
     * @param xValue - double of x coordinate af point we are looking for.
     * @return the new point found, null if can't find one point
     */
    public Point findPointByX(double xValue) {

        /*if cannot find one y for the point*/
        if (this.isParallelToY) {
            return null;
        }
        double y = (this.slope * xValue) + this.nVariable;
        return new Point(xValue, y);
    }

    /**
     * this method finds point in equation based on the y coordinate.
     * <p>
     * This method assigns the y coordinate given in the
     * straight equation to find the x coordinate. Then returns
     * a new point with these coordinates.
     * </p>
     *
     * @param yValue - double of x coordinate af point we are looking for.
     * @return the new point found, null if not able to find
     */
    public Point findPointByY(double yValue) {
        double xVal;

        /*
         * check if slope is zero and therefore equation is parallel,
         * before dividing by it.
         * */
        if (this.slope == PARALLEL_SLOPE) {

            /*
             * if equation is parallel, x value is always the same,
             * and is equal to nVariable.
             */
            if (this.isParallelToY) {
                xVal = nVariable;
            } else if (this.isOnePoint) {
                xVal = this.x;
            } else {

                /*
                 * in case equation is parallel to x axis,
                 * and so has infinite answers.
                 */
                return null;
            }
        } else {

            /*find x coordinate based on equation X = (Y - n)/m */
            xVal = (yValue - nVariable) / this.slope;
        }
        return new Point(xVal, yValue);
    }

    /**
     * this method checks if point given is in the straight line.
     * <p>
     * This method places either y value or x value of
     * point given in equation, finds the missing value,
     * and checks if point received from equation is
     * equal to point given.
     * </p>
     *
     * @param p - point to check if is in straight line
     * @return true if point exists in equation, false otherwise.
     */
    public boolean pointInEquation(Point p) {

        /*
         * check if point given is equal to point gotten from
         * equation with the same x coordinate as p
         * */
        if (p == null) {
            return false;
        }

        /*
         * if eq. is parallel to Y axis, place y value of point
         *  in equation. Otherwise- place x value.
         * */
        if (this.isParallelToY) {
            return p.equals(this.findPointByY(p.getY()));
        }
        return p.equals(this.findPointByX(p.getX()));
    }

    /**
     * this method checks if this straight equation intersects with
     * other straight equation given.
     *
     * @param other - other straight equation to check for intersection
     *              with.
     * @return true if intersection exists, false otherwise.
     */
    public boolean doIntersect(StraightEquation other) {

        /*if each line is parallel to a different axis.*/
        if ((this.isParallelToY && !other.getIsParallelToY())
                || (!this.isParallelToY && other.getIsParallelToY())) {
            return true;
        }

        /*if one line is a point.*/
        if (this.isOnePoint || other.isOnePoint) {
            if (this.isOnePoint && other.isOnePoint) {
                return this.findPointByX(this.x).equals(
                        other.findPointByX(other.x));
            } else if (this.isOnePoint) {
                return this.findPointByX(this.x).equals(
                        other.findPointByX(this.x));
            } else {
                return other.findPointByX(other.x).equals(
                        this.findPointByX(other.x));
            }
        }

        /*
         * intersection will exist as long as the slopes of
         * the two equations are not equal, unless the lines are
         * completely identical.
         * */
        return !isInRangeOfEpsilon(this.getSlope(), other.getSlope())
                || this.equals(other);
    }

    /**
     * this method finds point of intersection of two equations.
     *
     * @param other - other equation to find intersect point with.
     * @return the new point found, or null if intersect doesn't exist.
     */
    public Point intersectionWith(StraightEquation other) {
        double xVal;

        /*check if intersection exists with class method doIntersect*/
        if (this.doIntersect(other)) {

            /*if both equations are the same*/
            if (this.equals(other)) {

                /*check if lines are just a point each.*/
                if (this.getIsOnePoint() && other.getIsOnePoint()) {
                    return new Point(this.x, this.nVariable);
                }
                return null;
            }

            /*
             * if one equation is parallel to the y axis
             * then we already checked and the other equation is not
             * parallel to the y axis. There for we find the point
             * by the x coordinate stored in the parallel equation's
             * nVariable, using the non parallel equation.
             * */
            if (this.isParallelToY) {
                return other.findPointByX(this.nVariable);
            } else if (other.isParallelToY) {
                return this.findPointByX(other.getNVariable());
            }

            /*
             * if both equations are not parallel to the y axis,
             * find x coordinate of intersection by comparing
             * the two equations, or if equation is one point- use
             * the x value of the point.
             * */
            if (this.isOnePoint) {
                xVal = this.x;
            } else if (other.isOnePoint) {
                xVal = other.x;
            } else {
                xVal = (other.nVariable - this.nVariable)
                        / (this.slope - other.slope);
            }

            /*
             * return intersection point
             * by using method to find point based on
             * x coordinates
             * */
            return this.findPointByX(xVal);
        } else {

            /*if intersection doesn't exist return null.*/
            return null;
        }
    }

    /**
     * this method checks if this straight equation is the same as
     * other equation given.
     *
     * @param other - other straight equation to check for intersection
     *              with.
     * @return true if both equations are the same, false otherwise.
     */
    public boolean equals(StraightEquation other) {
        return this.getIsParallelToY() == other.getIsParallelToY()
                && isInRangeOfEpsilon(this.getSlope(), other.getSlope())
                && isInRangeOfEpsilon(this.getNVariable(), other.getNVariable())
                && this.getIsOnePoint() == other.getIsOnePoint();
    }

    /**
     * this method checks if p3 is in between p1 and p2 based on the
     * straight's equation.
     *
     * @param p1 - first range point.
     * @param p2 - second range point.
     * @param p3 - point to see if is in range of p1 and p2.
     * @return true if p3 is in between p1 and p2 on this
     * straight equation, false otherwise.
     */
    public boolean isPointInRange(Point p1, Point p2, Point p3) {
        double xMin = Math.min(p1.getX(), p2.getX());
        double xMax = Math.max(p1.getX(), p2.getX());
        double yMin = Math.min(p1.getY(), p2.getY());
        double yMax = Math.max(p1.getY(), p2.getY());

        /*return if p3 is in range.*/
        return (p3.getX() >= (xMin - EPSILON) && p3.getX() <= (xMax + EPSILON)
                && p3.getY() >= (yMin - EPSILON) && p3.getY()
                <= (yMax + EPSILON));
    }
}
