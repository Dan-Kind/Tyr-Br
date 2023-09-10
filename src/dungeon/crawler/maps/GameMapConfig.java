package dungeon.crawler.maps;

import dungeon.crawler.game.objects.*;
import dungeon.crawler.maps.GameMap;

public class GameMapConfig {

    public static GameMap createMap(int mapID) {
        switch (mapID) {
            case 1:
                return createMap1();
            case 2:
                return createMap2();
            // Add more cases for other maps as needed
            default:
                throw new IllegalArgumentException("Invalid map ID: " + mapID);
        }
    }

    private static GameMap createMap1() {
        GameMap map = new GameMap(10, 10, 1);

        // Initialize objects for map 1
        Player player = new Player(0, 0, 100, 1);
        Wall wall = new Wall(2, 2, 1);
        Portal portal = new Portal(5, 5, 1, 2);

        // Add objects to the map
        map.addObject(player);
        map.addObject(wall);
        map.addObject(portal);

        return map;
    }

    private static GameMap createMap2() {
        GameMap map = new GameMap(5, 5, 2);

        // Initialize objects for map 2
        Player player = new Player(1, 1, 100, 2);
        Wall wall = new Wall(3, 3, 2);
        // Add objects to the map
        map.addObject(player);
        map.addObject(wall);

        return map;
    }

    // Add more methods for creating other maps and their object configurations
}
