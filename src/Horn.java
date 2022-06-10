import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Horn extends CollidableObject {
    private static final int SPAWN_RATE = 90;
    int direction;
    Node node;
    Image img;
    public Horn(int x, int y, int direction) {
        pos = new Vector(x, y);
        this.direction = direction;
        node = new ImageView(img);
        img = new Image("assets/horn.png");
        createHitBox(pos, pos.add(new Vector((float)img.getWidth(), (float)img.getHeight())));
    }
    long frameCount = 0;
    @Override
    public void update() {
        if (frameCount % SPAWN_RATE == 0) {
            if (direction == -1) { // going to left
                Vector spawnPos = pos.add(new Vector(-40, 20));
                Game.attachObject(new Enemy(spawnPos.x, spawnPos.y), Game.SceneLayer.FOREGROUND);
            } else { // going to right
                Vector spawnPos = pos.add(new Vector((float)img.getWidth()+40, 20));
                Game.attachObject(new Enemy(spawnPos.x, spawnPos.y), Game.SceneLayer.FOREGROUND);
            }
        }
    }
    @Override
    public Node getNode() {
        return node;
    }
}
