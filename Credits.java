import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <p>
 * This class contains code for the Credits Screen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 10 minutes on this file.
 * </p>
 */
public class Credits {
    /** This Image variable stores the credits page. */
    private ImageView screen;
    private Stage window;
    /**
     * The constructor of the Credits Class.
     */
    public Credits(Stage window) throws FileNotFoundException {
        //screen = new ImageView( new Image(new FileInputStream("ISP Splash Screen Images\\Credits.png")));//add file first
        this.window = window;
    }
    /**
     * This method gets called to display the credits screen
     *
     */
    public void display() throws FileNotFoundException{
        //Setting the position of the image:
        screen.setX(0);
        screen.setY(0);
        screen.setFitHeight(780);
        screen.setFitWidth(1260);
        screen.setPreserveRatio(true);
        Group root = new Group(screen);
        //Creating a scene object
        Scene scene = new Scene(root, 600, 500);
        window.setScene(scene);
    }
    /**
     * This method sends the user back to the menu screen
     *
     */
    public void back() {}
}