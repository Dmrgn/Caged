import java.io.*;
import java.util.HashMap;
import javafx.scene.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * <p>
 * This class prevents us from having to hard code all of the level objects
 * by using .txt files to store level data and parsing them to something that can
 * be easily turned into Objects within the level.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 3.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 27th, 2022
 * <p>
 * File was created by Ryan Atlas on May 27th, 2022. 45 minutes were spent by Ryan Atlas.
 * Comments were added, variables created, the constructor was made and the readFile method
 * had most of its functionality (the file reading and the parsing of platforms, signs and doors) added.
 * 30 minutes were spent by Ryan Atlas on May 30th fixing file reading for some Objects
 * </p>
 */
public class LevelDataParser {
   /** Textures will have set keys for specific roles but different file paths so a HashMap is used to get the texture's file path*/
   private HashMap<String, Image> textures;
   /**
    * Constructor that initializes the textures HashMap.
    * @param textures The textures HashMap passed in by each level.
    */
   public LevelDataParser(HashMap<String, Image> textures){
      this.textures = textures;
   }
   /**
    * Method that reads the file and constructs a group of all of the objects
    * in the level with the correct texture and position.
    *
    * @param fileName The name of the file that contains the level data to be read.
    * @throws IOException To allow for file reading.
    * @return The Group of all objects in the level so it can be displayed as a scene.
    */
   public ArrayList<GameObject> readFile(String fileName) throws IOException {
      String line = "";
      BufferedReader fr = new BufferedReader(new FileReader(fileName));
      line = fr.readLine();
      ArrayList<GameObject> levelObjects = new ArrayList<GameObject>();
      while (line != null) {
         String[] data = line.split(", ");
         if (data[0].equals("Platform")){
            Platform p = new Platform(textures.get("Platform:"+data[1]), Boolean.parseBoolean(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            levelObjects.add(p);
         } else if (data[0].equals("Enemy1")){

         } else if (data[0].equals("Enemy2")){

         } else if (data[0].equals("Door")) {
            Door d = new Door(textures.get("DoorClosed"), textures.get("DoorOpen"), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            levelObjects.add(d);
         } else if (data[0].equals("Sign")) {
            Sign s = new Sign(textures.get("DoorClosed"), Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
            levelObjects.add(s);
         } else {
            throw new RuntimeException("Error parsing level data. Please redownload the level data files and ensure the data is not corrupted.");
         }
         line = fr.readLine();
      }
      fr.close();
      return levelObjects;
   }
}