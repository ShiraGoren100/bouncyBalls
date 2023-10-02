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
 * This is a class representing the Direct hit level.
 *
 * @author Shira Goren.
 * @version 1
 * @since 2021-06-15
 */
public class DirectHit implements LevelInformation {
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int DY = -10;
    public static final int PADDLE_SPEED = 10;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_WIDTH = 100;

    /*screen size and time frame*/
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int MIDDLE = 400;
    public static final int SCREEN_START = 50;
    public static final int LINE_LEN = 100;
    public static final int MIDDLE_SPACE = 24;
    public static final int HALF_MIDDLE_SPACE = 12;
    public static final int BOTTOM_LINE_START = SCREEN_START + LINE_LEN + MIDDLE_SPACE;
    public static final int LEFT_LINE_ON_SCREEN = 30;
    public static final int LEFT_LINE_START = SCREEN_START + LEFT_LINE_ON_SCREEN;

    /**
     * This is the sprite collection of all sprites in background.
     * */
    private SpriteCollection sprites;

    @Override
    public int numberOfBalls() {
        return ONE;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(ZERO, DY));
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
        return "Direct Hit";
    }

    /**
     * This method draws target circles on screen.
     * */
    public void drawTargetCircles() {

        /*draw target circles*/
        Ball circle1 = new Ball(MIDDLE, SCREEN_START + LINE_LEN + MIDDLE_SPACE / 2,
                LINE_LEN - 30 + MIDDLE_SPACE, Color.BLACK);
        Ball circle2 = new Ball(MIDDLE, SCREEN_START + LINE_LEN + MIDDLE_SPACE / 2,
                LINE_LEN - 50 + MIDDLE_SPACE, Color.BLACK);
        Ball circle3 = new Ball(MIDDLE, SCREEN_START + LINE_LEN + MIDDLE_SPACE / 2,
                LINE_LEN - 80 + MIDDLE_SPACE, Color.BLACK);

        /*set velocity to 0*/
        circle1.setVelocity(0, 0);
        circle2.setVelocity(0, 0);
        circle3.setVelocity(0, 0);

        /*set outer color*/
        circle1.setOuterColor(Color.BLUE);
        circle2.setOuterColor(Color.BLUE);
        circle3.setOuterColor(Color.BLUE);

        /*add to sprite collection*/
        this.sprites.addSprite(circle1);
        this.sprites.addSprite(circle2);
        this.sprites.addSprite(circle3);
    }

    /**
     * This method draws target lines on screen.
     * */
    public void drawTargetLines() {

        /*create target lines*/
        Block top = new Block(new Rectangle(
                new Point(MIDDLE, SCREEN_START), 2, LINE_LEN));
        Block bottom = new Block(new Rectangle(
                new Point(MIDDLE, BOTTOM_LINE_START), 2, LINE_LEN));
        Block left = new Block(new Rectangle(
                new Point(MIDDLE - LINE_LEN - (int) (MIDDLE_SPACE / 2),
                        SCREEN_START + LINE_LEN + (int) (MIDDLE_SPACE / 2)),
                LINE_LEN, 2));
        Block right = new Block(new Rectangle(
                new Point(MIDDLE + (int) (MIDDLE_SPACE / 2),
                        SCREEN_START + LINE_LEN + (int) (MIDDLE_SPACE / 2)),
                LINE_LEN, 2));

        /*set lines color*/
        top.setColor(Color.BLUE);
        bottom.setColor(Color.BLUE);
        left.setColor(Color.BLUE);
        right.setColor(Color.BLUE);

        /*add to sprite collection*/
        this.sprites.addSprite(top);
        this.sprites.addSprite(bottom);
        this.sprites.addSprite(left);
        this.sprites.addSprite(right);;
    }

    @Override
    public SpriteCollection getBackground() {

        /*create Sprite collection*/
        this.sprites = new SpriteCollection();

        /*create background color*/
        Block background = new Block(new Rectangle(
                new Point(ZERO, ZERO), SCREEN_WIDTH, SCREEN_HEIGHT));
        background.setColor(Color.BLACK);
        this.sprites.addSprite(background);
        this.drawTargetCircles(); // draw circles
        this.drawTargetLines(); // draw lines
        return this.sprites;
    }

    @Override
    public List<Block> blocks() {

        /*create list of blocks*/
        List<Block> blocks = new ArrayList<>();

        /*create inner square*/
        Block targetCenter = new Block(new Rectangle(
                new Point(MIDDLE - HALF_MIDDLE_SPACE,
                        SCREEN_START + LINE_LEN), MIDDLE_SPACE,
                MIDDLE_SPACE));
        targetCenter.setColor(Color.RED);

        /*add to blocks and return*/
        blocks.add(targetCenter);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return ONE;
    }
}
