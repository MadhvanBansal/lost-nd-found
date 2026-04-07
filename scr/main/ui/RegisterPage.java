package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import scr.main.servies.UserService;

public class RegisterPage {
    private JPanel panel;
    private MainWindow main;

    public RegisterPage(MainWindow main) {
        this.main = main;
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("Join the System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(44, 62, 80));

        JTextField userText = new JTextField();
        userText.setBorder(BorderFactory.createTitledBorder("Choose a Username"));
        userText.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField passText = new JPasswordField();
        passText.setBorder(BorderFactory.createTitledBorder("Choose a Password"));

        JButton regBtn = new JButton("Register Account");
        regBtn.setBackground(new Color(155, 89, 182));
        regBtn.setForeground(Color.WHITE);
        regBtn.setOpaque(true);
        regBtn.setBorderPainted(false);

        JButton backBtn = new JButton("Back to Login");
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setOpaque(true);
        backBtn.setBorderPainted(false);

        formPanel.add(title);
        formPanel.add(userText);
        formPanel.add(passText);
        formPanel.add(regBtn);
        formPanel.add(backBtn);

        JPanel wrapper = new JPanel();
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createLineBorder(new Color(236, 240, 241), 2, true));
        wrapper.add(formPanel);

        panel.add(wrapper);

        backBtn.addActionListener(e -> main.showLogin());

        regBtn.addActionListener(e -> {
            String u = userText.getText();
            String p = new String(passText.getPassword());
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(main.getFrame(), "Enter all fields correctly");
                return;
            }
            if (UserService.register(u, p)) {
                JOptionPane.showMessageDialog(main.getFrame(), "Registration Successful! You may now login.");
                main.showLogin();
            } else {
                JOptionPane.showMessageDialog(main.getFrame(), "Username strongly exists already. Try another.");
            }
        });
    }

    public JPanel getPanel() { return panel; }
}
