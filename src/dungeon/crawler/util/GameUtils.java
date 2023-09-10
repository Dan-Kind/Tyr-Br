/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.util;

import dungeon.crawler.game.GameManager;
import dungeon.crawler.game.objects.GameObject;
import dungeon.crawler.game.GamePanel;
import dungeon.crawler.game.objects.Player;
import dungeon.crawler.game.objects.Portal;
import dungeon.crawler.game.objects.Wall;
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
        int path = currentMap.getValue(newX, newY);

        if (path == 0) {
            // No collision
            return 1;
        } else if (path == 2) {
            
            // Collision with a wall
            int playerX = player.getX();
            int playerY = player.getY();

            // Calculate the direction of movement
            int deltaX = newX - playerX;
            int deltaY = newY - playerY;

            // Check if the adjacent cell in the direction of movement is empty (can be pushed)
            int adjacentCell = currentMap.getValue(newX + deltaX, newY + deltaY);
            System.out.println("Wall");
            if (adjacentCell == 0) {
                System.out.println("Clear behind wall");
                // Push the wall by moving the player and the wall to their new positions
                int wallX = newX;
                int wallY = newY;

                currentMap.setValue(playerX, playerY, 0); // Clear the player's current position
                currentMap.setValue(wallX, wallY, 1); // Move the player to the new position
                currentMap.setValue(wallX + deltaX, wallY + deltaY, 2); // Move the wall to the new position

                // Update the player's coordinates
                player.move(wallX, wallY);

                // Find and update the Wall object's coordinates
                System.out.println("list");
                List<GameObject> objects = currentMap.getObjects();
                for (GameObject object : objects) {
                    System.out.println("Object");
                    if (object instanceof Wall) {
                        int objectX = object.getX();
                        int objectY = object.getY();

                        System.out.println("Checking Wall at (" + objectX + ", " + objectY + ")");

                        if (objectX == wallX && objectY == wallY) {
                            Wall wall = (Wall) object;
                            wall.move(wallX + deltaX, wallY + deltaY);
                            System.out.println("Wall pushed to: " + (wallX + deltaX) + " ; " + (wallY + deltaY));
                        }
                    }
                }

                // Debugging output
                System.out.println("Player moved to: " + newX + " ; " + newY);

                return 1; // No collision, as the wall has been pushed
            } else {
                // Wall cannot be pushed as the adjacent cell is not empty
                return 2; // Return a code indicating a collision with an immovable object (wall)
            }
        } 
        else if (path >= 1001) {
            // Collision with a portal (assuming portal IDs are 1001 or higher)
            int portalId = path;

            // Find the portal object using the portal ID
            Portal portal = currentMap.getPortalById(portalId);

            if (portal != null) {
                // Get the destination map ID from the portal
                int destinationMapId = portal.getToMapID();

                // Switch to the new map by updating currentMap
                GameManager.loadMap(destinationMapId);

                // Move the player to the new map
                player.move(newX, newY);

                // Update the UI or perform other actions as needed
                // For example, you can update the UI to show the new map's name or other information.

                return 1; // Successful portal collision
        }
        else {
            // Collision with other objects (e.g., portals)
            player.takeDamage(1);
            return path;
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
        String portalIDString = "1001" + String.format("%03d", fromMapID) + String.format("%03d", toMapID);
        System.out.println(portalIDString);
        return Integer.parseInt(portalIDString);
    }
}
