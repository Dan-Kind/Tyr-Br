/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;

import items.GameItem;
import items.RawOre;
import static items.RawOreType.*;

/**
 *
 * @author 0607dacs
 */
public enum OreType {
    IRON_ORE(10020001, "Iron Ore", 5, new RawOre(null, RAW_IRON_ORE)),
    GOLD_ORE(10020002, "Gold Ore", 3, new RawOre(null, RAW_GOLD_ORE)),
    ;
    //SILVER_ORE(10020003, "Silver Ore", 4, GameItems.SILVER_ORE_ITEM);

    private final int id;
    private final String name;
    private final int hardness; // Hardness property
    private final GameItem droppedItem; // Specific game item dropped

    OreType(int id, String name, int hardness, GameItem droppedItem) {
        this.id = id;
        this.name = name;
        this.hardness = hardness;
        this.droppedItem = droppedItem;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHardness() {
        return hardness;
    }

    public GameItem getDroppedItem() {
        return droppedItem;
    }
    public static OreType getOreTypeById(int id) {
    for (OreType oreType : OreType.values()) {
        if (oreType.getId() == id) {
            return oreType;
        }
    }
    return null; // Return null if no matching ID is found
    }
}
