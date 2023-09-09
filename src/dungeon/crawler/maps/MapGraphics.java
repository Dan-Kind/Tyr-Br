/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.maps;

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
                        // Handle other map IDs (if necessary)
                    }
                }
                // Add more cases for other map IDs as needed
            }
        }
    }
}

    


