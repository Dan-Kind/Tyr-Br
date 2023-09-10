/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;

import dungeon.crawler.game.objects.GameObject;
import dungeon.crawler.util.GameUtils;
import java.awt.Color;

/**
 *
 * @author 0607dacs
 */

public class Portal extends GameObject {
    private final int fromMapID;
    private final int toMapID;
    private final int portalID;
    private final Color color;
    
    //Default color
    public Portal(int x, int y, int fromMapID, int toMapID) {
        super(x, y, fromMapID, GameUtils.constructPortalID(fromMapID, toMapID));
        this.fromMapID = fromMapID;
        this.toMapID = toMapID;
        this.portalID = GameUtils.constructPortalID(fromMapID, toMapID);
        this.color = new Color(127,0,153);
    }
    
    public Portal(int x, int y, int fromMapID, int toMapID, Color color) {
        super(x, y, fromMapID, GameUtils.constructPortalID(fromMapID, toMapID));
        this.fromMapID = fromMapID;
        this.toMapID = toMapID;
        this.portalID = GameUtils.constructPortalID(fromMapID, toMapID);
        this.color = color;
    }
    
    public Color getPortalColor(){
        return color;
    }
    
    public int getPortalID() {
        return portalID;
    }
    public int getFromMapID(){
        return fromMapID;
    }
    public int toFromMapID(){
        return toMapID;
    }
}
