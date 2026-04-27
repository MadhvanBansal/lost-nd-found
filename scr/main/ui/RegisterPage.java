package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import scr.main.servies.UserService;

public class RegisterPage {
    private JPanel panel;
    private MainWindow main;

    // --- REFINED COLORS ---
    private final Color CLR_BG      = new Color(243, 244, 246);
    private final Color CLR_CARD    = Color.WHITE;
    private final Color CLR_PRIMARY = new Color(155, 89, 182); // Amethyst Purple
    private final Color CLR_TEXT    = new Color(31, 41, 55);
    private final Color CLR_SUBTEXT = new Color(107, 114, 128);

    public RegisterPage(MainWindow main) {
        this.main = main;
        
        // Use GridBagLayout to perfectly center the registration card
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(CLR_BG);

        // --- THE REGISTRATION CARD ---
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CLR_CARD);
        card.setPreferredSize(new Dimension(450, 550)); // Made it significantly bigger
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(229, 231, 235), 1, true),
            new EmptyBorder(40, 50, 40, 50)
        ));

        // 1. Header Section
        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(CLR_TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitle = new JLabel("Join the Lost & Found community");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitle.setForeground(CLR_SUBTEXT);
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Input Fields
        JTextField userText = createStyledTextField("Choose a Username");
        JPasswordField passText = createStyledPasswordField("Choose a Password");
        JPasswordField confirmPassText = createStyledPasswordField("Confirm Password");

        // 3. Buttons
        JButton regBtn = createStyledButton("Register Account", CLR_PRIMARY);
        JButton backBtn = new JButton("Already have an account? Login");
        backBtn.setForeground(CLR_PRIMARY);
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Assemble Card
        card.add(title);
        card.add(Box.createVerticalStrut(5));
        card.add(subTitle);
        card.add(Box.createVerticalStrut(40));
        card.add(userText);
        card.add(Box.createVerticalStrut(20));
        card.add(passText);
        card.add(Box.createVerticalStrut(20));
        card.add(confirmPassText);
        card.add(Box.createVerticalStrut(30));
        card.add(regBtn);
        card.add(Box.createVerticalStrut(15));
        card.add(backBtn);

        panel.add(card);

        // Logic
        backBtn.addActionListener(e -> main.showLogin());

        regBtn.addActionListener(e -> {
            String u = userText.getText();
            String p = new String(passText.getPassword());
            String cp = new String(confirmPassText.getPassword());

            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(main.getFrame(), "All fields are required.");
                return;
            }
            if (!p.equals(cp)) {
                JOptionPane.showMessageDialog(main.getFrame(), "Passwords do not match!");
                return;
            }
            
            if (UserService.register(u, p)) {
                JOptionPane.showMessageDialog(main.getFrame(), "Account Created! You can now login.");
                main.showLogin();
            } else {
                JOptionPane.showMessageDialog(main.getFrame(), "Username already exists.");
            }
        });
    }

    private JTextField createStyledTextField(String title) {
        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        tf.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)), title));
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        return tf;
    }

    private JPasswordField createStyledPasswordField(String title) {
        JPasswordField pf = new JPasswordField();
        pf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        pf.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)), title));
        return pf;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(bg.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(bg); }
        });
        return btn;
    }

    public JPanel getPanel() { return panel; }
}
