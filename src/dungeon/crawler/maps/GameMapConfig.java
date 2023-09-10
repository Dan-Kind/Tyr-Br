package dungeon.crawler.maps;

import dungeon.crawler.game.objects.*;
import java.awt.Color;


public class GameMapConfig {
    public Player player;
    
    public GameMapConfig() {
        this.player = new Player(0, 0, 100, 1);
    }
    public GameMap createMap(int mapID) {
        //Initialize objects for map 1
        
        
        switch (mapID) {
            case 1:
                return createMap1();
            case 2:
                return createMap2();
            case 3:
                return createMap3();
            //Add more cases for other maps as needed
            default:
                throw new IllegalArgumentException("Invalid map ID: " + mapID);
        }
    }

    private GameMap createMap1() {
        GameMap map = new GameMap(10, 10, 1);

        
        Wall wall = new Wall(2, 2, 1);
        Portal portal = new Portal(5, 5, 1, 2);

        //Add objects to the map
        map.addObject(player);
        map.addObject(wall);
        map.addObject(portal);

        return map;
    }

    private GameMap createMap2() {
        GameMap map = new GameMap(5, 5, 2, "Heaven");

        //Initialize objects for map 2       
        Wall wall = new Wall(3, 3, 2);
        Portal portal1 = new Portal(3,1,2,1, Color.YELLOW);
        Portal portal2 = new Portal(3,4,2,3);
        //Add objects to the map
        map.addObject(wall);
        map.addObject(portal1);
        map.addObject(portal2);
        return map;
    }
    private GameMap createMap3() {
        GameMap map = new GameMap(20, 10, 3, "Mordor");

        //Initialize objects for map 3       
        Wall wall = new Wall(3, 3, 3);
        Wall wall2 = new Wall(10, 3, 3);
        Portal portal1 = new Portal(3,1,3,1, Color.YELLOW);
        Portal portal2 = new Portal(3,4,3,2);
        //Add objects to the map
        map.addObject(wall);
        map.addObject(wall2);
        map.addObject(portal1);
        map.addObject(portal2);
        return map;
    }
    
    
    public Player getPlayer(){
        return player;
    }
    //Add more methods for creating other maps and their object configurations
}
