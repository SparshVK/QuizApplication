package org.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserLogin2 {

    private JFrame frame;
    private JTextField idField;
    private JPasswordField passwordField;

    public UserLogin2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 300); // Updated size to match ProfileChooser2
        frame.setTitle("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(246, 245, 244)); // Set background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Label
        JLabel lblId = createLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.getContentPane().add(lblId, gbc);

        // ID Field
        idField = createTextField();
        idField.setColumns(10);
        gbc.gridx = 1;
        frame.getContentPane().add(idField, gbc);

        // Password Label
        JLabel lblPassword = createLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.getContentPane().add(lblPassword, gbc);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(75, 25));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(149, 165, 166)));
        gbc.gridx = 1;
        frame.getContentPane().add(passwordField, gbc);

        // Login Button
        JButton btnLogin = createButton("Login", new Color(41, 128, 185), Color.WHITE);
        btnLogin.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (DataBase.validatePassword(idField.getText(), new String(passwordField.getPassword()))) {
                        frame.dispose();
                        Quiz2 quiz = new Quiz2();
                    } else {
                        JOptionPane.showMessageDialog(btnLogin, "ID or Password does not match", "Invalid ID/Password", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Make button span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(btnLogin, gbc);

        // Register Button
        JButton btnRegister = createButton("Register", new Color(39, 174, 96), Color.WHITE);
        btnRegister.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Register2 register = new Register2();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.getContentPane().add(btnRegister, gbc);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(new Color(149, 165, 166)));
        return textField;
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        return button;
    }
}
