package scr.main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import scr.main.model.Item;
import scr.main.servies.ItemService;

public class SearchItem {
    private JPanel panel;
    private MainWindow main;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<Item> currentList;

    public SearchItem(MainWindow main) {
        this.main = main;
        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        top.setOpaque(false);

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton btn = new JButton("Search Catalog");
        btn.setBackground(new Color(142, 68, 173));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true); btn.setBorderPainted(false);

        top.add(new JLabel("Keyword:"));
        top.add(searchField);
        top.add(btn);
        
        panel.add(top, BorderLayout.NORTH);

        String[] cols = {"Type", "Date Logged", "Name"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(236, 240, 241));
        
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setBorder(BorderFactory.createLineBorder(new Color(236, 240, 241), 2));
        
        // Detailed Side Panel
        JPanel detailPanel = new JPanel(new BorderLayout(10, 10));
        detailPanel.setBackground(Color.WHITE);
        detailPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel imgView = new JLabel("Click on a row to view details", JLabel.CENTER);
        imgView.setPreferredSize(new Dimension(150, 150));
        imgView.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JTextArea descView = new JTextArea("Details will appear here...");
        descView.setLineWrap(true);
        descView.setWrapStyleWord(true);
        descView.setEditable(false);
        descView.setFont(new Font("Arial", Font.PLAIN, 14));
        
        detailPanel.add(imgView, BorderLayout.NORTH);
        detailPanel.add(new JScrollPane(descView), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollTable, detailPanel);
        splitPane.setDividerLocation(380); 
        panel.add(splitPane, BorderLayout.CENTER);

        JButton back = new JButton("Back to Dashboard");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setOpaque(true); back.setBorderPainted(false);
        
        JPanel bot = new JPanel(); bot.setOpaque(false);
        bot.add(back);
        panel.add(bot, BorderLayout.SOUTH);

        // Interaction logic mapping row click to image renderer
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int index = table.getSelectedRow();
                Item selected = currentList.get(index);
                
                String fullText = "Name: " + selected.getName() + 
                                  "\nLocation: " + selected.getLocation() +
                                  "\nDescription: " + selected.getDescription() +
                                  "\nType: " + selected.getType();
                descView.setText(fullText);

                if (selected.getImagePath() != null && !selected.getImagePath().isEmpty()) {
                    ImageIcon ic = new ImageIcon(selected.getImagePath());
                    Image im = ic.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    imgView.setIcon(new ImageIcon(im));
                    imgView.setText("");
                } else {
                    imgView.setIcon(null);
                    imgView.setText("No Photo Available");
                }
            }
        });

        back.addActionListener(e -> main.showDashboard());
        btn.addActionListener(e -> {
            currentList = ItemService.searchItem(searchField.getText().trim());
            tableModel.setRowCount(0); // clear
            for (Item i : currentList) {
                tableModel.addRow(new Object[]{i.getType(), i.getDateAdded().toString(), i.getName()});
            }
            imgView.setIcon(null);
            imgView.setText("Select an item");
            descView.setText("Details will appear here...");
        });

        btn.doClick(); // Initial Load
    }

    public JPanel getPanel() { return panel; }
}