/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.maps;

import dungeon.crawler.game.objects.Portal;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 0607dacs
 */
public class MapGraphics {
    

    public static void drawMap(Graphics g, GameMap currentMap, int tileScale) {
        for (int x = 0; x < currentMap.getWidth(); x++) {
            for (int y = 0; y < currentMap.getHeight(); y++) {
                int mapId = currentMap.getValue(x, y); // Get the map ID at (x, y)

                // Draw different elements based on map ID
                switch (mapId) {
                    case 1 -> {
                        // Draw the player's position in blue
                        g.setColor(Color.BLUE);
                        g.fillRect(x * tileScale, y * tileScale, tileScale, tileScale);
                    }
                    case 2 -> {
                        // Draw another element (e.g., an obstacle) in a different color
                        g.setColor(Color.RED);
                        g.fillRect(x * tileScale, y * tileScale, tileScale, tileScale);
                    }
                  
                    default -> {
                        // Check if the portal ID starts with "1001"
                        String mapIdString = String.format("%04d", mapId);
                        if (mapIdString.startsWith("1001")) {
                            // Get the Portal object for this portal ID
                            Portal portal = currentMap.getPortalById(mapId);

                            if (portal != null) {
                                // Set the color based on the portal's color
                                g.setColor(portal.getPortalColor());
                                g.fillRect(x * tileScale, y * tileScale, tileScale, tileScale);
                            }
                        }
                    }
                }
                // Add more cases for other map IDs as needed
            }
        }
    }
}

    


