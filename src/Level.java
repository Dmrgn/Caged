import java.io.IOException;
import java.util.*;

import javafx.scene.*;
import javafx.scene.image.Image;

/**
 * <p>
 * This abstract class creates a template for all the levels and contains essential
 * methods and attributes for all Levels to access
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * 20 minutes were spent by Ryan Atlas restructuring this file and adding more attributes and methods on May 30th, 2022
 * </p>
 */
public abstract class Level {
    /**
     * HashMap storing textures for respective objects
     */
    protected HashMap<String, Image> textures;
    /**
     * Node for the level
     */
    protected Node node;
    /**
     * Level screen number to keep track of which screen the player is on in the level
     */
    public int levelScreen;
    /** File array for reading level data*/
    protected String[] files;
    /** LevelDataParser for reading level data*/
    protected LevelDataParser ldp;
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