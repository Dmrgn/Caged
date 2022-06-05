/**
 * <p>
 * Button class that creates the pressing ability on any rectangle. 
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 28th, 2022
 * <p>
 * 30 minutes were spent on this file by Samuel Huang on June 1st 2022
 * </p>
 */
public class Button {
   public Vector p1;
   public Vector p2;
   public Button(Vector p1, Vector p2) {
      this.p1 = p1;
      this.p2 = p2;
   }

   public boolean hovering() {
      if (Keyboard.mouseCoords().x >= p1.x && Keyboard.mouseCoords().x <= p2.x && Keyboard.mouseCoords().y >= p1.y && Keyboard.mouseCoords().y <= p2.y) {
         return true;
      }
      return false;
   }

   public boolean pressed() {
      if (hovering() && Keyboard.isMouseClicked()) {
         System.out.println("work?");
         return true;
      }
      return false;
   }

}