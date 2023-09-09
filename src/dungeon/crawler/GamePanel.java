/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


//extends JPanel implements ActionListener, KeyListener


public class GamePanel extends JPanel implements ActionListener{

    private final Timer gameTimer; // Timer for game loop
    
    GameMap map = new GameMap(11,10);
    private UIPanel uiPanel;
    Player player = new Player(0, 0, 100); // Init player (0;0) pos 100 health
    public final int tileScale = 50;
    public final int SCREEN_HEIGHT = tileScale*map.getHeight();
    public final int SCREEN_WIDTH = tileScale*map.getWidth();
    public GamePanel() {
        // Set the preferred size of the panel (adjust this according to your game's requirements)
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        
        map.setValue(2,2,2);
        updatePlayerMapPos(player.getX(),player.getY());
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
        drawGrid(g);
        
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int mapId = map.getValue(x, y); // Get the map ID at (x, y)

                // Draw different elements based on map ID
                switch (mapId) {
                    case 1:
                        // Draw the player's position in blue
                        g.setColor(Color.BLUE);
                        g.fillRect(x * tileScale, y * tileScale, tileScale, tileScale);
                        break;
                    case 2:
                        // Draw another element (e.g., an obstacle) in a different color
                        g.setColor(Color.RED);
                        g.fillRect(x * tileScale, y * tileScale, tileScale, tileScale);
                        break;
                    // Add more cases for other map IDs as needed
                    default:
                        // Handle other map IDs (if necessary)
                        break;
                }
            }
        }
    }
    
    public void drawGrid(Graphics g){
        g.setColor(Color.BLACK);
        //draw horizontal lines
        for(int i=0; i < SCREEN_HEIGHT/tileScale; i++){
                g.drawLine(0, i*tileScale, SCREEN_WIDTH, i*tileScale);
        }
        
        //draw vertical lines
        
        for(int i=0; i < SCREEN_WIDTH/tileScale; i++){
                
                g.drawLine(i*tileScale, 0, i*tileScale, SCREEN_HEIGHT);
        }
    }
    
    public void removeAllPlayers() {
    for (int x = 0; x < map.getWidth(); x++) {
        for (int y = 0; y < map.getHeight(); y++) {
            if (map.getValue(x, y) == 1) {
                map.setValue(x, y, 0);; // Set the cell to 0 to remove the player
            }
        }
    }
}
    
    public void updatePlayerMapPos(int x, int y){
         // Move the player within the GameMap class
        removeAllPlayers();
        map.setValue(x, y, 1);
        
        // Repaint the panel to update the player's position on the screen
        repaint();
    }
    
    public int collisionDetection(int newX, int newY ,GameMap map){
        /**
        * This is a Javadoc-style comment that describes the purpose of the method.
        *
        * @param param1 New player X cordinate
        * @param param2 New player Y cordinate
        * @param param3 The map you are checking
        * @return Returns 1 if there was no collisions, returns the objectid if there was a collision found
        * @throws SomeException Description of when and why an exception might be thrown.
        */
        int path = map.getValue(newX, newY);
        if (path == 0){
            //no collision
            return 1;
        }
        else {
            //collision
            player.takeDamage(1);
            return path;
            
        }
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
                int path = collisionDetection(player.getX(), player.getY(), map);
                if (path == 1){
                    updatePlayerMapPos(player.getX(), player.getY());
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

