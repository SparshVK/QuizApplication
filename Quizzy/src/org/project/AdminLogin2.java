package org.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin2 {

    private JFrame frame;
    private JTextField adminNameField;
    private JPasswordField passwordField;
    private final String adminName = "Admin";
    private final String password = "1234";

    public AdminLogin2() {
        initialize();
    }

    private void initialize() {
        // Create the frame
        frame = new JFrame();
        frame.setTitle("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 300); // Consistent size with ProfileChooser2
        frame.setLayout(new GridBagLayout());
        
        // Set background color
        frame.getContentPane().setBackground(new Color(34, 34, 34)); // Dark grey

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Admin label
        JLabel lblAdmin = new JLabel("Admin Name:");
        lblAdmin.setForeground(new Color(255, 255, 255)); // White text
        lblAdmin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.getContentPane().add(lblAdmin, gbc);

        // Admin name field
        adminNameField = new JTextField();
        adminNameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        adminNameField.setColumns(20);
        adminNameField.setBackground(new Color(50, 50, 50));
        adminNameField.setForeground(Color.WHITE);
        adminNameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.getContentPane().add(adminNameField, gbc);

        // Password label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(255, 255, 255)); // White text
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(lblPassword, gbc);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setColumns(20);
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.getContentPane().add(passwordField, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(buttonPanel, gbc);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Back button
        JButton btnBack = createButton("Back", new Color(229, 57, 53), Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                new UserLogin2(); // Open the UserLogin2 frame
            }
        });
        buttonPanel.add(btnBack);

        // Login button
        JButton btnLogin = createButton("Login", new Color(41, 128, 185), Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminNameField.getText().equals(adminName) && password.equals(new String(passwordField.getPassword()))) {
                    frame.dispose();
                    new AdminPanel2(); // Open AdminPanel2 frame
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnLogin);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // Set the frame to be visible
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createLineBorder(fgColor, 2));
        return button;
    }
}
