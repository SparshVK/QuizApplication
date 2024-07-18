package org.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileChooser2 {

    private JFrame frmQuizApplication;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProfileChooser2 window = new ProfileChooser2();
                    window.frmQuizApplication.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProfileChooser2() {
        DataBase.dbInit();
        initialize();
    }

    private void initialize() {
        frmQuizApplication = new JFrame();
        frmQuizApplication.setTitle("Quiz Application");
        frmQuizApplication.setBounds(100, 100, 500, 300);
        frmQuizApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmQuizApplication.getContentPane().setLayout(new BorderLayout(0, 0));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 60, 70)); // Dark Grayish Blue
        frmQuizApplication.getContentPane().add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblWelcome = new JLabel("Welcome to Quiz Application");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(lblWelcome, BorderLayout.NORTH);

        JLabel lblInstruction = new JLabel("Please Select Login Profile:");
        lblInstruction.setForeground(Color.LIGHT_GRAY);
        lblInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruction.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(lblInstruction, BorderLayout.SOUTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light Gray
        frmQuizApplication.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAdmin = new JButton("Admin");
        styleButton(btnAdmin);
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmQuizApplication.dispose();
                new AdminLogin2();
            }
        });
        buttonPanel.add(btnAdmin);

        JButton btnUser = new JButton("User");
        styleButton(btnUser);
        btnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmQuizApplication.dispose();
                new UserLogin2();
            }
        });
        buttonPanel.add(btnUser);

        // Center the frame on the screen
        frmQuizApplication.setLocationRelativeTo(null);

        // Make the frame visible
        frmQuizApplication.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 51, 102)); // Dark blue color
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2), // Light gray border
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Ensure buttons are properly displayed
        button.setOpaque(true);
        button.setContentAreaFilled(true);
    }
    
    
}
