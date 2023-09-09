package dungeon.crawler;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UIPanel extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private GamePanel gamePanel;
    private JLabel healthLabel; // Declare a JLabel for displaying health
    
    public UIPanel(JPanel cardPanel, GamePanel gamePanel) {
        this.cardPanel = cardPanel;
        this.cardLayout = (CardLayout) cardPanel.getLayout();
        this.gamePanel = gamePanel;
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(this);
        add(inventoryButton);

        JButton craftingButton = new JButton("Crafting");
        craftingButton.addActionListener(this);
        add(craftingButton);
        
        // Initialize the health label
        healthLabel = new JLabel("Health: ");
        healthLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        healthLabel.setForeground(Color.RED); // Set foreground color
        add(healthLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("Inventory".equals(command)) {
            cardLayout.show(cardPanel, "Inventory");
        } else if ("Crafting".equals(command)) {
            cardLayout.show(cardPanel, "Crafting");
        }
        gamePanel.requestFocus();

    }

    void updateHealthLabel(int health) {
       healthLabel.setText("Health: " + health);
    }
}
