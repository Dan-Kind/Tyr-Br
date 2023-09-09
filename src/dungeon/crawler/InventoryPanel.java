/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon.crawler;

import java.awt.Color;
import java.awt.Dimension;
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
        
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    }
}
