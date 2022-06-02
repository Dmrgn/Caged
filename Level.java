import java.io.IOException;
import java.util.*;

import javafx.scene.*;

/**
 * <p>
 * This abstract class creates a template for all the levels and implements
 * GameObject so that all the Levels can be drawn and updated
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
public abstract class Level {
    /**
     * HashMap storing textures for respective objects
     */
    protected HashMap<String, String> textures;
    /**
     * Node for the level
     */
    protected Node node;
    /**
     * Level screen number to keep track of which screen the player is on in the level
     */
    public int levelScreen;
    protected String[] files;
    protected LevelDataParser ldp;
    /**
     * Controls adding the player to that level
     */
    public void enter(Player player) {
    }

    /**
     * Controls removing the player from that level
     */
    public void exit(Player player) {
    }

    /**
     * Abstract method for playing in that level and loading assets
     */
    public abstract void play();

    /**
     * Draw method that is to be overridden by the subclasses
     */
    public abstract void draw();
    /**
     * Update method to be overridden by the subclasses
     */
    public abstract void update();
    public abstract ArrayList<GameObject> getObjects() throws IOException;
}