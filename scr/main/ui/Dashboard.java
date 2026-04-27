package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import scr.main.servies.ItemService;

public class Dashboard {
    private MainWindow main;
    private JPanel panel;
    private JLabel lostStatLabel;
    private JLabel foundStatLabel;

    // --- REFINED COLOR PALETTE ---
    private final Color CLR_BG         = new Color(243, 244, 246); // Off-white/Gray
    private final Color CLR_SIDEBAR    = new Color(31, 41, 55);    // Deep Slate
    private final Color CLR_TEXT_DARK  = new Color(17, 24, 39);    // Near Black
    private final Color CLR_TEXT_LIGHT = new Color(107, 114, 128); // Muted Gray
    
    private final Color CLR_PRIMARY    = new Color(63, 131, 248);  // Modern Blue
    private final Color CLR_LOST       = new Color(249, 128, 128); // Soft Red
    private final Color CLR_FOUND      = new Color(49, 196, 141);  // Emerald Green
    private final Color CLR_SEARCH     = new Color(159, 122, 234); // Soft Purple

    public Dashboard(MainWindow main) {
        this.main = main;
        panel = new JPanel(new BorderLayout());
        panel.setBackground(CLR_BG);

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(230, 0));
        sidebar.setBackground(CLR_SIDEBAR);
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel logo = new JLabel("LOST & FOUND");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Inter", Font.BOLD, 18));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(50));

        // Navigation Buttons
        sidebar.add(createSidebarBtn("Dashboard", true));
        
        sidebar.add(Box.createVerticalGlue());
        
        JButton logout = createSidebarBtn("Exit System", false);
        logout.addActionListener(e -> main.showLogin());
        sidebar.add(logout);

        // --- MAIN CONTENT ---
        JPanel content = new JPanel(new BorderLayout(0, 30));
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(40, 50, 40, 50));

        // Header Section
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("System Overview");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(CLR_TEXT_DARK);
        header.add(title, BorderLayout.WEST);
        content.add(header, BorderLayout.NORTH);

        // Stats Row
        JPanel statsRow = new JPanel(new GridLayout(1, 2, 30, 0));
        statsRow.setOpaque(false);

        lostStatLabel = new JLabel("0");
        foundStatLabel = new JLabel("0");

        statsRow.add(createStatCard("LOST ITEMS", lostStatLabel, CLR_LOST));
        statsRow.add(createStatCard("FOUND ITEMS", foundStatLabel, CLR_FOUND));
        content.add(statsRow, BorderLayout.CENTER);

        // Quick Actions Row
        JPanel actionRow = new JPanel(new GridLayout(1, 3, 20, 0));
        actionRow.setOpaque(false);
        actionRow.setPreferredSize(new Dimension(0, 60));

        JButton btnReportLost = createActionButton("Report Lost", CLR_PRIMARY);
        btnReportLost.addActionListener(e -> main.showLostForm());
        actionRow.add(btnReportLost);

        JButton btnReportFound = createActionButton("Report Found", CLR_FOUND);
        btnReportFound.addActionListener(e -> main.showFoundForm());
        actionRow.add(btnReportFound);

        JButton btnSearchDb = createActionButton("Search DB", CLR_SEARCH);
        btnSearchDb.addActionListener(e -> main.showSearch());
        actionRow.add(btnSearchDb);

        content.add(actionRow, BorderLayout.SOUTH);

        panel.add(sidebar, BorderLayout.WEST);
        panel.add(content, BorderLayout.CENTER);

        refreshStats();
    }

    private JPanel createStatCard(String title, JLabel valLabel, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        // Subtle Border instead of heavy shadow
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(229, 231, 235), 1, true),
            new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel tLabel = new JLabel(title);
        tLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        tLabel.setForeground(CLR_TEXT_LIGHT);

        valLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        valLabel.setForeground(accentColor);

        card.add(tLabel, BorderLayout.NORTH);
        card.add(valLabel, BorderLayout.CENTER);
        return card;
    }

    private JButton createActionButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(baseColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect for better UX
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(baseColor.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(baseColor); }
        });

        return btn;
    }

    private JButton createSidebarBtn(String text, boolean isActive) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(200, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(isActive ? CLR_PRIMARY : CLR_SIDEBAR);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", isActive ? Font.BOLD : Font.PLAIN, 14));
        return btn;
    }

    public void refreshStats() {
        lostStatLabel.setText(String.valueOf(ItemService.getLostCount()));
        foundStatLabel.setText(String.valueOf(ItemService.getFoundCount()));
    }

    public JPanel getPanel() { return panel; }
}