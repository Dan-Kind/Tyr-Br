/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.util;

import dungeon.crawler.game.GamePanel;
import dungeon.crawler.game.Player;
import dungeon.crawler.maps.GameMap;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
/**
 *
 * @author 0607dacs
 */
public class GameUtils {
    private final GamePanel gamePanel;
    public GameUtils(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    public int collisionDetection(int newX, int newY, GameMap currentMap, Player player) {
          /**
        * This is a Javadoc-style comment that describes the purpose of the method.
        *
        * @param param1 New player X cordinate
        * @param param2 New player Y cordinate
        * @param param3 The map you are checking
        * @return Returns 1 if there was no collisions, returns the objectid if there was a collision found
        * @throws SomeException Description of when and why an exception might be thrown.
        */
        int path = currentMap.getValue(newX, newY);
        if (path == 0) {
            // No collision
            return 1;
        } else {
            // Collision
            player.takeDamage(1); // Note: You need to pass the player reference here or make it accessible.
            return path;
        }
    }
    public void removeAllPlayers(GameMap currentMap) {
        for (int x = 0; x < currentMap.getWidth(); x++) {
            for (int y = 0; y < currentMap.getHeight(); y++) {
                if (currentMap.getValue(x, y) == 1) {
                    currentMap.setValue(x, y, 0); // Set the cell to 0 to remove the player
                }
            }
        }
    }
    public void updatePlayerMapPos(int x, int y, GameMap currentMap, Graphics g){
         // Move the player within the GameMap class
        removeAllPlayers(currentMap);
        currentMap.setValue(x, y, 1);
        
        // Repaint the panel to update the player's position on the screen
        gamePanel.repaint();
    }
    
    public void drawGrid(Graphics g, int tileScale, int SCREEN_HEIGHT, int SCREEN_WIDTH) {
        g.setColor(Color.BLACK);

        // Draw horizontal lines
        for (int i = 0; i < SCREEN_HEIGHT / tileScale; i++) {
            g.drawLine(0, i * tileScale, SCREEN_WIDTH, i * tileScale);
        }

        // Draw vertical lines
        for (int i = 0; i < SCREEN_WIDTH / tileScale; i++) {
            g.drawLine(i * tileScale, 0, i * tileScale, SCREEN_HEIGHT);
        }
    }
}
