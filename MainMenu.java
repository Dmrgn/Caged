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
 * 1 hour was spent by Samuel Huang on this file on May 30th, 2022.
 * </p>
 */

public class MainMenu {
    /** The user's selection from the menu*/
    private int selection;
    private Stage stage;

    private boolean[] buttonActive = new boolean[4];
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
    public void display() 
    {
        RadialGradient backgroundGradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE),
                new Stop(1, Color.BLACK));
        //menu options
        //original with gradient
        Rectangle newGameButtonO = new Rectangle(325, 225, 650, 60);
        newGameButtonO.setFill(backgroundGradient);
        newGameButtonO.setStroke(Color.WHITE);
        Rectangle instructionsButtonO = new Rectangle(325, 335, 650, 60);
        instructionsButtonO.setFill(backgroundGradient);
        instructionsButtonO.setStroke(Color.WHITE);
        Rectangle creditsButtonO = new Rectangle(325, 445, 650, 60);
        creditsButtonO.setFill(backgroundGradient);
        creditsButtonO.setStroke(Color.WHITE);
        Rectangle quitButtonO = new Rectangle(325, 555, 650, 60);
        quitButtonO.setFill(backgroundGradient);
        quitButtonO.setStroke(Color.WHITE);
        //layered
        Rectangle newGameButton = new Rectangle(325, 225, 650, 60);
        newGameButton.setFill(Color.BLACK);
        newGameButton.setStroke(Color.WHITE);
        Rectangle instructionsButton = new Rectangle(325, 335, 650, 60);
        instructionsButton.setFill(Color.BLACK);
        instructionsButton.setStroke(Color.WHITE);
        Rectangle creditsButton = new Rectangle(325, 445, 650, 60);
        creditsButton.setFill(Color.BLACK);
        creditsButton.setStroke(Color.WHITE);
        Rectangle quitButton = new Rectangle(325, 555, 650, 60);
        quitButton.setFill(Color.BLACK);
        quitButton.setStroke(Color.WHITE);
        //Text
        //Title
        Text title = new Text("Caged Inside the Mind");
        title.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 62));
        title.setFill(Color.WHITE);
        title.setX(350);
        title.setY(150);
        //Option 1
        Text newGameText = new Text("1. New Game");
        newGameText.setFont(Font.font ("Times New Roman", 28));
        newGameText.setFill(Color.WHITE);
        newGameText.setX(565);
        newGameText.setY(265);
        //Option 2
        Text instructionsText = new Text("2. Instructions");
        instructionsText.setFont(Font.font ("Times New Roman", 28));
        instructionsText.setFill(Color.WHITE);
        instructionsText.setX(560);
        instructionsText.setY(375);
        //Option 3
        //Option 2
        Text creditsText = new Text("3. Credits");
        creditsText.setFont(Font.font ("Times New Roman", 28));
        creditsText.setFill(Color.WHITE);
        creditsText.setX(585);
        creditsText.setY(485);
        //Option 4
        Text quitText = new Text("4. Quit");
        quitText.setFont(Font.font ("Times New Roman", 28));
        quitText.setFill(Color.WHITE);
        quitText.setX(600);
        quitText.setY(595);
        //background
        Rectangle background = new Rectangle(0, 0, 1270, 720);
        background.setFill(Color.BLACK);
        Group root = new Group(background, title, newGameButtonO, instructionsButtonO, creditsButtonO, quitButtonO, newGameButton, instructionsButton, creditsButton, quitButton, newGameText, instructionsText, creditsText, quitText);
        Scene scene = new Scene(root, 600, 500); 
        stage.setScene(scene);
        scene.setFill(backgroundGradient);
        //animate each button on rect
        //button 1
        FadeTransition newGameButtonFade = new FadeTransition(Duration.millis(2000), newGameButton);
        newGameButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        newGameButtonFade.setAutoReverse(true);
        newGameButtonFade.setFromValue(0);
        newGameButtonFade.setToValue(1);
        //for hovering animation stopping
        if(!buttonActive[0]) {
            newGameButtonFade.play();
        }
        //button 2
        FadeTransition instructionsButtonFade = new FadeTransition(Duration.millis(2000), instructionsButton);
        instructionsButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        instructionsButtonFade.setAutoReverse(true);
        instructionsButtonFade.setFromValue(0);
        instructionsButtonFade.setToValue(1);
        //for hovering animation stopping
        if(!buttonActive[1]) {
            instructionsButtonFade.play();
        }
        //button 3
        FadeTransition creditsButtonFade = new FadeTransition(Duration.millis(2000), creditsButton);
        creditsButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        creditsButtonFade.setAutoReverse(true);
        creditsButtonFade.setFromValue(0);
        creditsButtonFade.setToValue(1);
        //for hovering animation stopping
        if(!buttonActive[2]) {
            creditsButtonFade.play();
        }
        //button 4
        FadeTransition quitButtonFade = new FadeTransition(Duration.millis(2000), quitButton);
        quitButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        quitButtonFade.setAutoReverse(true);
        quitButtonFade.setFromValue(0);
        quitButtonFade.setToValue(1);
        //for hovering animation stopping
        if(!buttonActive[3]) {
            quitButtonFade.play();
        }
    }
    public void checkSelections()
    {
        Button button1 = new Button(new Vector(325, 225), new Vector(975, 285));//325, 225, 650, 60
        Button button2 = new Button(new Vector(325, 335), new Vector(975, 395));//325, 335, 650, 60
        Button button3 = new Button(new Vector(325, 445), new Vector(975, 505));//325, 445, 650, 60
        Button button4 = new Button(new Vector(325, 555), new Vector(975, 615));//325, 555, 650, 60
        //check for button 1
        if(button1.hovering())
        {
            //add animation to button being hovered on
            System.out.println("Hovering on 1");
            Arrays.fill(buttonActive, false);
            buttonActive[0] = true;
        }
        if(button1.pressed())
        {
            selection = 1;
            System.out.println("1 ");
        }
        //check for button 2
        if(button2.hovering())
        {
            //add animation to button being hovered on
            System.out.println("Hovering on 2");
            Arrays.fill(buttonActive, false);
            buttonActive[1] = true;
        }
        if(button2.pressed())
        {
            selection = 2;
            System.out.println("2");
        }
        //check for button 3
        if(button3.hovering())
        {
            //add animation to button being hovered on
            System.out.println("Hovering on 3");
            Arrays.fill(buttonActive, false);
            buttonActive[2] = true;
            
        }
        if(button3.pressed())
        {
            selection = 3;
            System.out.println("3");
        }
        //check for button 4
        if(button4.hovering())
        {
            //add animation to button being hovered on
            System.out.println("Hovering on 4");
            Arrays.fill(buttonActive, false);
            buttonActive[3] = true;
        }
        if(button4.pressed())
        {
            selection = 4;
            stage.close();
            System.out.println("4");
        }


    }
    /**
     * Getter method for the selection instance variable
     * @return The user's selection
     */
    public int getSelection() {
        //add mouse clicking functions here
        return selection;
    }
}
