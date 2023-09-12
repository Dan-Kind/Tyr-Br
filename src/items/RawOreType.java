/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author 0607dacs
 */
public enum RawOreType {
    RAW_IRON_ORE("Iron Ore", 5, 10),
    RAW_GOLD_ORE("Gold Ore", 8, 15),
    // Add more raw ore types as needed
    ;

    private final String name;
    private final int weight;
    private final int durability;

    RawOreType(String name, int weight, int durability) {
        this.name = name;
        this.weight = weight;
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getDurability() {
        return durability;
    }
}