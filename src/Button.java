/**
 * <p>
 * Button class that creates the pressing ability on any rectangle. 
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 5.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since June 1st, 2022
 * <p>
 * 30 minutes were spent on this file by Samuel Huang on June 1st 2022
 * </p>
 */
public class Button {
   /** Vector for top corner*/
   public Vector p1;
   /** Vector for bottom corner*/
   public Vector p2;
   /**
    * Constructor to initialize the vectors
    * @param p1 First vector
    * @param p2 Second vector
    */
   public Button(Vector p1, Vector p2) {
      this.p1 = p1;
      this.p2 = p2;
   }
   /**
    * Check if the mouse if hovering above the button
    * @return Whether the mouse is between those coordinates
    */
   public boolean hovering() {
      if (Keyboard.mouseCoords().x >= p1.x && Keyboard.mouseCoords().x <= p2.x && Keyboard.mouseCoords().y >= p1.y && Keyboard.mouseCoords().y <= p2.y) {
         return true;
      }
      return false;
   }
   /**
    * Check whether mouse is pressing the button
    * @return If the button is clicked
    */
   public boolean pressed() {
      if (hovering() && Keyboard.isMouseClicked()) {
         return true;
      }
      return false;
   }

}