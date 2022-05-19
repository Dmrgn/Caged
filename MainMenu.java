/**
 * <p>
 * This file controls the main menu of the game. It is called by the Game class
 * and it's selection variable is used as part of the main game loop in the Main class
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * </p>
 */

public class MainMenu {
    /** The user's selection from the menu*/
    private int selection;
    /**
     * MainMenu constructor, initializes selection to 0 as a default
     */
    public MainMenu() {
        selection = 0;
    }
    /**
     * Displays the graphics to the user
     */
    public void display() {}
    /**
     * Getter method for the selection instance variable
     * @return The user's selection
     */
    public int getSelection() {
        return selection;
    }
}