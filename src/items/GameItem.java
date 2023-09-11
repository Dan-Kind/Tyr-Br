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
public class GameItem {
    private GameObject owner;
    private boolean equipped;
    private String name;
    private int durability;
    private int weight;
     /**
     * Creates a new `GameItem` with the specified attributes.
     *
     * @param owner     The game object that owns or possesses this item.
     * @param durability The durability or condition of the item.
     * @param weight    The weight of the item.
     * @param name      The name of the game item.
     */
    public GameItem(GameObject owner, int durability, int weight, String name){
        this.equipped = false;
        this.owner = owner;
        this.durability = durability;
        this.weight = weight;
        this.name = name;
    }
    
    // Getter methods for the properties
    public GameObject getOwner() {
        return owner;
    }
    
    public boolean isEquipped() {
        return equipped;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDurability() {
        return durability;
    }
    
    public int getWeight() {
        return weight;
    }
    
    // setter
    public void setOwner(GameObject owner) {
        this.owner = owner;
    }
}
