/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package graphics;
import biuoop.DrawSurface;
import java.util.ArrayList;
import general.Utility;

/**
 *  This is a class representing a rectangle in line with the axes.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class Rectangle {

    /** These vars are the start point, width and height of the rectangle.*/
    private final Point leftUpperStart;
    private final double rectWidth;
    private final double rectHeight;
    private Line top;
    private Line bottom;
    private Line right;
    private Line left;
    private java.awt.Color color;

    /**
     * this constructor constructs a rectangle.
     * <p>
     *       This function assigns values of upper left
     *       point of rectangle, and assigns the width
     *       and height as well.
     * </p>
     * @param upperLeft - upper left point of rectangle.
     * @param width - rectangle width.
     * @param height - rectangle height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.leftUpperStart = upperLeft;
        this.rectWidth = width;
        this.rectHeight = height;
        this.color = Utility.generateRandomColor(null);

        /*create rectangle lines.*/
        createLines();
    }

    /**
     * this method creates rectangle lines.
     * <p>
     *       This function creates new points and uses
     *       them to create the rectangle lines.
     * </p>
     */
    public void createLines() {

        /*create new points of rectangle.*/
         Point upperRight = new Point(this.leftUpperStart.getX()
                 + this.rectWidth, this.leftUpperStart.getY());
         Point bottomLeft = new Point(this.leftUpperStart.getX(),
                 this.leftUpperStart.getY() + this.rectHeight);
         Point bottomRight = new Point(this.leftUpperStart.getX()
                 + this.rectWidth, this.leftUpperStart.getY()
                 + this.rectHeight);

         /*create rectangle lines.*/
        this.top = new Line(this.leftUpperStart, upperRight);
        this.bottom = new Line(bottomLeft, bottomRight);
        this.left = new Line(this.leftUpperStart, bottomLeft);
        this.right = new Line(upperRight, bottomRight);
    }

    /**
     * this method creates a list of intersection points.
     * <p>
     *       This method finds all intersection points
     *       between line given and rectangle, and returns
     *       a list of them.
     * </p>
     * @param line - line to find intersection with rectangle.
     * @return intersectionPoints - list of all intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        /*create intersection point list.*/
        java.util.List<Point> intersectionList = new ArrayList<Point>();

        /*find intersection points with rectangle.*/
        Point intersectTop = line.intersectionWith(this.top);
        Point intersectBottom = line.intersectionWith(this.bottom);
        Point intersectRight = line.intersectionWith(this.right);
        Point intersectLeft = line.intersectionWith(this.left);

        /*add intersection points if are not null.*/
        if (intersectTop != null) {
            intersectionList.add(intersectTop);
        }
        if (intersectBottom != null) {
            intersectionList.add(intersectBottom);
        }
        if (intersectRight != null) {
            intersectionList.add(intersectRight);
        }
        if (intersectLeft != null) {
            intersectionList.add(intersectLeft);
        }

        /*return intersection point list.*/
        return intersectionList;
    }

    /**
     * this method returns the width of the rectangle.
     * @return rectangle width.
     */
    public double getWidth() {
        return this.rectWidth;
    }

    /**
     * this method returns the height of the rectangle.
     * @return rectangle height.
     */
    public double getHeight() {
        return this.rectHeight;
    }

    /**
     * this method returns the upper left point of the rectangle.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.leftUpperStart;
    }

    /**
     * this method returns color of rectangle.
     * @return color - color of ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method returns the top line of the rectangle.
     * @return top line.
     */
    public Line getTopLine() {
        return this.top;
    }

    /**
     * this method returns the bottom line of the rectangle.
     * @return bottom line.
     */
    public Line getBottomLine() {
        return this.bottom;
    }

    /**
     * this method returns the right line of the rectangle.
     * @return right line.
     */
    public Line getRightLine() {
        return this.right;
    }

    /**
     * this method returns the left line of the rectangle.
     * @return left line.
     */
    public Line getLeftLine() {
        return this.left;
    }

    /**
     *This method sets the rectangles color.
     * @param c - color to change rect to.
     * */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * This method draws border of rectangle.
     * @param d - surface to draw on.
     * */
    public void drawBorders(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);

        /*fill top line*/
        d.drawLine((int) this.top.start().getX(),
                (int) this.top.start().getY(),
                (int) this.top.end().getX(),
                (int) this.top.end().getY());

        /*fill bottom line*/
        d.drawLine((int) this.bottom.start().getX(),
                (int) this.bottom.start().getY(),
                (int) this.bottom.end().getX(),
                (int) this.bottom.end().getY());

        /*fill right line*/
        d.drawLine((int) this.right.start().getX(),
                (int) this.right.start().getY(),
                (int) this.right.end().getX(),
                (int) this.right.end().getY());

        /*fill left line*/
        d.drawLine((int) this.left.start().getX(),
                (int) this.left.start().getY(),
                (int) this.left.end().getX(),
                (int) this.left.end().getY());

    }

    /**
     * This method returns true if point is
     * inside the coordinates of the rectangle.
     * @param p - point to check.
     * @return true - if is in rectangle, false otherwise.
     * */
    public boolean isInRectangle(Point p) {
        return p.getX() >= this.getUpperLeft().getX()
                && p.getX() <= this.getUpperLeft().getX() + this.getWidth()
                && p.getY() >= this.getUpperLeft().getY()
                && p.getY() <= this.getUpperLeft().getY() + this.getHeight();
    }
}