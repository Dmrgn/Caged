import javafx.scene.*;
import javafx.scene.image.*;

public class FinalBoss extends Boss {
    public FinalBoss(float x, float y, Vector HITBOX_SIZE){
        super(200, x, y, HITBOX_SIZE);

    }
    @Override
    public void draw() {

    }

    @Override
    public Node getNode() {
        return boss;
    }

    @Override
    public void update() {

    }
}
