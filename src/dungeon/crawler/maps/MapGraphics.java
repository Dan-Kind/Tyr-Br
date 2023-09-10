/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.maps;

import dungeon.crawler.game.objects.Portal;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author 0607dacs
 */
public class MapGraphics {
    
    public static void drawTextOnMap(String text, int fontSize ,Graphics g, int x, int y, int tileScale){
        /**
        * Draws text on a graphics context with an outline effect.
        *
        * @param text       The text to be displayed.
        * @param fontSize   The font size for the text.
        * @param g          The graphics context on which to draw.
        * @param x          The x-coordinate where the text should be centered.
        * @param y          The y-coordinate where the text should be centered.
        * @param tileScale  The scale of the tile on which the text is drawn.
        */
        Font font = new Font("Arial", Font.BOLD, fontSize);
        Color textColor = Color.WHITE;  // Fill color
        Color outlineColor = Color.BLACK;  // Outline color
        int outlineSize = 2;
        g.setFont(font);
        //calculate middle
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int textX = (int)((tileScale - textWidth) / 2) + x*tileScale;
        int textY = (int)((tileScale + fontMetrics.getAscent() ) /2) + y*tileScale;

        //drawing outline font
        g.setColor(outlineColor);
        for (int i = -outlineSize; i <= outlineSize; i++) {
            for (int j = -outlineSize; j <= outlineSize; j++) {
                if (i != 0 || j != 0) {
                    g.drawString(text, textX + i, textY + j);
                }
            }
        }
        //draw the main font
        g.setColor(textColor);
        g.drawString(text, textX, textY);
    }
    
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
                        drawTextOnMap("player", 12,g, x, y, tileScale);
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
                                
                                
                                //Draw which map portal leads to
                                String portalText = Integer.toString(portal.getToMapID());
                                drawTextOnMap(portalText, 30,g, x, y, tileScale);
                            }
                        }
                    }
                }
                // Add more cases for other map IDs as needed
            }
        }
    }
}

    


