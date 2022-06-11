/**
 * <p>
 * This interface is used for interactable objects like doors and signs
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. Comments and methods were added 
 * </p>
 */
public interface Interactable {
   /**
   * Checks whether the player is close enough to the object to interact with it
   * @param p The player
   * @return A boolean on whether the player is in range
   */
   public boolean inRange(Player p);
   /**
   * Method to display a message to the player when they are interacting with it
   */
   public void display();
}