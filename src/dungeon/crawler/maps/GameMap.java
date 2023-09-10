/*
 *2D int array, each number can represent some sort of object.
  1 can be player 2 a portal so on so on. 
 */
package dungeon.crawler.maps;

import dungeon.crawler.game.objects.GameObject;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 0607dacs
 */
public class GameMap {
    private int[][] map; // This is a 2D array to represent your map
    private int depth;
    private int mapID;
    
    private List<GameObject> objects;
    
    // Constructor to initialize the map with dimensions (width x height)
    public GameMap(int height, int width, int mapID) {
        map = new int[height][width];
        this.mapID = mapID;
        this.objects = new ArrayList<>();
        // You can initialize the map with default values here, e.g., fill it with zeros.
        // You can also load map data from a file or generate it procedurally.
    }
    public void addObject(GameObject object) {
        objects.add(object);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    // Get the value at a specific position (x, y) on the map
    public int getValue(int x, int y) {
        if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
            return map[x][y];
        } else {
            // Handle out-of-bounds access gracefully, e.g., by returning a default value.
            return -1; // Or another appropriate default value
        }
    }

    // Set the value at a specific position (x, y) on the map
    public void setValue(int x, int y, int value) {
        if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
            map[x][y] = value;
        } else {
            // Handle out-of-bounds access gracefully, e.g., by doing nothing or displaying an error message.
        }
    }
    
    public void printMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                System.out.print(map[x][y] + " "); // Print the element at (i, j)
            }
            System.out.println(); // Move to the next row
        }
    }

       // Get the width of the map
    public int getWidth() {
        return map.length;
    }

    // Get the height of the map
    public int getHeight() {
        if (map.length > 0) {
            return map[0].length;
        } else {
            return 0; // Handle the case when the map has no height
        }
    }
    
    public int getMapID(){
        return mapID;
    }
    //Clear map
    
    public void clearMap(){
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[x][y] = 0;
            }
        }
    }
    // Other methods for map manipulation or querying can be added here.
}
