package org.project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Register2 {
    private JFrame frame;
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Register2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 400); // Original size
        frame.getContentPane().setLayout(new BorderLayout(20, 20));

        // Gradient Background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 255, 255), 0, getHeight(), new Color(240, 240, 240));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout(20, 20));
        frame.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 15, 15));
        formPanel.setBackground(new Color(255, 255, 255)); // White background
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        JLabel lblId = createLabel("User ID:");
        formPanel.add(lblId);

        userIdField = createTextField();
        formPanel.add(userIdField);

        JLabel lblName = createLabel("Name:");
        formPanel.add(lblName);

        nameField = createTextField();
        formPanel.add(nameField);

        JLabel lblEmail = createLabel("Email:");
        formPanel.add(lblEmail);

        emailField = createTextField();
        formPanel.add(emailField);

        JLabel lblPassword = createLabel("Password:");
        formPanel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setBorder(createRoundedBorder(new Color(200, 200, 200), 10));
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnBack = createButton("Back", new Color(192, 57, 43), Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserLogin2 userLogin = new UserLogin2();
            }
        });
        buttonPanel.add(btnBack);

        JButton btnRegister = createButton("Register", new Color(41, 128, 185), Color.WHITE);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase.addUser(Integer.valueOf(userIdField.getText()), nameField.getText(), emailField.getText(), new String(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(btnRegister, "User Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btnRegister, "Can't Add User\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        buttonPanel.add(btnRegister);

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 18));
        label.setForeground(new Color(41, 128, 185));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textField.setBorder(createRoundedBorder(new Color(200, 200, 200), 10));
        return textField;
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createEmptyBorder()); // No border
        button.setBorder(BorderFactory.createCompoundBorder(
            button.getBorder(),
            BorderFactory.createLineBorder(bgColor.darker(), 2)
        )); // Add a darker border
        return button;
    }

    private Border createRoundedBorder(Color color, int radius) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1),
            BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        );
    }
}
