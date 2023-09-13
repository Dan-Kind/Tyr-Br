/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game.objects;

import dungeon.crawler.ui.UIPanel;
import dungeon.crawler.game.objects.GameObject;
import dungeon.crawler.ui.InventoryPanel;
import items.GameItem;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author 0607dacs
 */
public class Player extends GameObject{
    private int health;    // Player's health
    private UIPanel uiPanel;
    private final Inventory inventory;
    private int strength;
    private int stamina;
    private int maxStamina;
    private int agility;
    private Timer staminaRegenTimer;
    // Constructor to initialize the player's position and health
    public Player(int x, int y, int initialHealth, int mapID) {
        super(x,y, mapID, 1);
        health = initialHealth;
        this.inventory = new Inventory(10);
        this.strength = 50;
        this.stamina = 100;
        this.agility = 30;
        this.maxStamina = 100;
        // Player timer
        // Initialize the stamina regeneration timer
        this.staminaRegenTimer = new Timer();
            staminaRegenTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    regenStamina();
                    
                }
            }, 1000, 1000);
    }
    
    // Getter method for health
    public int getHealth() {
        return health;
    }
    public void movePlayerStamina(){
        stamina -= (inventory.getWeight()*5/strength) + 10;
    }
    public void regenStamina(){
        int incrementAmount = agility; // Increment stamina by the player's agility value

        stamina += incrementAmount; // Increment stamina

        if (stamina > maxStamina) {
            stamina = maxStamina; // Ensure stamina doesn't exceed the maximum
        }
    }
    public boolean canMove(){
        if (stamina > ((inventory.getWeight()*5/strength) + 10)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Inventory getInventory(){
        return inventory;
    }

    // Damage the player by reducing their health
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        uiPanel.updateHealthLabel(health);
    }

    // Heal the player by increasing their health
    public void heal(int amount) {
        health += amount;
        // You can set a maximum health value here if needed.
    }

    // Check if the player is alive
    public boolean isAlive() {
        return health > 0;
    }
    public void updateUIPanel(UIPanel uiPanelInput){
        this.uiPanel = uiPanelInput;
        uiPanel.updateHealthLabel(health);
        uiPanel.updateStaminaLabel(stamina);
    }
    public void updateInventory(InventoryPanel inventoryPanel){
        inventoryPanel.updateInventory(inventory);
    }
     public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    // Method to check if an item can be added to the inventory based on strength
    public boolean canAddItem(GameItem item) {
        // Calculate the total weight of items in the inventory
        int totalWeight = inventory.getWeight() + item.getWeight();
        
        return totalWeight <= strength;
    }

    // Method to add an item to the inventory if feasible
    public boolean addItemToInventory(GameItem item) {
        if (canAddItem(item)) {
            return inventory.addItem(item);
        } else {
            System.out.println("Item is too heavy for your strength.");
            return false;
        }
    }
}

