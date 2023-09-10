/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.util;

import dungeon.crawler.game.objects.GameObject;
import dungeon.crawler.game.GamePanel;
import dungeon.crawler.game.objects.Player;
import dungeon.crawler.maps.GameMap;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.util.List;
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
    public void clearMap(GameMap currentMap) {
        for (int x = 0; x < currentMap.getWidth(); x++) {
            for (int y = 0; y < currentMap.getHeight(); y++) {
                if (currentMap.getValue(x, y) == 1) {
                    currentMap.setValue(x, y, 0); // Set the cell to 0 to remove the player
                }
            }
        }
    }
    public void updateMap(GameMap currentMap, Graphics g){
         // Clear the map
        currentMap.clearMap();
        // iterate over the objects, then do the currentMap.setValue(object.getX(),object.getY(),object.getObjectID());
        List<GameObject> objects = currentMap.getObjects();
        
        objects.forEach(object -> {
            int x = object.getX();
            int y = object.getY();
            int objectID = object.getObjectID();
            
            // Set the value for the object at (x, y) on the map
            currentMap.setValue(x, y, objectID);
        });
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
    public static int constructPortalID(int fromMapID, int toMapID) {
        // Construct the portal ID as "1001" + "0003" + "0005"
        String portalIDString = "1001" + String.format("%04d", fromMapID) + String.format("%04d", toMapID);
        return Integer.parseInt(portalIDString);
    }
}
