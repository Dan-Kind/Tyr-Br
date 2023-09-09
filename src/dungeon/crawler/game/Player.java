/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game;

import dungeon.crawler.ui.UIPanel;
import dungeon.crawler.util.GameObject;

/**
 *
 * @author 0607dacs
 */
public class Player extends GameObject{
    private int health;    // Player's health
    private UIPanel uiPanel;
    // Constructor to initialize the player's position and health
    public Player(int x, int y, int initialHealth) {
        super(x,y);
        health = initialHealth;
        
    }
    
    // Getter method for health
    public int getHealth() {
        return health;
    }

    // Move the player to a new position
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
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
    }
}

