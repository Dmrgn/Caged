/**
 * <p>
 * This class contains wrapper methods and abstractions for the graphics 
 * functions built into LWJGL. Methods are called statically to ensure
 * any class can access the UI.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 17th, 2022
 * <p>
 * File was created by Daniel Morgan on may 17th, 2022.
 * Daniel Morgan spent 2 hours on may 17th and 18th 2022.
 * </p>
 */

import static org.lwjgl.opengl.GL40.*;

public class Graphics {
    static void drawQuad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        glColor3f(0.0f, 0.0f, 1.0f);
        Vector p1 = (new Vector(x1, y1)).div(Main.getDims()).sub(new Vector(0.5f, 0.5f)).mul(2);
        Vector p2 = (new Vector(x2, y2)).div(Main.getDims()).sub(new Vector(0.5f, 0.5f)).mul(2);
        Vector p3 = (new Vector(x3, y3)).div(Main.getDims()).sub(new Vector(0.5f, 0.5f)).mul(2);
        Vector p4 = (new Vector(x4, y4)).div(Main.getDims()).sub(new Vector(0.5f, 0.5f)).mul(2);
        glBegin(GL_QUADS); {
            glVertex2f(p1.x, p1.y);
            glVertex2f(p2.x, p2.y);
            glVertex2f(p3.x, p3.y);
            glVertex2f(p4.x, p4.y);
        }
        glEnd();
    }
    static void drawRect(float x, float y, float width, float height) {
        drawQuad(x, y, x + width, y, x + width, y + height, x, y + height);
    }
}