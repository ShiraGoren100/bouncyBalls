/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package graphics;

import biuoop.DrawSurface;
import moveable.Sprite;
import java.awt.Color;

/**
 *  This is a class representing a line on a coordinate system.
 * @author Shira Goren.
 * @version 4
 * @since 2021-06-16
 */
public class Line implements Sprite {
    public static final int DIVIDER = 2;

    /** These vars are the start and end points of the Line.*/
    private final Point start;
    private final Point end;
    private  Color color;

    /**
     * this function constructs a line from two points.
     * <p>
     *       This function constructs a line based on a start
     *       and end point.
     * </p>
     * @param start - point of start of line
     * @param end - point of end of line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * this function constructs a line from 4 numbers.
     * <p>
     *       This function constructs a line by creating two Points
     *       from the 4 numbers given.
     * </p>
     * @param x1 -x coordinate for start Point.
     * @param y1 - y coordinate for start Point.
     * @param x2 - x coordinate for end Point.
     * @param y2 - y coordinate for end Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method returns the start point of the line.
     * @return start Point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * this method returns the end point of the line.
     * @return end Point of this line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * this method returns the length of the line.
     * <p>
     *       This function calls function to find distance between
     *       start and end points, which is the length of the line.
     * </p>
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method creates middle point on line.
     * <p>
     *       This function finds the middle point of the line by
     *       adding the x coordinates of start and end
     *       points and, dividing them by two, Doing the same
     *       for the y coordinates.
     * </p>
     * @return a new point created, representing the middle
     *         of the line.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / DIVIDER;
        double middleY = (this.start.getY() + this.end.getY()) / DIVIDER;
        return new Point(middleX, middleY);
    }

    /**
     * this method checks if other line is equal to this line.
     * @param other - other line to chek if is equal with this
     *              line.
     * @return true if lines are equal, false otherwise.
     */
    public boolean equals(Line other) {

        /*
         * return true if both start points and end points are equal
         * or if the start of this line is equal to the end of the
         * other line and the end of this line is equal to the start of
         * the other line.
         * */
        return (this.start.equals(other.start)
                && this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start));
    }

    /**
     * this method checks if line given intersects with this line.
     * <p>
     *       This function finds the two lines equations by using
     *       StraightEquation class, finds out if their equations
     *       have an intersection, and if they do- makes sure it
     *       is in the range of x and y coordinates of start and
     *       end points of Line.
     * </p>
     * @param other - other line to check if intersects with this
     *              line.
     * @return true if there is an intersection, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        /*Create the equations of both lines using StraightEquation class*/
        StraightEquation thisLineEq = new StraightEquation(this);
        StraightEquation otherLineEq = new StraightEquation(other);

        /*Find intersect point of the two equations*/
        Point intersect1 = thisLineEq.intersectionWith(otherLineEq);
        Point intersect2 = otherLineEq.intersectionWith(thisLineEq);

        /* return false if lines don't intersect at all*/
        if (!thisLineEq.doIntersect(otherLineEq)) {
            return false;
        } else if (intersect1 == null && intersect2 == null) {

            /*
             * this is the case of the same line equation.
             * checking if the lines have intersection within
             * their limits of start and end.
             * */
            return (thisLineEq.isPointInRange(
                            this.start, this.end, other.start)
                    || thisLineEq.isPointInRange(
                            this.start, this.end, other.end()));

        } else {

            /*
             * both intersects should have same value, in range of
             * a small epsilon, so intersect1 should never be
             * null at this point.
             * */
            assert intersect1 != null;

            /*
             * return if the intersect of both line equations
             *  is in range of the lines
             * */
            return (thisLineEq.isPointInRange(this.start, this.end, intersect1)
                    && otherLineEq.isPointInRange(
                            other.start(), other.end(), intersect2));
        }
    }

    /**
     * this method returns intersect if lines  only have one intersect.
     * @param thisLine - this line's equation
     * @param otherLine - other line's equation.
     * @param other - other line to find intersect with this
     *              line.
     * @return intersection point if exists, null otherwise.
     */
    public Point checkIfOnlyOneIntersect(StraightEquation thisLine,
                              StraightEquation otherLine, Line other) {

        /*check if lines only share start or end points.*/
        if (this.start().equals(other.start())
                && !thisLine.isPointInRange(
                        this.start(), this.end(), other.end())
                && !otherLine.isPointInRange(
                        other.start(), other.end(), this.end())) {

            /*
             * only start points are equal,
             *  rest of points not in range of
             *  each other.
             * */
            return this.start();
        }

        /*
         * only this start point and other end point are equal,
         *  rest of points not in range of
         *  each other.
         * */
        if (this.start().equals(other.end())
                && !thisLine.isPointInRange(
                        this.start(), this.end(), other.start())
                && !otherLine.isPointInRange(
                        other.start(), other.end(), this.end())) {
            return this.start();
        }

        /*
         * only this end point and other start point are equal,
         *  rest of points not in range of
         *  each other.
         * */
        if (this.end().equals(other.end())
                && !thisLine.isPointInRange(
                        this.start(), this.end(), other.start())
                && !otherLine.isPointInRange(
                        other.start(), other.end(), this.start())) {
            return this.end();
        }

        /*
         * only this end point and other end point are equal,
         *  rest of points not in range of
         *  each other.
         * */
        if (this.end().equals(other.start())
                && !thisLine.isPointInRange(
                        this.start(), this.end(), other.end())
                && !otherLine.isPointInRange(
                        other.start(), other.end(), this.start())) {
            return this.end();
        }
        return null;
    }


    /**
     * this method returns intersect of line given with this line.
     * <p>
     *       This function finds out if the two lines have an
     *       intersection by using isIntersection method of
     *       this class. If intersection exists- returns it.
     *       otherwise returns null.
     * </p>
     * @param other - other line to find intersect with this
     *              line.
     * @return intersection point if exists, null otherwise.
     */
    public Point intersectionWith(Line other) {

        /*return null if intersection doesn't exist*/
        if (!this.isIntersecting(other)) {
            return null;
        }

        /*create the lines equation by using StraightEquation class*/
        StraightEquation thisLineEq = new StraightEquation(this);
        StraightEquation otherLineEq = new StraightEquation(other);

        /*
         * if both lines have same equation- check if they share
         *  more than one point. If meet only
         * in their end or start points- return the shared point.
         * */
        if (thisLineEq.equals(otherLineEq)) {
            return checkIfOnlyOneIntersect(thisLineEq, otherLineEq, other);
        }

        /*
         * otherwise- return the intersection point using the
         * intersection equation method to do so.
         * */
        return thisLineEq.intersectionWith(otherLineEq);
    }

    /**
     * this method returns closest point to start of line.
     * @param pointArray - array of points of witch to find closest to start.
     * @return intersection point closest to start of line.
     * If there are none - returns null.
     */
    public Point findClosestToStart(java.util.List<Point> pointArray) {

        /*return null if there are no points*/
        if (pointArray == null) {
            return null;
        }

        /*create closest point*/
        Point closestToStart = null;

        /*Otherwise- return the closest intersection point to start of line.*/
        for (Point intersect: pointArray) {

            /*
             * if closest point is null- then this is
             * the first point we are checking.
             * */
            if (closestToStart == null) {
                closestToStart = intersect;
            } else if (this.start().distance(intersect)
                    < this.start().distance(closestToStart)) {
                /*
                 * if the new intersect point is closer to
                 *  start of line than previously saved closest
                 *  point- save this intersect poijnt as the
                 *  closest to start.
                 * */
                closestToStart = intersect;
            }

            /*otherwise- don't change closest point.*/
        }
        return closestToStart;
    }

    /**
     * this method returns closest Intersection point to start of line.
     * @param rect - rectangle to find intersection with.
     * @return intersection point closest to start of line.
     * If there are none - returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        /*get list of all intersection points with rectangle.*/
        java.util.List<Point> rectangleIntersections
                = rect.intersectionPoints(this);

        /*return null if there are no intersection points*/
        if (rectangleIntersections.size() == 0) {
            return null;
        }

        /*return closest intersection point to start.*/
        return findClosestToStart(rectangleIntersections);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method returns lines color.
     * @return color of line
     * */
    public Color getColor() {
        return color;
    }

    /**
     * This method sets lines color.
     * @param c color of line
     * */
    public void setColor(Color c) {
        this.color = c;
    }
}
