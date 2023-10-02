/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.KeyboardSensor;
import general.Counter;
import java.util.List;

/**
 * This is a class representing a game with 4 levels.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class GameFlow {

    private final Counter score;
    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private boolean didWin;

    /**
     * This constructor creates vars for game flow.
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param prevScore int score acquired
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int prevScore) {
        this.score = new Counter(prevScore);
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.didWin = true;
    }

    /**
     * This method returns animation runner.
     *
     * @return animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    /**
     * This method runs loop of levels.
     *
     * @param levels all levels to run in one game.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo.numberOfBalls(),
                    this.score, levelInfo, this.keyboardSensor,
                    this.animationRunner);

            /*initialize level*/
            level.initialize();

            /*play level while there are blocks and balls*/
            while (level.getRemainingBalls().getValue() != 0
                    && level.getRemainingBlocks().getValue() != 0) {
                level.run();
            }

            /*if all balls are lost- lose game.*/
            if (level.getRemainingBalls().getValue() == 0) {
                this.didWin = false;
                break;
            }
        }

        /*show end screen*/
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.didWin, this.score.getValue())));
    }
}
