package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import scr.main.servies.UserService;

public class LoginPage {
    private JPanel panel;
    private MainWindow main;

    public LoginPage(MainWindow main) {
        this.main = main;
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("Secure Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));

        JTextField userText = new JTextField();
        userText.setBorder(BorderFactory.createTitledBorder("Username"));
        userText.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField passText = new JPasswordField();
        passText.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginBtn = new JButton("Enter System");
        loginBtn.setBackground(new Color(52, 152, 219));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);

        JButton regBtn = new JButton("Create New Account");
        regBtn.setBackground(new Color(46, 204, 113));
        regBtn.setForeground(Color.WHITE);
        regBtn.setOpaque(true);
        regBtn.setBorderPainted(false);

        formPanel.add(title);
        formPanel.add(userText);
        formPanel.add(passText);
        formPanel.add(loginBtn);
        formPanel.add(regBtn);

        JPanel wrapper = new JPanel();
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createLineBorder(new Color(236, 240, 241), 2, true));
        wrapper.add(formPanel);

        panel.add(wrapper);

        regBtn.addActionListener(e -> main.showRegister());

        loginBtn.addActionListener(e -> {
            String u = userText.getText();
            String p = new String(passText.getPassword());
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(main.getFrame(), "Enter credentials");
                return;
            }
            if (UserService.login(u, p)) {
                main.showDashboard();
            } else {
                JOptionPane.showMessageDialog(main.getFrame(), "Invalid Login");
            }
        });
    }

    public JPanel getPanel() { return panel; }
}