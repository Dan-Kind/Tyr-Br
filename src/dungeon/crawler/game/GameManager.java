package dungeon.crawler.game;

import dungeon.crawler.game.objects.Player;
import dungeon.crawler.maps.GameMap;
import dungeon.crawler.maps.GameMapConfig;
import java.util.List;
import java.util.ArrayList;

public class GameManager {
    public GameMap currentMap;
    public GameMapConfig gameMapConfig;
    public Player player;
    private List<GameMap> maps; // Your list of existing maps
    public GameManager() {
        this.gameMapConfig = new GameMapConfig();
        this.player = gameMapConfig.getPlayer();
        System.out.println(player);
        this.maps = new ArrayList<>();
        // Initialize the game manager with the initial map
        // Replace '1' with the initial map ID you want to load
        loadMap(1);
        
    }

    public void loadMap(int mapID) {
        // Load the map based on the provided mapID
        // You can implement this logic to create and initialize the map
        // For example, you can load maps from files or generate them procedurally
        // Initialize a boolean flag to indicate whether the map was found
        boolean mapFound = false;
        for (GameMap map : maps) {
            if (map.getMapID() == mapID) {
                // Found an existing map with the same ID, set it as the current map
                System.out.println("Found map");
                // Set the flag to true to indicate that the map was found
                mapFound = true;
                currentMap = map;
                break;
            }
        }
        if (!mapFound) {
            currentMap = createMap(mapID);
            maps.add(currentMap);
        }
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    private GameMap createMap(int mapID) {
        // Implement map creation logic here based on mapID
        // You can switch between different map instances based on the ID
        // For simplicity, we'll create a new map with the provided ID
        return gameMapConfig.createMap(mapID);
    }

    public void update() {
        // Update game logic here, such as handling player input, collision detection, etc.
        // You can also update other game elements and trigger map changes here
    }
    
    public Player getPlayer(){
        return player;
    }
    // Add more methods as needed for game management, e.g., handling map transitions, saving/loading game state, etc.
}
