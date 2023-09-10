/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;

/**
 *
 * @author 0607dacs
 */
public class GameObject {
    protected int x;
    protected int y;
    protected int mapID;
    protected int objectID;
    
    public GameObject(int x, int y, int mapID, int objectID) {
        this.x = x;
        this.y = y;
        this.mapID = mapID;
        this.objectID = objectID;
    }

    // Common methods and properties for all game objects
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getMapID() {
        return mapID;
    }
    
    public int getObjectID(){
        return objectID;
    }
}
