/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package general;

/**
 *  This is an interface representing things that are notified of hits.
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public class Counter {

    public static final int ZERO = 0;

    /** this is the counter.*/
    private int counter;

    /**
     * This constructor starts counter from number given.
     * @param start number to start counter at
     * */
    public Counter(int start) {
         this.counter = start;
    }

    /**
     * This constructor starts counter from zero.
     * */
    public Counter() {
        this.counter = ZERO;
    }

    /**
     * This method  adds number to current count.
     * @param  number number to add to counter.
     * */
     public void increase(int number) {
        this.counter += number;
    }

    /**
     * This method subtracts number from current count.
     * @param number number to subtract from counter.
     * */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * This method gets current count.
     * @return counter.
     * */
    public int getValue() {
        return this.counter;
    }
}
