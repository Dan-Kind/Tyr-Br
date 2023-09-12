/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import dungeon.crawler.game.objects.GameObject;

/**
 *
 * @author 0607dacs
 */
public class RawOre extends GameItem {
    private final RawOreType oreType;

    public RawOre(GameObject owner, RawOreType oreType) {
        super(owner, oreType.getDurability(), oreType.getWeight(), oreType.getName());
        this.oreType = oreType;
    }

    public RawOreType getOreType() {
        return oreType;
    }
}
