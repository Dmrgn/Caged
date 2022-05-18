import static org.lwjgl.opengl.GL40.*;

public class Graphics {
    static void drawQuad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        glColor3f(0.0f, 0.0f, 1.0f);
        x1 = (x1/Main.getWidth())-1;
        x2 = (x2/Main.getWidth())-1;
        x3 = (x3/Main.getWidth())-1;
        x4 = (x4/Main.getWidth())-1;
        y1 = (y1/Main.getHeight())-1;
        y2 = (y2/Main.getHeight())-1;
        y3 = (y3/Main.getHeight())-1;
        y4 = (y4/Main.getHeight())-1;
        glBegin(GL_QUADS); {
            glVertex2f(x1, y1);
            glVertex2f(x2, y2);
            glVertex2f(x3, y3);
            glVertex2f(x4, y4);
        }
        glEnd();
    }
    static void drawRect(float x, float y, float width, float height) {
        drawQuad(x, y, x+width, y, x+width, y+height, x, y+height);
    }
}
