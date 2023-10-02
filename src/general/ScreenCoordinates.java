/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package general;

/**
 *  This is a class representing the coordinates of a screen.
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-16
 */
public class ScreenCoordinates {
    private int screenWidth;
    private int screenHeight;
    private int start;

    /**
     * This constructor sets all coordinates of the screen.
     * @param width - width of screen.
     * @param height - height of screen.
     * @param startPoint - start point of screen.
     * */
    public ScreenCoordinates(int width, int height, int startPoint) {
        this.screenHeight = height;
        this.screenWidth = width;
        this.start = startPoint;
    }

    /**
     * This method returns screen width.
     * @return screen width.
     * */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * This method returns screen height.
     * @return screen height.
     * */
    public int getScreenHeight() {
        return this.screenHeight;
    }

    /**
     * This method returns screen start point.
     * @return screen start point.
     * */
    public int getStart() {
        return this.start;
    }

    /**
     * This method sets screen width.
     * @param width - screen width to change.
     * */
    public void setScreenWidth(int width) {
        this.screenWidth = width;
    }

    /**
     * This method sets screen height.
     * @param height - screen height to change.
     * */
    public void setScreenHeight(int height) {
        this.screenHeight = height;
    }

    /**
     * This method sets screen start point.
     * @param startPoint - screen start point to change.
     * */
    public void setStart(int startPoint) {
        this.start = startPoint;
    }
}
