import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
 * This class contains code for the SplashScreen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 4 hours on this file May 24th-30th
 * Smauel Huang spent 1 hour June 1st
 * </p>
 */
public class SplashScreen {
    /** Current stage*/
    private final Stage stage;
    /** This Image variable stores the logo image. */
    private Image logo;
    /** ImageView for logo*/
    private ImageView imageViewLogo;
    /** This Image array variable stores all the text images for the Logo Page Animation. */
    private Image[] screen1Text;
    /** ImageView array for text*/
    private ImageView[] imageViewScreen1Text;
    /** This Image variable stores the logo image. */
    private Image cage;
    /** ImageView for the cage*/
    private ImageView imageViewCage;
    /** Is the animation done?*/
    private boolean done = false;
    /**
     * The constructor of the Splash Screen Class.
     * Images are initialized to then be used in the splash screen.
     */
    public SplashScreen(Stage stage)  {
        this.stage = stage;
        screen1Text = new Image[2];
        imageViewScreen1Text = new ImageView[2];
    }

    /**
     * Imports the image
     * @throws FileNotFoundException In case files cannot be found
     */
    private void importImages() throws FileNotFoundException
    {
        logo = new Image(new FileInputStream("ISP Splash Screen Images/Logo.png"));
        imageViewLogo = new ImageView(logo);
        cage = new Image(new FileInputStream("ISP Splash Screen Images/Cage.png"));
        imageViewCage = new ImageView(cage);
        screen1Text[0] = new Image(new FileInputStream("ISP Splash Screen Images/CompanyName.png"));
        screen1Text[1] = new Image(new FileInputStream("ISP Splash Screen Images/Presents.png"));
        imageViewScreen1Text[0] = new ImageView(screen1Text[0]);
        imageViewScreen1Text[1] = new ImageView(screen1Text[1]);
    }
    /**
     * This method gets called to display and animate the company name and logo portion of the splash screen
     * @throws FileNotFoundException In case images cannot be found
     */
    public void animateCompName() throws FileNotFoundException {
        //Setting the position of the image LOGO:
        imageViewLogo.setX(450);
        imageViewLogo.setY(-350);
        imageViewLogo.setFitHeight(350);
        imageViewLogo.setFitWidth(350);
        imageViewLogo.setPreserveRatio(true);
        //Setting the position of textImage1
        imageViewScreen1Text[0].setX(460);
        imageViewScreen1Text[0].setY(-150);
        imageViewScreen1Text[0].setFitHeight(125);
        imageViewScreen1Text[0].setFitWidth(350);
        imageViewScreen1Text[0].setPreserveRatio(true);
        //Setting the position of textImage1
        imageViewScreen1Text[1].setX(545);
        imageViewScreen1Text[1].setY(525);
        imageViewScreen1Text[1].setFitHeight(40);
        imageViewScreen1Text[1].setFitWidth(180);
        imageViewScreen1Text[1].setPreserveRatio(true);
        //Creating a Group object
        Group root = new Group(imageViewLogo, imageViewScreen1Text[0], imageViewScreen1Text[1]);
        //Creating a scene object
        Scene scene = new Scene(root, Main.getWidth(), Main.getHeight());
        RadialGradient background = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE),
                new Stop(1, Color.BLACK));
        TranslateTransition translateCompName = new TranslateTransition();
        translateCompName.setNode(imageViewScreen1Text[0]);
        translateCompName.setDuration(Duration.millis(2000));
        translateCompName.setByY(250);
        //Translates the logo
        TranslateTransition translateLogo = new TranslateTransition();
        translateLogo.setNode(imageViewLogo);
        translateLogo.setDuration(Duration.millis(2000));
        translateLogo.setByY(500);
        scene.setFill(background);
        stage.setScene(scene);
        //Fades in Presents
        FadeTransition presents = new FadeTransition(Duration.millis(2000), imageViewScreen1Text[1]);
        presents.setFromValue(0);
        presents.setToValue(1);
        //Plays all animations in order with a time in between
        SequentialTransition transitionPart = new SequentialTransition (translateCompName,new PauseTransition(Duration.millis(1000)),translateLogo,
                new PauseTransition(Duration.millis(1000)),presents);  //8 seconds
        transitionPart.play();

    }

    /**
     * This method gets called to display and animate the transition animation from the company name to the logo.
     * @throws FileNotFoundException
     */
    public void transition() throws FileNotFoundException{
        //Fade out everything
        FadeTransition logoFade = new FadeTransition(Duration.millis(1000), imageViewLogo);
        FadeTransition word1Fade = new FadeTransition(Duration.millis(1000), imageViewScreen1Text[0]);
        FadeTransition word2Fade = new FadeTransition(Duration.millis(1000), imageViewScreen1Text[1]);
        logoFade.setToValue(0);
        word1Fade.setToValue(0);
        word2Fade.setToValue(0);
        SequentialTransition transitionPart = new SequentialTransition (new PauseTransition(Duration.millis(9000)), word1Fade, logoFade, word2Fade);  //9+3 = 12 seconds
        transitionPart.play();

    }
    /**
     * This method gets called to display and animate the game name of the splash screen
     * @throws FileNotFoundException In case image files cannot be found
     */
    public void animateGameName() throws FileNotFoundException {
        //Text 1
        Text title = new Text("Caged Inside the Mind");
        title.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 62));
        title.setFill(Color.WHITE);
        title.setX(350);
        title.setY(100);
        //Add Cage
        imageViewCage.setX(500);
        imageViewCage.setY(150);
        imageViewCage.setFitHeight(350);
        imageViewCage.setFitWidth(350);
        imageViewCage.setPreserveRatio(true);
        //Text bottom
        Text continueText = new Text("Please Hold Tight While the Game Loads...");
        continueText.setFont(Font.font ("Times New Roman", 36));
        continueText.setFill(Color.WHITE);
        continueText.setX(360);
        continueText.setY(600);
        //Rectangle Background
        Rectangle background = new Rectangle(0, 0, 1280, 720);
        background.setFill(Color.BLACK);
        //Creating a Group object
        Group root = new Group(background, title, continueText, imageViewCage);
        //Creating a scene object
        Scene scene = new Scene(root, Main.getWidth(), Main.getHeight());
      /*
      Timer timer = new Timer();
            TimerTask screen2 = new TimerTask() {
                @Override
                public void run() {
                     stage.setScene(scene);
                }
            };
            timer.schedule(screen2, 13000L);
            */
        RadialGradient backgroundGradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE),
                new Stop(1, Color.BLACK));
        scene.setFill(backgroundGradient);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(12000), ev -> {
            stage.setScene(scene);
        }));
        timeline.play();
        //animations
        FadeTransition titleFade = new FadeTransition(Duration.millis(1000), title);
        FadeTransition blackScreen = new FadeTransition(Duration.millis(2000), background);
        FadeTransition cageFade = new FadeTransition(Duration.millis(1000), imageViewCage);
        FadeTransition continueFade = new FadeTransition(Duration.millis(1000), continueText);
        blackScreen.setFromValue(0);
        blackScreen.setToValue(1);
        titleFade.setFromValue(0);
        titleFade.setToValue(1);
        cageFade.setFromValue(0);
        cageFade.setToValue(1);
        continueFade.setFromValue(0);
        continueFade.setToValue(1);
        SequentialTransition transitionParts = new SequentialTransition (new PauseTransition(Duration.millis(13000)), blackScreen, titleFade, cageFade, continueFade);
        transitionParts.play();
    }

    /**
     * Method to run splash screen
     * @throws FileNotFoundException In case images cannot be found
     */
    public void runSplashScreen() throws FileNotFoundException
    {
        importImages();
        animateCompName();
        transition();
        animateGameName();
    }
}