/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import graphics.Point;
import graphics.Rectangle;
import moveable.SpriteCollection;
import moveable.Block;
import moveable.Ball;
import moveable.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class representing the Green 3 level.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class GreenThree implements LevelInformation {
    public static final int ZERO = 0;
    public static final int DY = -10;
    public static final int PADDLE_SPEED = 20;
    public static final int PADDLE_WIDTH = 70;

    /*screen size and time frame*/
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int NUM_OF_BLOCKS = 10;
    public static final int NUM_OF_BALLS = 2;
    public static final int BORDER = 25;
    public static final int DEGREES = 90;
    private static final int NUM_OF_ROWS = 5;
    private static final int MIDDLE_BLOCKS_START = 100;
    private static final int MIDDLE_BLOCKS_HEIGHT = 20;
    private static final int MIDDLE_BLOCKS_WIDTH = 50;

    /*building attributes*/
    public static final int FLOOR_ONE_HEIGHT = 150;
    public static final int FLOOR_ONE_WIDTH = 90;
    public static final Point START_ONE = new Point(50,
            SCREEN_HEIGHT - FLOOR_ONE_HEIGHT);

    /*floor 2*/
    public static final int FLOOR_TWO_HEIGHT = 50;
    public static final int FLOOR_TWO_WIDTH = 30;
    public static final Point START_TWO = new Point(80, SCREEN_HEIGHT
            - FLOOR_ONE_HEIGHT - FLOOR_TWO_HEIGHT);

    /*floor 3*/
    public static final int FLOOR_THREE_HEIGHT = 200;
    public static final int FLOOR_THREE_WIDTH = 10;
    public static final Point START_THREE = new Point(90, SCREEN_HEIGHT
            - FLOOR_ONE_HEIGHT - FLOOR_TWO_HEIGHT - FLOOR_THREE_HEIGHT);

    /*windows*/
    public static final int WINDOW_HEIGHT = 25;
    public static final int WINDOW_WIDTH = 10;
    public static final int WINDOW_LINES = 5;
    public static final int SPACE = 7;
    public static final int WINDOW_START_X = (int) START_ONE.getX() + SPACE;
    public static final int WINDOW_START_Y = (int) START_ONE.getY() + SPACE;

    /*antenna*/
    public static final int RADIUS = 15;
    public static final int SUBTRACT = 6;

    /**
     * This is the sprite collection of all sprites in background,
     * and all blocks and their colors.
     * */
    private List<Color> colorList;
    private List<Block> blocks;
    private SpriteCollection sprites;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        int balls = NUM_OF_BALLS;
        double distance = (double) DEGREES / NUM_OF_BALLS;

        /*if odd number of balls- center ball will go straight up.*/
        if (balls % 2 != ZERO) {
            Velocity v = Velocity.fromAngleAndSpeed(ZERO, DY);
            velocities.add(v);
            balls--;
        }

        /*make balls go in arc*/
        for (int i = 0; i < balls / 2; i++) {
            Velocity vLeft = Velocity.fromAngleAndSpeed(distance * (i + 1), DY);
            velocities.add(vLeft);
            Velocity vRight = Velocity.fromAngleAndSpeed(-(distance * (i + 1)), DY);
            velocities.add(vRight);
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * This method draws building on screen.
     * */
    public void drawBuilding() {

        /*create target lines*/
        Block firstFloor = new Block(new Rectangle(START_ONE, FLOOR_ONE_WIDTH,
                FLOOR_ONE_HEIGHT));
        firstFloor.setColor(new Color(40, 40, 40));
        this.sprites.addSprite(firstFloor);

        Block secondFloor = new Block(new Rectangle(START_TWO, FLOOR_TWO_WIDTH,
                FLOOR_TWO_HEIGHT));
        secondFloor.setColor(new Color(50, 50, 50));
        this.sprites.addSprite(secondFloor);

        Block thirdFloor = new Block(new Rectangle(START_THREE, FLOOR_THREE_WIDTH,
                FLOOR_THREE_HEIGHT));
        thirdFloor.setColor(new Color(80, 80, 80));
        this.sprites.addSprite(thirdFloor);

    }

    /**
     * This method draws building windows on screen.
     * */
    public void drawWindows() {

       for (int i = 0; i < WINDOW_LINES; i++) {
           for (int j = 0; j < WINDOW_LINES; j++) {
               Block window = new Block(new Rectangle(
                        new Point(WINDOW_START_X + j * (WINDOW_WIDTH + SPACE),
                       WINDOW_START_Y + i * (WINDOW_HEIGHT + SPACE)),
                       WINDOW_WIDTH, WINDOW_HEIGHT));
               window.setColor(Color.WHITE);
               this.sprites.addSprite(window);
           }
       }
    }

    /**
     * This method draws building antenna on screen.
     * */
    public void drawAntenna() {
        Point center = new Point(START_THREE.getX()
                + (int) (FLOOR_THREE_WIDTH / 2), START_THREE.getY()
                - RADIUS);

        /*draw target circles*/
        Ball circle1 = new Ball(center, RADIUS, Color.ORANGE.darker());
        Ball circle2 = new Ball(center, RADIUS - SUBTRACT, Color.RED);
        Ball circle3 = new Ball(center, RADIUS - 2 * SUBTRACT, Color.WHITE);


        /*set velocity to 0*/
        circle1.setVelocity(0, 0);
        circle2.setVelocity(0, 0);
        circle3.setVelocity(0, 0);

        /*set outer color*/
        circle1.setOuterColor(Color.ORANGE.darker());
        circle2.setOuterColor(Color.RED);
        circle3.setOuterColor(Color.WHITE);

        /*add to sprite collection*/
        this.sprites.addSprite(circle1);
        this.sprites.addSprite(circle2);
        this.sprites.addSprite(circle3);
    }

    @Override
    public SpriteCollection getBackground() {

        /*create Sprite collection*/
        this.sprites = new SpriteCollection();

        /*create background color*/
        Block background = new Block(new Rectangle(
                new Point(ZERO, ZERO), SCREEN_WIDTH, SCREEN_HEIGHT));
        background.setColor(Color.GREEN.darker().darker());
        this.sprites.addSprite(background);

        /*create building*/
        this.drawBuilding();
        this.drawWindows();
        this.drawAntenna();
        return this.sprites;
    }

    /**
     * This method initializes a row of blocks.
     *
     * @param numOfBlocks - number of blocks in the line.
     * @param height      - height of row on screen.
     * @param color       of blocks in row.
     */
    public void initializeRowOfBlocks(int numOfBlocks, int height,
                                      java.awt.Color color) {
        Point start = new Point((SCREEN_WIDTH - BORDER)
                - ((numOfBlocks * MIDDLE_BLOCKS_WIDTH)), height);

        /*
         * create new block as ong as doesn't meet
         * right screen border.
         * */
        while (start.getX() < SCREEN_WIDTH - BORDER) {

            /*create block at start point and add to list*/
            Block b = new Block(start, MIDDLE_BLOCKS_WIDTH, MIDDLE_BLOCKS_HEIGHT);
            b.setColor(color);
            this.blocks.add(b);

            /*change start point*/
            start = new Point(start.getX() + MIDDLE_BLOCKS_WIDTH, height);
        }
    }

    /**
     * This method initializes 6 rows of blocks in different colors.
     * <p>
     * This method runs for 6 rows, each time starting row
     * right under previous row.
     * </p>
     */
    public void initializeMiddleBlocks() {

        /*initialize 5 rows of blocks.*/
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            initializeRowOfBlocks(NUM_OF_BLOCKS - i,
                    MIDDLE_BLOCKS_START + (MIDDLE_BLOCKS_HEIGHT * i),
                    this.colorList.get(i));
        }
    }

    @Override
    public List<Block> blocks() {

        /*create list of blocks*/
        this.blocks = new ArrayList<>();

        /*create block size and color array*/
        this.colorList = new ArrayList<Color>(List.of(Color.GRAY, Color.RED,
                Color.YELLOW, Color.BLUE, Color.WHITE));

        /*create blocks*/
        this.initializeMiddleBlocks();
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        int numOfBlocks = ZERO;

        /*count all blocks from each row*/
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            numOfBlocks += (NUM_OF_BLOCKS - i);
        }
        return numOfBlocks;
    }
}
