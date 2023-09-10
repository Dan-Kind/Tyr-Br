/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.game;

import dungeon.crawler.game.objects.Player;
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
    
    GameMap map = new GameMap(11,10, 0);
    GameMap currentMap = map;
    private UIPanel uiPanel;
    Player player = new Player(0, 0, 100, 0); // Init player (0;0) pos 100 health
    public final int tileScale = 50;
    public final int SCREEN_HEIGHT = tileScale*map.getHeight();
    public final int SCREEN_WIDTH = tileScale*map.getWidth();
    GameUtils gameUtils = new GameUtils(this);
    private Graphics g;
    public GamePanel() {
        // Set the preferred size of the panel (adjust this according to your game's requirements)
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        
        map.setValue(2,2,2);
        gameUtils.updateMap(currentMap, g);
        // Initialize the game timer (adjust the delay as needed for your desired frame rate)
        int delay = 16; // 16 milliseconds per frame (approximately 60 FPS)
        gameTimer = new Timer(delay, this);
        gameTimer.start();
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setVisible(true);
        
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
        // This method is called when the panel needs to be repainted
        // Put your rendering code here to draw game objects
        
        // For example, you can draw the player at their current position
        gameUtils.drawGrid(g, tileScale, SCREEN_HEIGHT, SCREEN_WIDTH);
        MapGraphics.drawMap(g, currentMap, tileScale);
    }
   
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            // Handle key presses 
            int oldX = player.getX();
            int oldY = player.getY();
            
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> // Move the player left by tileScale pixels
                    player.move(player.getX() - 1, player.getY());
                case KeyEvent.VK_RIGHT -> // Move the player right by tileScale pixels
                    player.move(player.getX() + 1, player.getY());
                case KeyEvent.VK_UP -> // Move the player up by tileScale pixels
                    player.move(player.getX() , player.getY() - 1);
                case KeyEvent.VK_DOWN -> {
                    player.move(player.getX() , player.getY() + 1);
                    }
                }
                int path = gameUtils.collisionDetection(player.getX(), player.getY(), currentMap, player);
                if (path == 1){
                    gameUtils.updateMap(currentMap, g);
                    System.out.println("Moved player without collision to:");
                    System.out.println(player.getX() + " ; " + player.getY());
                }
                else {
                    player.move(oldX, oldY);
                    System.out.println("Player couldnt move, moved back to old cords. Collided with: " + path);
                }
                
            }
    }
}

