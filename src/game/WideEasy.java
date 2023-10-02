/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import graphics.Line;
import graphics.Point;
import graphics.Rectangle;
import moveable.Ball;
import moveable.Block;
import moveable.Velocity;
import moveable.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class representing the Wide Easy level.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class WideEasy implements LevelInformation {

    /*useful numbers*/
    public static final int ZERO = 0;
    public static final int DY = -10;
    public static final int PADDLE_SPEED = 10;
    public static final int PADDLE_WIDTH = 600;

    /*screen size and time frame*/
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int NUM_OF_BLOCKS = 15;
    public static final int NUM_OF_BALLS = 10;
    public static final int BORDER = 25;
    public static final int RAINBOW_HEIGHT = 250;
    public static final int BLOCK_HEIGHT = 25;
    public static final int DEGREES = 90;

    /*sun attributes*/
    public static final Point SUN_CENTER = new Point(150, 130);
    public static final int RADIUS = 30;
    public static final int ADD = 5;
    public static final int MAX_RAY = 700;

    /**
     * This is the sprite collection of all sprites in background.
     * */
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
        return "Wide Easy";
    }

    /**
     * This method draws target circles on screen.
     * */
    public void drawSunCircles() {

        /*draw target circles*/
        Ball circle1 = new Ball(SUN_CENTER, RADIUS, Color.YELLOW);
        Ball circle2 = new Ball(SUN_CENTER, RADIUS + ADD,
                new Color(255, 255, 170));
        Ball circle3 = new Ball(SUN_CENTER, RADIUS + ADD * 2,
                new Color(255, 230, 70));

        /*set velocity to 0*/
        circle1.setVelocity(0, 0);
        circle2.setVelocity(0, 0);
        circle3.setVelocity(0, 0);

        /*set outer color*/
        circle1.setOuterColor(Color.YELLOW);
        circle2.setOuterColor(Color.YELLOW.brighter());
        circle3.setOuterColor(new Color(255, 230, 100));

        /*add to sprite collection*/
        this.sprites.addSprite(circle3);
        this.sprites.addSprite(circle2);
        this.sprites.addSprite(circle1);
    }

    /**
     * This method draws sun rays on screen.
     * */
    public void drawSunRays() {

        /*generate rays*/
        for (int i = 0; i < MAX_RAY; i += 7) {
            Point end = new Point(i, RAINBOW_HEIGHT);
            Line ray = new Line(SUN_CENTER, end);
            ray.setColor(Color.ORANGE);
            this.sprites.addSprite(ray);
        }

    }

    @Override
    public SpriteCollection getBackground() {

        /*create Sprite collection*/
        this.sprites = new SpriteCollection();

        /*create background color*/
        Block background = new Block(new Rectangle(
                new Point(ZERO, ZERO), SCREEN_WIDTH, SCREEN_HEIGHT));
        background.setColor(Color.WHITE);
        this.sprites.addSprite(background);

        /*create sun*/
        this.drawSunRays();
        this.drawSunCircles();
        return this.sprites;
    }

    @Override
    public List<Block> blocks() {

        /*create list of blocks*/
        List<Block> blocks = new ArrayList<>();

        /*create block size and color array*/
        int blockSize = (SCREEN_WIDTH - (2 * BORDER)) / NUM_OF_BLOCKS;
        List<Color> colorList = new ArrayList<Color>(List.of(Color.RED, Color.RED,
                Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE,
                Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN));

        /*create rainbow blocks*/
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {

            Block created = new Block(new Rectangle(new Point(BORDER
                    + (i * blockSize), RAINBOW_HEIGHT),
                    blockSize, BLOCK_HEIGHT));
            created.setColor(colorList.get(i));
            blocks.add(created);

        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
