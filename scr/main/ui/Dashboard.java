package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import scr.main.servies.ItemService;

public class Dashboard {
    private MainWindow main;
    private JPanel panel;
    private JLabel lostStatLabel;
    private JLabel foundStatLabel;

    public Dashboard(MainWindow main) {
        this.main = main;
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Header Title
        JLabel title = new JLabel("System Overview", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(44, 62, 80));
        panel.add(title, BorderLayout.NORTH);

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statsPanel.setOpaque(false);

        lostStatLabel = new JLabel("0", JLabel.CENTER);
        foundStatLabel = new JLabel("0", JLabel.CENTER);

        statsPanel.add(createStatCard("Total Lost", lostStatLabel, new Color(231, 76, 60)));
        statsPanel.add(createStatCard("Total Found", foundStatLabel, new Color(46, 204, 113)));

        // Buttons Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(new EmptyBorder(30, 0, 0, 0)); 

        JButton lostBtn = createStyledButton("Report Lost", new Color(41, 128, 185));
        JButton foundBtn = createStyledButton("Report Found", new Color(39, 174, 96));
        JButton searchBtn = createStyledButton("Database Search", new Color(142, 68, 173));

        lostBtn.addActionListener(e -> main.showLostForm());
        foundBtn.addActionListener(e -> main.showFoundForm());
        searchBtn.addActionListener(e -> main.showSearch());

        btnPanel.add(lostBtn);
        btnPanel.add(foundBtn);
        btnPanel.add(searchBtn);

        JPanel centerWrapper = new JPanel(new BorderLayout(0, 20));
        centerWrapper.setOpaque(false);
        centerWrapper.add(statsPanel, BorderLayout.CENTER);
        centerWrapper.add(btnPanel, BorderLayout.SOUTH);

        panel.add(centerWrapper, BorderLayout.CENTER);

        JButton logoutBtn = createStyledButton("Logout", Color.GRAY);
        logoutBtn.addActionListener(e -> main.showLogin());
        
        JPanel bottomWrap = new JPanel(new FlowLayout());
        bottomWrap.setOpaque(false);
        bottomWrap.add(logoutBtn);
        panel.add(bottomWrap, BorderLayout.SOUTH);
        
        refreshStats();
    }

    public void refreshStats() {
        lostStatLabel.setText(String.valueOf(ItemService.getLostCount()));
        foundStatLabel.setText(String.valueOf(ItemService.getFoundCount()));
    }

    private JPanel createStatCard(String title, JLabel vLabel, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        
        JLabel tLabel = new JLabel(title, JLabel.CENTER);
        tLabel.setForeground(Color.WHITE);
        tLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        vLabel.setForeground(Color.WHITE);
        vLabel.setFont(new Font("Arial", Font.BOLD, 36));

        card.add(tLabel, BorderLayout.NORTH);
        card.add(vLabel, BorderLayout.CENTER);
        return card;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setOpaque(true); 
        btn.setBorderPainted(false);
        return btn;
    }

    public JPanel getPanel() { return panel; }
}