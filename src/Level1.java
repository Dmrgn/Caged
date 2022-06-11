import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.scene.image.Image;
/**
 * <p>
 * This class creates the first level inheriting from the Level abstract class
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 19th, 2022
 * <p>
 * Ten minutes were spent by Ryan Atlas on this file on May 19th, 2022.
 * Two minutes were spent by Ryan Atlas on this file on May 27th adding the getNode() method
 * 30 minutes were spent by Ryan Atlas on May 30th-June 1st adding text file input and using this to test level file reading
 * </p>
 */
public class Level1 extends Level {
    public Level1() {
        textures = new HashMap();
        textures.put("Sign", new Image("assets/player.png"));

        textures.put("Mold:two", new Image("assets/molds/two.png"));
        textures.put("Mold:one", new Image("assets/molds/one.png"));

        textures.put("Door:doorClosed", new Image("assets/doors/doorClosed.png"));
        textures.put("Door:DoorSignLvl1", new Image("assets/doors/DoorSignLvl1.png"));
        textures.put("Door:doorOpen", new Image("assets/doors/doorOpen.png"));

        textures.put("Background:abyss", new Image("assets/backgrounds/abyss.png"));
        textures.put("Platform:ceiling2", new Image("assets/platforms/ceiling2.png"));
        
        textures.put("Platform:platformBump", new Image("assets/platforms/platformBump.png"));
        textures.put("Platform:rightTop", new Image("assets/platforms/rightTop.png"));
        textures.put("Platform:rottedSquare", new Image("assets/platforms/rottedSquare.png"));
        textures.put("Platform:rightPlatformLedge", new Image("assets/platforms/rightPlatformLedge.png"));
        textures.put("Platform:lowerCeiling", new Image("assets/platforms/lowerCeiling.png"));
        textures.put("Platform:rottedShortWall", new Image("assets/platforms/rottedShortWall.png"));
        textures.put("Platform:doorLeft", new Image("assets/platforms/doorLeft.png"));
        textures.put("Platform:overHangWall", new Image("assets/platforms/overHangWall.png"));
        textures.put("Platform:rottedRightBottomWall", new Image("assets/platforms/rottedRightBottomWall.png"));
        textures.put("Platform:rottedTopOverHang", new Image("assets/platforms/rottedTopOverHang.png"));
        textures.put("Platform:rottedTShapeTop", new Image("assets/platforms/rottedTShapeTop.png"));
        textures.put("Platform:spike", new Image("assets/platforms/spike.png"));
        textures.put("Platform:ledgeCrossSection1", new Image("assets/platforms/ledgeCrossSection1.png"));
        textures.put("Platform:rottedPlatform", new Image("assets/platforms/rottedPlatform.png"));
        textures.put("Platform:rottedClimber", new Image("assets/platforms/rottedClimber.png"));
        textures.put("Platform:rottedTShapeBottom", new Image("assets/platforms/rottedTShapeBottom.png"));
        textures.put("Platform:rightFloor", new Image("assets/platforms/rightFloor.png"));
        textures.put("Platform:bigWallRight", new Image("assets/platforms/bigWallRight.png"));
        textures.put("Platform:rottedShortFloor", new Image("assets/platforms/rottedShortFloor.png"));
        textures.put("Platform:fallFloor", new Image("assets/platforms/fallFloor.png"));
        textures.put("Platform:rottedMiddleOverHang", new Image("assets/platforms/rottedMiddleOverHang.png"));
        textures.put("Platform:pillar", new Image("assets/platforms/pillar.png"));
        textures.put("Platform:rottedWallRightHorn", new Image("assets/platforms/rottedWallRightHorn.png"));
        textures.put("Platform:thinBoiFloor", new Image("assets/platforms/thinBoiFloor.png"));
        textures.put("Platform:partialLeftWall", new Image("assets/platforms/partialLeftWall.png"));
        textures.put("Platform:hornInWall", new Image("assets/platforms/hornInWall.png"));
        textures.put("Platform:rightJumper", new Image("assets/platforms/rightJumper.png"));
        textures.put("Platform:rightBottom", new Image("assets/platforms/rightBottom.png"));
        textures.put("Platform:largeOverhang", new Image("assets/platforms/largeOverhang.png"));
        textures.put("Platform:overhangEdge", new Image("assets/platforms/overhangEdge.png"));
        textures.put("Platform:underHang", new Image("assets/platforms/underHang.png"));
        textures.put("Platform:invisible", new Image("assets/platforms/invisible.png"));
        textures.put("Platform:ceiling1", new Image("assets/platforms/ceiling1.png"));
        textures.put("Platform:bigDoor", new Image("assets/platforms/bigDoor.png"));
        textures.put("Platform:rottedTopPlatform", new Image("assets/platforms/rottedTopPlatform.png"));
        textures.put("Platform:rottedSlab", new Image("assets/platforms/rottedSlab.png"));
        textures.put("Platform:rottedLongFloor", new Image("assets/platforms/rottedLongFloor.png"));
        textures.put("Platform:rightFallWall", new Image("assets/platforms/rightFallWall.png"));
        textures.put("Platform:platform", new Image("assets/platforms/platform.png"));
        textures.put("Platform:leftFallWall", new Image("assets/platforms/leftFallWall.png"));
        textures.put("Platform:longFloor", new Image("assets/platforms/longFloor.png"));
        textures.put("Platform:rottedUnderHang", new Image("assets/platforms/rottedUnderHang.png"));
        textures.put("Platform:platformWithBump", new Image("assets/platforms/platformWithBump.png"));
        textures.put("Platform:ledgeCrossSection3", new Image("assets/platforms/ledgeCrossSection3.png"));
        textures.put("Platform:ledgeCrossSection2", new Image("assets/platforms/ledgeCrossSection2.png"));
        textures.put("Platform:bigSlabMissingRightFoot", new Image("assets/platforms/bigSlabMissingRightFoot.png"));
        textures.put("Platform:wallOutCrop", new Image("assets/platforms/wallOutCrop.png"));
        textures.put("Platform:rottedTopPlatformEdge", new Image("assets/platforms/rottedTopPlatformEdge.png"));
        textures.put("Platform:leftWallTall", new Image("assets/platforms/leftWallTall.png"));
        textures.put("Platform:rottedRightTopWall", new Image("assets/platforms/rottedRightTopWall.png"));
        textures.put("Platform:doorForehead", new Image("assets/platforms/doorForehead.png"));

        textures.put("Sign:Sign1", new Image("assets/signs/Sign1.png"));
        textures.put("Sign:QuesSign1", new Image("assets/signs/QuesSign1.png"));
        textures.put("Sign:Sign4", new Image("assets/signs/Sign4.png"));
        textures.put("Sign:Sign2", new Image("assets/signs/Sign2.png"));
        textures.put("Sign:Sign3", new Image("assets/signs/Sign3.png"));
        textures.put("Sign:Correct", new Image("assets/signs/Correct.png"));

        textures.put("Key:heart", new Image("assets/keys/heart.png"));
        textures.put("Key:heartout", new Image("assets/keys/heartout.png"));

        levelScreen = 0;
        files = new String[]{"Level1_1.txt", "Level1_2.txt", "Level1_3.txt"};
        ldp = new LevelDataParser(textures);
    }
    public ArrayList<GameObject> getObjects () throws IOException {
        return ldp.readFile(files[levelScreen]);
    }
    /**
     * Method for the user to play the game
     */
    public void play() {}
    /**
     * Updates the level's background and state
     */
    public void update() {

    }
    /**
     * Draws the level's background and assets
     */
    public void draw() {
    }
}