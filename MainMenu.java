import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.shape.*;
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
    private ImageView menuChoicesImage;
    private Stage stage;
    /**
     * MainMenu constructor, initializes selection to 0 as a default
     */
    public MainMenu(Stage stage) {
        selection = 0;
        this.stage = stage; 
    }
    /**
     * Displays the graphics to the user
     */
    public void display() throws FileNotFoundException 
    {
        menuChoicesImage = new ImageView(new Image(new FileInputStream("ISP Splash Screen Images\\MainMenuScreen.png"))); 
        menuChoicesImage.setX(125); 
        menuChoicesImage.setY(150);
        menuChoicesImage.setFitHeight(1000); 
        menuChoicesImage.setFitWidth(1000); 
        menuChoicesImage.setPreserveRatio(true);
        Text title = new Text("Caged Inside the Mind");
        title.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 62));
        title.setFill(Color.WHITE);
        title.setX(350);
        title.setY(150);
        Rectangle background = new Rectangle(0, 0, 1280, 720);
        background.setFill(Color.BLACK);
        Group root = new Group(background, menuChoicesImage, title);
        Scene scene = new Scene(root, 600, 500); 
        stage.setScene(scene);
        RadialGradient backgroundGradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
            new Stop(0, Color.WHITE),
            new Stop(1, Color.BLACK)
        }); 
        scene.setFill(backgroundGradient);
        FadeTransition continueFade = new FadeTransition(Duration.millis(2000), background);
        continueFade.setCycleCount(FadeTransition.INDEFINITE);
        continueFade.setAutoReverse(true);
        continueFade.setFromValue(0);
        continueFade.setToValue(1);
        continueFade.play();
    }
    /**
     * Getter method for the selection instance variable
     * @return The user's selection
     */
    public int getSelection() {
        
        return selection;
    }
}