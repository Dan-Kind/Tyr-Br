/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game;


import dungeon.crawler.game.objects.*;

import dungeon.crawler.maps.GameMap;
import dungeon.crawler.maps.MapGraphics;
import dungeon.crawler.ui.UIPanel;
import dungeon.crawler.util.GameUtils;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


//extends JPanel implements ActionListener, KeyListener


public class GamePanel extends JPanel implements ActionListener{

    private final Timer gameTimer; // Timer for game loop
     // Reference to the currently active map
    
    private GameManager gameManager;
    private final Player player;
    GameMap currentMap;
    private UIPanel uiPanel;
    public int tileScale = 50;
    public int SCREEN_HEIGHT;
    public int SCREEN_WIDTH;
    GameUtils gameUtils;
    private Graphics g;

    public GamePanel() {
        this.gameManager = new GameManager();
        this.gameUtils = new GameUtils(this, gameManager);
        this.player = gameManager.getPlayer();
        System.out.println(player);
        this.currentMap = gameManager.getCurrentMap();
        this.SCREEN_HEIGHT = tileScale*currentMap.getHeight();
        this.SCREEN_WIDTH = tileScale*currentMap.getWidth();
        // Set the preferred size of the panel (adjust this according to your game's requirements)
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        gameUtils.updateMap(currentMap, g);
        // Initialize the game timer (adjust the delay as needed for your desired frame rate)
        int delay = 16; // 16 milliseconds per frame (approximately 60 FPS)
        gameTimer = new Timer(delay, this);
        gameTimer.start();
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setVisible(true);
        
    }
    private void calculateTileScale() {
        int mapWidth = currentMap.getWidth();
        int mapHeight = currentMap.getHeight();
        int screenWidth = (int) (0.7* Toolkit.getDefaultToolkit().getScreenSize().width);
        int screenHeight = (int) (0.8* Toolkit.getDefaultToolkit().getScreenSize().height);

        // Calculate tileScale based on the map's width and height
        tileScale = Math.min(screenWidth / mapWidth, screenHeight / mapHeight);
        SCREEN_WIDTH = tileScale * mapWidth;
        SCREEN_HEIGHT = tileScale * mapHeight;
    }
    public void setUIPanel(UIPanel uiPanel) {
        this.uiPanel = uiPanel;
        player.updateUIPanel(uiPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draws drig and map
        gameUtils.drawGrid(g, tileScale, SCREEN_HEIGHT, SCREEN_WIDTH);
        MapGraphics.drawMap(g, currentMap, tileScale);
        
        //draw map name
        String mapName = currentMap.getMapName();
        Font font = new Font("Arial", Font.BOLD, 36);
        g.setFont(font);
        g.setColor(Color.WHITE);
        
        // Calculate the position to center the text horizontally
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(mapName);
        int textX = (SCREEN_WIDTH - textWidth) / 2;
        
        // Place the text just above the map
        int textY = fontMetrics.getHeight();
        
        g.drawString(mapName, textX, textY);
        
        
    }
   
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            // Handle key presses 
            int oldX = player.getX();
            int oldY = player.getY();
            int newX = oldX;
            int newY = oldY;

            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> // Move the player left by tileScale pixels
                    newX = oldX - 1;
                case KeyEvent.VK_RIGHT -> // Move the player right by tileScale pixels
                    newX = oldX + 1;
                case KeyEvent.VK_UP -> // Move the player up by tileScale pixels
                    newY = oldY - 1;
                case KeyEvent.VK_DOWN -> {
                    newY = oldY + 1;
                }
            }

            int path = gameUtils.collisionDetection(newX, newY, currentMap, player);
            if (path == 1){
                // The player can move to the new position
                player.move(newX, newY);
                
                gameUtils.updateMap(currentMap, g);
                System.out.println("Moved player without collision to:");
                System.out.println(player.getX() + " ; " + player.getY());
            }
            else if (path == 1001){
                //portal map change
                currentMap.removeObject(player);
                currentMap = gameManager.getCurrentMap();
                SCREEN_HEIGHT = tileScale*currentMap.getHeight();
                SCREEN_WIDTH = tileScale*currentMap.getWidth();
                player.moveToMap(currentMap.getMapID());
                currentMap.addObject(player);
                calculateTileScale();
                player.move(0, 0);
                gameUtils.updateMap(currentMap, g);
            }
            else {
                // The player couldn't move, so revert to the old position
                player.move(oldX, oldY);
                System.out.println("Player couldn't move, moved back to old cords. Collided with: " + path);

            }
        }
    }
}

