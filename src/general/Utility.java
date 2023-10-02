/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package general;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  This is a class of utility functions used frequently in different classes.
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-16
 */
public class Utility {

    /**
     * this method generates random color.
     * @param notToBe - a color we don't want.
     * @return randColor - the random color found.
     */
    public static java.awt.Color generateRandomColor(java.awt.Color notToBe) {
        Random rand = new Random();
        java.awt.Color randColor;

        /*
         * get three random numbers and generate a color out of them,
         *  if color found is color we don't want - try again.
         * */
        do {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            randColor = new java.awt.Color(r, g, b);
        } while (randColor == notToBe);
        return randColor;
    }

    /**
     * This method creates a list of colors each different from the others.
     * @param rows - amount of colors wanted.
     * @return color list.
     * */
    public static List<Color> findListOfDifferentColors(int rows) {
        List<Color> colorList = new ArrayList<Color>();
        boolean isNewColor = true;

        /*create list of colors for block rows of game*/
        for (int i = 0; i < rows; i++) {
            java.awt.Color c;

            /*find color and make sure its a different color each time.*/
            do {
                c = Utility.generateRandomColor(null);

                /*make sure color isn't already in list.*/
                for (java.awt.Color color : colorList) {
                    if (color == c) {
                        isNewColor = false;
                        break;
                    }
                }
            } while (!isNewColor);

            /*add new color to color list*/
            colorList.add(c);
        }
        return colorList;
    }
}
