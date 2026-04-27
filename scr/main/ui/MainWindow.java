package scr.main.ui;


import scr.main.ui.*;
import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private Dashboard dashboardPanel;

    public void createUI() {
        frame = new JFrame("Lost & Found System - Professional Edition");
        frame.setSize(800, 600); // Made slightly larger for split pane
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPage loginPage = new LoginPage(this);
        cardPanel.add(loginPage.getPanel(), "Login");

        RegisterPage registerPage = new RegisterPage(this);
        cardPanel.add(registerPage.getPanel(), "Register");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public void showDashboard() {
        if (dashboardPanel == null) {
            dashboardPanel = new Dashboard(this);
            cardPanel.add(dashboardPanel.getPanel(), "Dashboard");
        } else {
            dashboardPanel.refreshStats();
        }
        cardLayout.show(cardPanel, "Dashboard");
    }

    public void showLostForm() {
        cardPanel.add(new LostItemForm(this).getPanel(), "Lost");
        cardLayout.show(cardPanel, "Lost");
    }

    public void showFoundForm() {
        cardPanel.add(new FoundItemForm(this).getPanel(), "Found");
        cardLayout.show(cardPanel, "Found");
    }

    public void showSearch() {
        cardPanel.add(new SearchItem(this).getPanel(), "Search");
        cardLayout.show(cardPanel, "Search");
    }

    public void showLogin() {
        cardLayout.show(cardPanel, "Login");
    }

    public void showRegister() {
        cardLayout.show(cardPanel, "Register");
    }

    public JFrame getFrame() {
        return frame;
    }
}
