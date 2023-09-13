/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.ui;
import dungeon.crawler.game.GamePanel;
import dungeon.crawler.game.GamePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class GameFrame extends JFrame {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public GameFrame() {
        this.setTitle("Dungeon Crawler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Set the frame's size explicitly
        this.setLayout(new BorderLayout()); // Set the layout manager
        this.setLocationRelativeTo(null);
        this.requestFocusInWindow();

        JPanel cardPanel = new JPanel(new CardLayout());
        InventoryPanel inventoryPanel = new InventoryPanel();
        CraftingPanel craftingPanel = new CraftingPanel();
        
        cardPanel.add(inventoryPanel, "Inventory");
        cardPanel.add(craftingPanel, "Crafting");

        // Create the GamePanel and add it to the CENTER position
        GamePanel gamePanel = new GamePanel();
        
        gamePanel.setBackground(Color.GREEN);
        this.add(gamePanel, BorderLayout.CENTER);

        // Create the UIPanel and pass the cardPanel to it
        UIPanel uiPanel = new UIPanel(cardPanel, gamePanel);
        uiPanel.setBackground(Color.BLUE);
        gamePanel.setUIPanel(uiPanel);
        gamePanel.setInventoryPanel(inventoryPanel);
        this.add(uiPanel, BorderLayout.SOUTH);
        
        this.add(cardPanel, BorderLayout.EAST);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Create an instance of the GameFrame class
        GameFrame gameFrame = new GameFrame();
    }
}

