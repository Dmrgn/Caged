import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * <p>
 * This class contains code for the Instructions Screen
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 4.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * File was created by Samuel Huang on May 19th, 2022.
 * Samuel Huang spent 10 minutes on this file.
 * </p>
 */
public class Instructions {
   /** This ImageView variable stores the first instructions page. */
   private ImageView instructionsP1;
   /** This ImageView variable stores the second instructions page. */
   private ImageView instructionsP2;
   /** This int variable keeps track of the current page the user is reading */
   private int currentPage;
   /** This Stage variable is used to display all things */
   private Stage window;
   /** Current scene */
   private Scene scene;
   /**
     * The constructor of the Instructions Class.
     */
   public Instructions(Stage window) throws FileNotFoundException {
      instructionsP1 = new ImageView( new Image(new FileInputStream("Page Screens/InstructionsScreen1.png")));//add file first
      instructionsP2 = new ImageView( new Image(new FileInputStream("Page Screens/InstructionsScreen2.png")));//add file first
      this.window = window;
      currentPage = 1;
   }
   /**
     * This method gets called to display the first instructions screen
     *
     */
   public void displayScreen1() {
      currentPage = 1;
      //Setting the position of the  image:
      instructionsP1.setX(0);
      instructionsP1.setY(0);
      instructionsP1.setFitWidth(1266);
      instructionsP1.setFitHeight(720);
      instructionsP1.setPreserveRatio(true);
      Group root = new Group(instructionsP1);
      //Creating a scene object
      scene = new Scene(root, Main.getWidth(), Main.getHeight());
      //window.setScene(scene);
      Keyboard.init(scene);
   }
   /**
     * This method gets called to display the second instructions screen
     *
     */
   public void displayScreen2() {
      currentPage = 2;
      //Setting the position of the  image:
      instructionsP2.setX(0);
      instructionsP2.setY(0);
      instructionsP2.setFitWidth(1266);
      instructionsP2.setFitHeight(720);
      instructionsP2.setPreserveRatio(true);
      Group root = new Group(instructionsP2);
      //Creating a scene object
      scene = new Scene(root, Main.getWidth(), Main.getHeight());
      //window.setScene(scene);
      Keyboard.init(scene);
   }
   /**
     * This method swaps between different screens
     *
     */
   public void controlScreens() {
      //check the keyboard
      if(Keyboard.isKeyDown(KeyCode.C)) {
         currentPage = 2;
      }
      else if(Keyboard.isKeyDown(KeyCode.I)) {
         currentPage = 1;
      }
      //displays screens
      if(currentPage == 1) {
         displayScreen1();
      }
      else if (currentPage == 2) {
         displayScreen2();
      }
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