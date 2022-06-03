import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * <p>
 * This class contains code for the Instructions Screen
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
public class Instructions
{
   /** This Image variable stores the first instructions page. */
   private ImageView instructionsP1;
   /** This Image variable stores the second instructions page. */
   private ImageView instructionsP2;
   /** This int variable keeps track of the current page the user is reading */
   private int currentPage;
   Stage window;
   
   /**
     * The constructor of the Instructions Class.
     */
   public Instructions(Stage window) throws FileNotFoundException {
      //instructionsP1 = new ImageView( new Image(new FileInputStream("ISP Splash Screen Images\\Instruction1.png")));//add file first
      //instructionsP2 = new ImageView( new Image(new FileInputStream("ISP Splash Screen Images\\Instruction2.png")));//add file first
      this.window = window;
   }
   
   /**
     * This method gets called to display the first instructions screen
     *
     */
   public void displayScreen1()
   {
      currentPage = 1;
      //Setting the position of the  image:
      instructionsP1.setX(0);
      instructionsP1.setY(0);
      instructionsP1.setFitHeight(780);
      instructionsP1.setFitWidth(1260);
      instructionsP1.setPreserveRatio(true);
      Group root = new Group(instructionsP1);
      //Creating a scene object
      Scene scene = new Scene(root, 600, 500);
      window.setScene(scene);
   }
   /**
     * This method gets called to display the second instructions screen
     *
     */
   public void displayScreen2()
   {
      currentPage = 2;
      //Setting the position of the  image:
      instructionsP2.setX(0);
      instructionsP2.setY(0);
      instructionsP2.setFitHeight(780);
      instructionsP2.setFitWidth(1260);
      instructionsP2.setPreserveRatio(true);
      Group root = new Group(instructionsP2);
      //Creating a scene object
      Scene scene = new Scene(root, 600, 500);
      window.setScene(scene);
   }
   /**
     * This method swaps between different screens
     *
     */
   public void switchScreen()
   {
      if(currentPage == 1)
      {
         displayScreen2();
      }
      else if(currentPage == 2)
      {
         displayScreen1();
      }
   }
   /**
     * This method sends the user back to the menu page.
     *
     */
   public void back()
   {
   
   }
}