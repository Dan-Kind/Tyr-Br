/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;

import items.GameItem;

/**
 *
 * @author 0607dacs
 */
public class Ore extends GameObject {
    GameItem itemDrop;
    public Ore(int x, int y, int mapID,  GameItem itemDrop) {
        super(x, y, mapID, 1002);
        this.itemDrop = itemDrop;
    }
    
    public GameItem getDrop(){
        //drop mechanics here
        return itemDrop;
    }
}
