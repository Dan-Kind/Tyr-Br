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
    private final OreType oreType;
    private int durability;

    public Ore(int x, int y, int mapID, OreType oreType, int durability) {
        super(x, y, mapID, oreType.getId());
        this.oreType = oreType;
        this.durability = durability;
    }

    public GameItem getDrop() {
        // Implement drop mechanics based on the oreType
        return oreType.getDroppedItem();
    }
}
