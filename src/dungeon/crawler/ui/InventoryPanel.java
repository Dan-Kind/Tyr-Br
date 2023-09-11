/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.ui;

import java.awt.Color;
import java.awt.Dimension;
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

        // Set the alignment of the JLabel to center
        inventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the inventoryLabel to the panel
        add(inventoryLabel);
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    }
}
