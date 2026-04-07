package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class FoundItemForm {
    private JPanel panel;
    private MainWindow main;

    public FoundItemForm(MainWindow main) {
        this.main = main;
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Log a Found Item", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(46, 204, 113)); 
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 1, 10, 15));
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(10, 100, 20, 100));

        JTextField name = new JTextField();
        name.setBorder(BorderFactory.createTitledBorder("Item Name"));
        JTextField desc = new JTextField();
        desc.setBorder(BorderFactory.createTitledBorder("Description"));
        JTextField loc = new JTextField();
        loc.setBorder(BorderFactory.createTitledBorder("Discovery Location"));

        // Image Attachment Feature
        JPanel uploadPanel = new JPanel(new BorderLayout(10, 10));
        uploadPanel.setOpaque(false);
        uploadPanel.setBorder(BorderFactory.createTitledBorder("Attach Visual Evidence"));
        
        JButton uploadBtn = createBtn("Browse System...", new Color(155, 89, 182));
        JLabel imgLabel = new JLabel("No Image", JLabel.CENTER);
        imgLabel.setPreferredSize(new Dimension(80, 80));
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        uploadPanel.add(uploadBtn, BorderLayout.WEST);
        uploadPanel.add(imgLabel, BorderLayout.CENTER);

        form.add(name);
        form.add(desc);
        form.add(loc);
        form.add(uploadPanel);
        panel.add(form, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setOpaque(false);

        JButton submit = createBtn("Submit Item", new Color(52, 152, 219));
        JButton cancel = createBtn("Cancel", Color.GRAY);

        btnPanel.add(cancel);
        btnPanel.add(submit);
        panel.add(btnPanel, BorderLayout.SOUTH);

        final String[] selectedPath = {""};

        uploadBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(main.getFrame());
            if (res == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                selectedPath[0] = f.getAbsolutePath();
                ImageIcon icon = new ImageIcon(selectedPath[0]);
                Image im = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(im));
                imgLabel.setText("");
            }
        });

        cancel.addActionListener(e -> main.showDashboard());
        submit.addActionListener(e -> {
            if (name.getText().trim().isEmpty() || loc.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(main.getFrame(), "Item name and location are required.");
                return;
            }
            scr.main.model.Item item = new scr.main.model.Item(name.getText().trim(), desc.getText().trim(), loc.getText().trim(), "Found", selectedPath[0]);
            scr.main.servies.ItemService.addItem(item);
            JOptionPane.showMessageDialog(main.getFrame(), "Successfully added to the Found database.");
            
            name.setText(""); desc.setText(""); loc.setText("");
            selectedPath[0] = ""; imgLabel.setIcon(null); imgLabel.setText("No Image");
            main.showDashboard();
        });
    }

    private JButton createBtn(String txt, Color bg) {
        JButton b = new JButton(txt);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(140, 40));
        return b;
    }

    public JPanel getPanel() { return panel; }
}