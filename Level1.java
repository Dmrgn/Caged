import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.scene.image.Image;
/**
 * <p>
 * This class creates the first level inheriting from the Level abstract class
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * Two minutes were spent by Ryan Atlas on this file on May 27th adding the getNode() method
 * 30 minutes were spent by Ryan Atlas on May 30th-June 1st adding text file input and using this to test level file reading
 * </p>
 */
public class Level1 extends Level {
    public Level1() {
        textures = new HashMap();
        textures.put("DoorOpen", new Image("assets/player.png"));
        textures.put("DoorClosed", new Image("assets/player.png"));
        textures.put("Sign", new Image("assets/player.png"));
        textures.put("Enemy1", new Image("assets/enemy.png"));
        File platformsFolder = new File("assets/platforms");
        for (File platform : platformsFolder.listFiles()) {
            textures.put("Platform:"+platform.getName().split("\\.")[0], new Image(platform.getPath()));
        }

        levelScreen = 0;
        files = new String[]{"Level1_1.txt", "Level1_2.txt"};
        ldp = new LevelDataParser(textures);
    }
    public ArrayList<GameObject> getObjects () throws IOException {
        return ldp.readFile(files[levelScreen]);
    }
    /**
     * Method for the user to play the game
     */
    public void play() {}
    /**
     * Updates the level's background and state
     */
    public void update() {

    }
    /**
     * Draws the level's background and assets
     */
    public void draw() {
    }
}