/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler.ui;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 0607dacs
 */
public class CraftingPanel extends JPanel {
    public CraftingPanel() {
        setBackground(Color.LIGHT_GRAY);
        // Add your crafting components here
        // Create a new JLabel with the text "crafting"
        JLabel craftingLabel = new JLabel("<html><span style='font-size: 20px; color: white; text-shadow: 2px 2px black;'>crafting</span></html>");

        // Set the alignment of the JLabel to center
        craftingLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add the craftingLabel to the panel
        add(craftingLabel);
        
    }
}
