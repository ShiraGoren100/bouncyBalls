/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

import biuoop.KeyboardSensor;
import game.RunFourLevels;
import game.GameFlow;
import game.AnimationRunner;
import game.LevelInformation;
import game.DirectHit;
import game.WideEasy;
import game.GreenThree;
import game.FinalFour;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is a class representing a game with levels.
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-16
 */
public class Ass6Game {

    /*These vars hold usefull numbers*/
    public static final int ILEGAL = -1;
    public static final int NUM_OF_LEVELS = 4;
    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;
    public static final int LEVEL_TREE = 3;
    public static final int LEVEL_FOUR = 4;
    public static final int ZERO = 0;

    /**These vars are lists of levels.*/
    private final List<Integer> levelNumbersList;
    private final List<LevelInformation> levels;

    /**
     * This constructor initializes level lists.
     * */
    public Ass6Game() {
        this.levelNumbersList  = new ArrayList<Integer>();
        this.levels  = new ArrayList<LevelInformation>();
    }

    /**
     * This method checks if string given is a number.
     * @param arg string to check
     * @return ILEGAL if not a number, or if number not in range.
     *         else- returns num in numerical form
     **/
    public int getLevel(String arg) {

        /*check if string is a number*/
        for (int i = 0; i < arg.length(); i++) {

            /*if string is not a number*/
            if (arg.charAt(i) < '0' || arg.charAt(i) > '9') {
                return ILEGAL;
            }
        }

        /*string is a number- so convert to integer.*/
        int num = Integer.parseInt(arg);

        /*check if number is in range of num of levels*/
        if (num < 1 || num > NUM_OF_LEVELS) {
            return ILEGAL;
        } else {

            /*return level in numerical form*/
            return num;
        }
    }

    /**
     * This method creates level list.
     * */
    public void createLevelList() {

        for (int level : this.levelNumbersList) {
            switch (level) {
                case LEVEL_ONE:
                    this.levels.add(new DirectHit());
                    break;
                case LEVEL_TWO:
                    this.levels.add(new WideEasy());
                    break;
                case LEVEL_TREE:
                    this.levels.add(new GreenThree());
                case LEVEL_FOUR:
                    this.levels.add(new FinalFour());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method runs customized game.
     * */
    public void runGame() {

        /*if no levels were given- run default 4 level game.*/
        if (this.levels.size() == 0) {
            RunFourLevels.main(null);
        } else {

            /*customize levels for game*/
            AnimationRunner animationRunner = new AnimationRunner();
            KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();

            /*initialize game of four levels*/
            GameFlow game = new GameFlow(animationRunner, keyboardSensor, ZERO);
            game.runLevels(this.levels);

            /*close screen to end game*/
            game.getAnimationRunner().getGui().close();
        }
    }

    /**
     * This method receives request for levels and runs game accordingly.
     * @param argv array of levels*/
    public static void main(String[] argv) {

        /*initialize game and level number array*/
        Ass6Game game = new Ass6Game();

        /*check if any arguments were given*/
        if (argv.length != 0) {

            /*extract all legal levels*/
            for (String arg : argv) {
                int num = game.getLevel(arg);

                /*add number of legal level*/
                if (num != ILEGAL) {
                    game.levelNumbersList.add(num);
                }
            }
        }

        /*call function to run customized game*/
        game.createLevelList();
        game.runGame();
    }
}
