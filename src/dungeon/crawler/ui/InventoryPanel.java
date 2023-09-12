/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.ui;

import items.GameItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 0607dacs
 */
public class InventoryPanel extends JPanel{
    public final int SCREEN_HEIGHT = 500;
    public final int SCREEN_WIDTH = 220;
     public InventoryPanel() {
        setBackground(Color.CYAN);
        // Add your inventory components here
        // Create a new JLabel with the text "inventory"
        JLabel inventoryLabel = new JLabel("<html><span style='font-size: 20px; color: white; text-shadow: 2px 2px black;'>inventory</span></html>");
        setLayout(new GridLayout(0, 1)); // Use a vertical layout for items
        // Set the alignment of the JLabel to center
        inventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the inventoryLabel to the panel
        add(inventoryLabel);
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    }
    public void updateInventory(List<GameItem> inventory) {
        removeAll(); // Remove all previous inventory items from the panel
        
        // Add a label for each item in the updated inventory
        for (GameItem item : inventory) {
            JLabel itemLabel = new JLabel(item.getName());
            itemLabel.setHorizontalAlignment(JLabel.CENTER);
            add(itemLabel);
        }
        
        revalidate(); // Revalidate the panel to update its layout
        repaint();   // Repaint the panel to reflect changes
    }
}
