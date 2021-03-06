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
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * Two minutes were spent by Ryan Atlas on this file on May 27th adding the getNode() method
 * 30 minutes were spent by Ryan Atlas on May 30th-June 1st adding text file input and using this to test level file reading
 * </p>
 */
public class Level1 extends Level {
    /**
     * Level1 class constructor that initializes images and reads files
     */
    public Level1() {
        textures = new HashMap();
        textures.put("Sign", new Image("assets/player.png"));
        textures.put("FinalBoss", new Image("assets/finalBoss.png"));
        textures.put("Boss1", new Image("assets/boss1.png"));
        for (File mold : (new File("assets/molds").listFiles())) {
            textures.put("Mold:"+mold.getName().split("\\.")[0], new Image(mold.getPath()));
        }
        for (File door : (new File("assets/doors").listFiles())){
            textures.put("Door:"+ door.getName().split("\\.")[0], new Image(door.getPath()));
        }
        for (File background : (new File("assets/backgrounds").listFiles())) {
            textures.put("Background:"+background.getName().split("\\.")[0], new Image(background.getPath()));
        }
        for (File platform : (new File("assets/platforms").listFiles())) {
            textures.put("Platform:"+platform.getName().split("\\.")[0], new Image(platform.getPath()));
        }
        for (File sign : (new File("assets/signs").listFiles())) {
            textures.put("Sign:"+sign.getName().split("\\.")[0], new Image(sign.getPath()));
        }
        for (File key : (new File("assets/keys").listFiles())){
            textures.put("Key:"+key.getName().split("\\.")[0], new Image(key.getPath()));
        }
        levelScreen = 0;
        files = new String[]{"Level1_1.txt", "Level1_2.txt", "Level1_3.txt"};
        ldp = new LevelDataParser(textures);
    }
    /**
     * GetObjects method from Level
     * @return ArrayList of all gameObjects
     * @throws IOException LevelDataParser throws an IOException
     */
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
    public void update() {}
    /**
     * Draws the level's background and assets
     */
    public void draw() {}
}