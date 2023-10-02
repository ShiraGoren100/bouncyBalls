/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class representing a game with four levels.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-16
 */
public class RunFourLevels {

    private static final int ZERO = 0;

    /**
     * This method runs the game.
     *
     * @param argv nothing
     */
    public static void main(String[] argv) {
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();

        /*initialize four levels*/
        List<LevelInformation> levels = new ArrayList<>(List.of(new DirectHit(),
                new WideEasy(), new GreenThree(), new FinalFour()));

        /*initialize game of four levels*/
        GameFlow game = new GameFlow(animationRunner, keyboardSensor, ZERO);
        game.runLevels(levels);

        /*close screen to end game*/
        game.getAnimationRunner().getGui().close();
    }
}
