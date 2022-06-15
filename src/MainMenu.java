import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * <p>
 * This file controls the main menu of the game. It is called by the Game class
 * and it's selection variable is used as part of the main game loop in the Main class
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * 2.5 hours was spent by Samuel Huang on this file on May 30th, 2022.
 * </p>
 */

public class MainMenu {
    /** The user's selection from the menu*/
    public int selection;
    /** Current stage*/
    private Stage stage;
    /** Button to start a new game*/
    private Rectangle newGameButton;
    /** Button to go to instructions screen*/
    private Rectangle instructionsButton;
    /** Button to go to credits screen*/
    private Rectangle creditsButton;
    /** Button to quit*/
    private Rectangle quitButton;
    /** Current scene*/
    private Scene scene;
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
        Rectangle newGameButton0 = new Rectangle(325, 225, 650, 60);
        newGameButton0.setFill(backgroundGradient);
        newGameButton0.setStroke(Color.WHITE);
        Rectangle instructionsButton0 = new Rectangle(325, 335, 650, 60);
        instructionsButton0.setFill(backgroundGradient);
        instructionsButton0.setStroke(Color.WHITE);
        Rectangle creditsButton0 = new Rectangle(325, 445, 650, 60);
        creditsButton0.setFill(backgroundGradient);
        creditsButton0.setStroke(Color.WHITE);
        Rectangle quitButton0 = new Rectangle(325, 555, 650, 60);
        quitButton0.setFill(backgroundGradient);
        quitButton0.setStroke(Color.WHITE);
        //layered

        newGameButton = new Rectangle(325, 225, 650, 60);
        newGameButton.setFill(Color.BLACK);
        newGameButton.setStroke(Color.WHITE);
        instructionsButton = new Rectangle(325, 335, 650, 60);
        instructionsButton.setFill(Color.BLACK);
        instructionsButton.setStroke(Color.WHITE);
        creditsButton = new Rectangle(325, 445, 650, 60);
        creditsButton.setFill(Color.BLACK);
        creditsButton.setStroke(Color.WHITE);
        quitButton = new Rectangle(325, 555, 650, 60);
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
        newGameText.setMouseTransparent(true);
        //Option 2
        Text instructionsText = new Text("2. Instructions");
        instructionsText.setFont(Font.font ("Times New Roman", 28));
        instructionsText.setFill(Color.WHITE);
        instructionsText.setX(560);
        instructionsText.setY(375);
        instructionsText.setMouseTransparent(true);
        //Option 3
        //Option 2
        Text creditsText = new Text("3. Credits");
        creditsText.setFont(Font.font ("Times New Roman", 28));
        creditsText.setFill(Color.WHITE);
        creditsText.setX(585);
        creditsText.setY(485);
        creditsText.setMouseTransparent(true);
        //Option 4
        Text quitText = new Text("4. Quit");
        quitText.setFont(Font.font ("Times New Roman", 28));
        quitText.setFill(Color.WHITE);
        quitText.setX(600);
        quitText.setY(595);
        quitText.setMouseTransparent(true);
        //background
        Rectangle background = new Rectangle(0, 0, 1270, 720);
        background.setFill(Color.BLACK);

        Group root = new Group(background, title, newGameButton0, instructionsButton0, creditsButton0, quitButton0, newGameButton, instructionsButton, creditsButton, quitButton, newGameText, instructionsText, creditsText, quitText);
        scene = new Scene(root);
        //stage.setScene(scene);
        scene.setFill(Color.BLACK);
        Keyboard.init(scene);

        //animate each button on rect
        //button 1
        FadeTransition newGameButtonFade = new FadeTransition(Duration.millis(2000), newGameButton);
        newGameButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        newGameButtonFade.setAutoReverse(true);
        newGameButtonFade.setFromValue(0);
        newGameButtonFade.setToValue(1);
        //for hovering animation stopping
        newGameButtonFade.play();
        //button 2
        FadeTransition instructionsButtonFade = new FadeTransition(Duration.millis(2000), instructionsButton);
        instructionsButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        instructionsButtonFade.setAutoReverse(true);
        instructionsButtonFade.setFromValue(0);
        instructionsButtonFade.setToValue(1);
        //for hovering animation stopping
        instructionsButtonFade.play();
        //button 3
        FadeTransition creditsButtonFade = new FadeTransition(Duration.millis(2000), creditsButton);
        creditsButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        creditsButtonFade.setAutoReverse(true);
        creditsButtonFade.setFromValue(0);
        creditsButtonFade.setToValue(1);
        //for hovering animation stopping
        creditsButtonFade.play();

        //button 4
        FadeTransition quitButtonFade = new FadeTransition(Duration.millis(2000), quitButton);
        quitButtonFade.setCycleCount(FadeTransition.INDEFINITE);
        quitButtonFade.setAutoReverse(true);
        quitButtonFade.setFromValue(0);
        quitButtonFade.setToValue(1);
        //for hovering animation stopping
        quitButtonFade.play();
    }
    /**
     * Animates the main menu
     * @param timer Timer for buttons
     * @param mainTimer Timer for main game loop
     */
    public void checkMenu(AnimationTimer timer, AnimationTimer mainTimer)
    {
        /******** Buttons on the main menu ********/
        newGameButton.setOnMousePressed(
                e ->{
                    try {
                        selection = 1;
                        timer.stop();
                        mainTimer.start();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        instructionsButton.setOnMousePressed(
                e ->{
                    try {
                        selection = 2;
                        timer.stop();
                        mainTimer.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        creditsButton.setOnMousePressed(
                e ->{
                    try {
                        selection = 3;
                        timer.stop();
                        mainTimer.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        quitButton.setOnMousePressed(
                e ->{
                    try {
                        timer.stop();
                        mainTimer.start();
                        stage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
    }
    /**
     * Getter method for the selection instance variable
     * @return The user's selection
     */
    public int getSelection() {
        //add mouse clicking functions here
        return selection;
    }
    public void setSelection(int selection)
    {
        this.selection = selection;
    }
    /**
     * Getter method for the scene instance variable
     * @return The game scene
     */
    public Scene getScene()
    {
        return scene;
    }
}