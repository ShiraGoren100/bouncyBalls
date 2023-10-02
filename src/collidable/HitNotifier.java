/*
 * Name: Shira Goren.
 * ID: 207814989
 * ass6
 */

package collidable;

/**
 * This is an interface representing objects that notify when they are hit.
 *
 * @author Shira Goren.
 * @version 2
 * @since 2021-06-16
 */
public interface HitNotifier {

    /**
     * This method adds hl as a listener to hit events.
     * @param hl hit listener to add.
     * */
    void addHitListener(HitListener hl);

   /**
    * This method removes hl from the list of listeners to hit events.
    * @param hl hit listener to remove.
    * */
    void removeHitListener(HitListener hl);
}
