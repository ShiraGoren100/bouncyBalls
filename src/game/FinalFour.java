/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import graphics.Line;
import graphics.Point;
import moveable.SpriteCollection;
import moveable.Block;
import moveable.Ball;
import moveable.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class representing the Final Four level.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class FinalFour implements LevelInformation {
    public static final int ZERO = 0;
    public static final int DY = -10;
    public static final int PADDLE_SPEED = 20;
    public static final int PADDLE_WIDTH = 70;

    /*screen size and time frame*/
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int NUM_OF_BLOCKS = 15;
    public static final int NUM_OF_BALLS = 3;
    public static final int BORDER = 25;
    public static final int DEGREES = 90;
    private static final int NUM_OF_ROWS = 7;
    private static final int MIDDLE_BLOCKS_START = 100;
    private static final int MIDDLE_BLOCKS_HEIGHT = 20;
    private static final int MIDDLE_BLOCKS_WIDTH = 50;

    /*clouds*/
    public static final int CLOUD_ONE_START_X = 70;
    public static final int CLOUD_ONE_START_Y = 400;
    public static final int CLOUD_ONE_END = CLOUD_ONE_START_X + 90;
    public static final int CLOUD_TWO_START_X = 570;
    public static final int CLOUD_TWO_START_Y = 500;
    public static final int CLOUD_TWO_END = CLOUD_TWO_START_X + 90;

    /**
     * This is the sprite collection of all sprites in background.
     * Also all blocks and their colors.
     * */
    private SpriteCollection sprites;
    private java.util.List<Color> colorList;
    private java.util.List<Block> blocks;


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> velocities = new ArrayList<Velocity>();
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
        return "Final Four";
    }

    /**
     * This method draws a cloud on screen.
     * */
    public void drawCloudOne() {

        /*draw target circles*/
        Ball b1 = new Ball(70, 400, 25, new Color(192, 192, 250));
        Ball b2 = new Ball(100, 405, 20, new Color(192, 192, 192));
        Ball b3 = new Ball(130, 390, 30, new Color(192, 180, 180));
        Ball b4 = new Ball(100, 370, 28, new Color(180, 180, 180));
        Ball b5 = new Ball(160, 400, 18, new Color(170, 170, 170));


        /*set velocity to 0*/
        b1.setVelocity(0, 0);
        b2.setVelocity(0, 0);
        b3.setVelocity(0, 0);
        b4.setVelocity(0, 0);
        b5.setVelocity(0, 0);

        /*set outer color*/
        b1.setOuterColor(new Color(192, 192, 250));
        b2.setOuterColor(new Color(192, 192, 192));
        b3.setOuterColor(new Color(192, 180, 180));
        b4.setOuterColor(new Color(180, 180, 180));
        b5.setOuterColor(new Color(170, 170, 170));

        /*add to sprite collection*/
        this.sprites.addSprite(b1);
        this.sprites.addSprite(b2);
        this.sprites.addSprite(b5);
        this.sprites.addSprite(b3);
        this.sprites.addSprite(b4);
    }

    /**
     * This method draws a cloud on screen.
     * */
    public void drawCloudOTWO() {

        /*draw target circles*/
        Ball b1 = new Ball(570, 500, 25, new Color(192, 192, 250));
        Ball b2 = new Ball(600, 489, 20, new Color(192, 192, 192));
        Ball b3 = new Ball(630, 500, 30, new Color(192, 180, 180));
        Ball b4 = new Ball(610, 520, 28, new Color(180, 180, 180));
        Ball b5 = new Ball(660, 490, 18, new Color(170, 170, 170));


        /*set velocity to 0*/
        b1.setVelocity(0, 0);
        b2.setVelocity(0, 0);
        b3.setVelocity(0, 0);
        b4.setVelocity(0, 0);
        b5.setVelocity(0, 0);

        /*set outer color*/
        b1.setOuterColor(new Color(192, 192, 250));
        b2.setOuterColor(new Color(192, 192, 192));
        b3.setOuterColor(new Color(192, 180, 180));
        b4.setOuterColor(new Color(180, 180, 180));
        b5.setOuterColor(new Color(170, 170, 170));

        /*add to sprite collection*/
        this.sprites.addSprite(b4);
        this.sprites.addSprite(b1);
        this.sprites.addSprite(b2);
        this.sprites.addSprite(b5);
        this.sprites.addSprite(b3);
    }

    /**
     * This method draws sun rain on screen.
     * */
    public void drawRain() {

        /*cloud one rain*/
        for (int i = CLOUD_ONE_START_X; i < CLOUD_ONE_END; i += 9) {
            Point start = new Point(i, CLOUD_ONE_START_Y);
            Point end = new Point(i + 50, SCREEN_HEIGHT);
            Line ray = new Line(start, end);
            ray.setColor(Color.WHITE);
            this.sprites.addSprite(ray);
        }

        /*cloud two rain*/
        for (int i = CLOUD_TWO_START_X; i < CLOUD_TWO_END; i += 7) {
            Point start = new Point(i, CLOUD_TWO_START_Y);
            Point end = new Point(i - 30, SCREEN_HEIGHT);
            Line ray = new Line(start, end);
            ray.setColor(Color.WHITE);
            this.sprites.addSprite(ray);
        }

    }

    @Override
    public SpriteCollection getBackground() {

        /*create Sprite collection*/
        this.sprites = new SpriteCollection();
        /*create background color*/
        Block background = new Block(new graphics.Rectangle(
                new graphics.Point(ZERO, ZERO), SCREEN_WIDTH, SCREEN_HEIGHT));
        background.setColor(new Color(31, 190, 214));
        this.sprites.addSprite(background);

        /*create rain clouds*/
        this.drawRain();
        this.drawCloudOne();
        this.drawCloudOTWO();
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
        graphics.Point start = new graphics.Point((SCREEN_WIDTH - BORDER)
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

        /*initialize 7 rows of blocks.*/
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            initializeRowOfBlocks(NUM_OF_BLOCKS, MIDDLE_BLOCKS_START
                    + (MIDDLE_BLOCKS_HEIGHT * i), this.colorList.get(i));
        }
    }

    @Override
    public java.util.List<Block> blocks() {

        /*create list of blocks*/
        this.blocks = new ArrayList<>();

        /*create block size and color array*/
        this.colorList = new ArrayList<Color>(List.of(Color.GRAY, Color.RED,
                Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN));

        /*create blocks*/
        this.initializeMiddleBlocks();
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {

        /*count all blocks from each row*/
        return NUM_OF_BLOCKS * NUM_OF_ROWS;
    }
}
