/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package game;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

import biuoop.KeyboardSensor;
import collidable.ScoreTrackingListener;
import collidable.Collidable;
import collidable.BallRemover;
import collidable.BlockRemover;
import general.Counter;
import graphics.Point;
import moveable.Velocity;
import moveable.Block;
import moveable.Ball;
import moveable.ScoreIndicator;
import moveable.Sprite;
import moveable.SpriteCollection;
import moveable.Paddle;
import general.ScreenCoordinates;
import graphics.Rectangle;

/**
 * This is a class representing the game.
 *
 * @author Shira Goren.
 * @version 3
 * @since 2021-06-15
 */
public class GameLevel implements Animation {

    /*useful numbers*/
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int ONE_HUNDRED = 100;

    /*screen size and time frame*/
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int MIDDLE = 400;

    /*borders of blocks*/
    public static final int START = 0;
    public static final int BORDER_SMALL = 25;
    public static final int MIDDLE_BLOCKS_WIDTH = 50;
    public static final int NUM_OF_ROWS = 6;
    public static final int NUM_OF_BLOCKS = 12;
    public static final int PADDLE_HEIGHT = 10;


    /*ball coordinates*/
    public static final int RADIUS = 6;
    private static final double SECONDS = 2000;

    /**
     * These vars represent the objects of the game.
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private ScreenCoordinates screen;
    private Paddle paddle;
    private int numOfBalls;
    private final Counter remainingBlocks;
    private Counter remainingBalls;
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;

    /**
     * This constructor initializes a game
     * environment and a sprite collector for the game.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.screen
                = new ScreenCoordinates(SCREEN_WIDTH, SCREEN_HEIGHT, ZERO);
        this.numOfBalls = TWO;

        /*initialize counters.*/
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter(this.numOfBalls);
        this.score = new Counter();
        this.runner = new AnimationRunner();
    }

    /**
     * This constructor initializes a game
     * environment and a sprite collector for the game.
     *
     * @param ballsNum  num of balls
     * @param prevScore score acquired
     * @param levelInf  the current level
     * @param key       keyboard sensor
     * @param ar        animation runner
     */
    public GameLevel(int ballsNum, Counter prevScore, LevelInformation levelInf,
                     KeyboardSensor key, AnimationRunner ar) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.screen
                = new ScreenCoordinates(SCREEN_WIDTH, SCREEN_HEIGHT, ZERO);
        this.numOfBalls = ballsNum;

        /*initialize counters.*/
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter(this.numOfBalls);
        this.score = prevScore;

        /*create ball and block list and colors for each block.*/
        this.level = levelInf;
        this.keyboard = key;
        this.runner = ar;
    }

    /**
     * This method sets game level.
     *
     * @param addLevel level to add
     */
    public void setLevel(LevelInformation addLevel) {
        this.level = addLevel;
    }

    /**
     * This method returns num of balls left.
     *
     * @return num of balls left.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * This method returns num of blocks left.
     *
     * @return num of blocks left.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * This method returns game sprites.
     *
     * @return spriteCollection of game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * This method returns game environment.
     *
     * @return environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * This method sets game environment.
     *
     * @param e - the new environment.
     */
    public void setEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**
     * This method sets number of balls for game.
     *
     * @param num num of balls ta add to game.
     */
    public void setNumOfBalls(int num) {
        this.numOfBalls = num;

        /*change ball counter to current number of balls.*/
        this.remainingBalls = new Counter(this.numOfBalls);
    }

    /**
     * This method sets new size of screen for game.
     *
     * @param width  - screen width.
     * @param height - screen height.
     * @param start  - top left corner of screen.
     */
    public void setScreen(int width, int height, int start) {
        this.screen = new ScreenCoordinates(width, height, start);
    }

    /**
     * This method sets new paddle rectangle for game.
     *
     * @param rec - rectangle to set paddle to be
     */
    public void setPaddle(Rectangle rec) {
        this.paddle.setPaddle(rec);
    }

    /**
     * This method returns size of screen for game.
     *
     * @return screen coordinates.
     */
    public ScreenCoordinates getScreen() {
        return this.screen;
    }

    /**
     * This method returns paddle of game.
     *
     * @return paddle.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * This method returns game environment.
     *
     * @return game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * This method adds a collidable to the game environment.
     *
     * @param c - collidable object to add to game environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds a sprite to the sprite collection.
     *
     * @param s - sprite object to add to sprite collector.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * This method initializes the borders framing the screen.
     */
    public void initializeBorders() {
        Block rightBorder = new Block(new Point(START, BORDER_SMALL), BORDER_SMALL,
                this.getScreen().getScreenHeight() + THREE);
        Block topBorder = new Block(new Point(START, BORDER_SMALL),
                this.getScreen().getScreenWidth()
                        - BORDER_SMALL, BORDER_SMALL);
        Block leftBorder = new Block(new Point(
                this.getScreen().getScreenWidth()
                        - BORDER_SMALL, BORDER_SMALL), BORDER_SMALL,
                this.getScreen().getScreenHeight());
        Block bottomBorder = new Block(
                new Point(START, this.getScreen().getScreenHeight()),
                this.getScreen().getScreenWidth(), ONE);

        /*add bottom border as listener of ball remover*/
        BallRemover removeBalls = new BallRemover(this, this.remainingBalls);
        bottomBorder.addHitListener(removeBalls);

        /*set border colors*/
        rightBorder.setColor(java.awt.Color.GRAY);
        leftBorder.setColor(java.awt.Color.GRAY);
        topBorder.setColor(java.awt.Color.GRAY);
        bottomBorder.setColor(java.awt.Color.GRAY);

        /*add blocks to list of game.*/
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        topBorder.addToGame(this);
        bottomBorder.addToGame(this);
    }

    /**
     * This method creates num of balls asked for with velocities given,
     * and adds to ball list.
     *
     * @param numOfB     num of balls to create.
     * @param velocities ball velocities to apply
     */
    public void createBalls(int numOfB, List<Velocity> velocities) {
        for (int i = 0; i < numOfB; i++) {
            Color color = Color.WHITE;
            Ball b = new Ball(MIDDLE, SCREEN_HEIGHT - BORDER_SMALL, RADIUS, color);
            b.setVelocity(velocities.get(i));
            b.setGameEnvironment(this.getGameEnvironment());
            b.setScreen(this.getScreen().getScreenWidth(),
                    this.getScreen().getScreenHeight(),
                    this.getScreen().getStart());
            b.addToGame(this);
        }
    }

    /**
     * This method adds all blocks to the game, with listeners.
     * @param blocks list of blocks to add to game
     */
    public void addBlocksToGame(List<Block> blocks) {

        /*create hit listeners*/
        BlockRemover br = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        for (Block b : blocks) {
            b.addToGame(this);
            this.remainingBlocks.increase(ONE); // add one to counter of middle blocks
            b.addHitListener(br); // add blockRemover as a hitListener to block
            b.addHitListener(scoreListener); // add ScoreListener to track score

        }
    }

    /**
     * This method sets paddles boundaries and size.
     * @param key keyboard sensor
     */
    public void setPaddleForGame(KeyboardSensor key) {

        /*set paddle*/
        this.paddle = new Paddle(key, Color.YELLOW);
        this.paddle.setPaddle(new Rectangle(new Point(
                MIDDLE - (int) (this.level.paddleWidth() / 2),
                PADDLE_HEIGHT - BORDER_SMALL + SCREEN_HEIGHT),
                this.level.paddleWidth(), PADDLE_HEIGHT));

        /*set paddle speed*/
        this.paddle.setSpeed(this.level.paddleSpeed());

        /*create boundaries for paddle movement.*/
        ScreenCoordinates paddleScreen = new ScreenCoordinates(
                this.getScreen().getScreenWidth() - BORDER_SMALL,
                this.getScreen().getScreenHeight() - TWO * BORDER_SMALL,
                BORDER_SMALL);
        this.paddle.setScreen(paddleScreen);

        /*add paddle to game*/
        paddle.addToGame(this);
        this.environment.setPaddle(this.paddle);
    }

    /**
     * This method adds panel with writing to screen.
     */
    public void addLevelSprite() {
        LevelIndicator li = new LevelIndicator(this.level);
        this.sprites.addSprite(li);
    }

    /**
     * This method initializes a new game.
     * <p>
     * create the Blocks and Ball (and Paddle)
     * and add them to the game.
     * </p>
     */
    public void initialize() {

        /*initialize all game objects*/
//        this.sprites.addSprite(this.level.getBackground());
        this.sprites = this.level.getBackground();
        this.addLevelSprite();
        this.addBlocksToGame(this.level.blocks());
        this.createBalls(this.level.numberOfBalls(),
                this.level.initialBallVelocities());
        this.setNumOfBalls(this.level.numberOfBalls());
        this.initializeBorders();

        /*create paddle for user*/
        this.setPaddleForGame(this.keyboard);

        /*create scoreBoard*/
        ScoreIndicator scoreBoard = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreBoard);
    }

    /**
     * This method signals when game should stop.
     * @return if animation should stop
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This method draws one iteration of animation.
     *
     * @param d draw surface to draw animation on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        /*draw all objects and notify them time passed*/
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        /*check if game is paused*/
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        /*add 100 points if cleared all blocks, and set flag to end game*/
        if (this.remainingBlocks.getValue() == ZERO) {
            this.score.increase(ONE_HUNDRED);
            this.running = false;
        } else if (this.remainingBalls.getValue() == ZERO) {

            /*set flag to stop game*/
            this.running = false;
        }
    }

    /**
     * This method runs the game by running animation runner.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(SECONDS, THREE, this.sprites));

        this.running = true;

        /* use our runner to run the current animation -- which is one turn of
         the game.*/
        this.runner.run(this);
    }

    /**
     * This method removes collidable from list.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getAllCollidables().remove(c);
    }

    /**
     * This method removes sprite from list.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeASprite(s);
    }
}
