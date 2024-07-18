package org.project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AdminPanel2 extends JFrame {
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField answerField;
    private JTextField remIDfield;

    public AdminPanel2() {
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(20, 20));

        // Create main panel with custom background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(34, 34, 34)); // Dark grey background
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Delete Panel
        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(new Color(44, 44, 46)); // Darker grey background
        deletePanel.setLayout(new GridBagLayout());
        deletePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(deletePanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Users");
        comboBox.addItem("Question");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        deletePanel.add(comboBox, gbc);

        remIDfield = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 2.0;
        deletePanel.add(remIDfield, gbc);

        JButton btnRemove = new JButton("Remove");
        styleButton(btnRemove, new Color(229, 57, 53), Color.WHITE);
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DataBase.delete(remIDfield.getText(), (String) comboBox.getSelectedItem());
                    JOptionPane.showMessageDialog(btnRemove, "Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btnRemove, "Delete Error\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        deletePanel.add(btnRemove, gbc);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(44, 44, 46)); // Darker grey background
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblQuestion = new JLabel("Question:");
        lblQuestion.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblQuestion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea queTextArea = new JTextArea(10, 25); // Adjusted size
        queTextArea.setLineWrap(true);
        queTextArea.setWrapStyleWord(true);
        queTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(queTextArea);
        inputPanel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblOption1 = new JLabel("Option 1:");
        lblOption1.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblOption1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        option1Field = new JTextField();
        inputPanel.add(option1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblOption2 = new JLabel("Option 2:");
        lblOption2.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblOption2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        option2Field = new JTextField();
        inputPanel.add(option2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblOption3 = new JLabel("Option 3:");
        lblOption3.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblOption3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        option3Field = new JTextField();
        inputPanel.add(option3Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblOption4 = new JLabel("Option 4:");
        lblOption4.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblOption4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        option4Field = new JTextField();
        inputPanel.add(option4Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel lblAnswer = new JLabel("Answer:");
        lblAnswer.setForeground(Color.WHITE); // Set label text color to white
        inputPanel.add(lblAnswer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        answerField = new JTextField();
        inputPanel.add(answerField, gbc);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(34, 34, 34)); // Dark grey background
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        JButton btnAddQue = new JButton("Add Question");
        styleButton(btnAddQue, new Color(46, 204, 113), Color.WHITE);
        btnAddQue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] options = {option1Field.getText(), option2Field.getText(), option3Field.getText(), option4Field.getText()};
                    DataBase.addQuestion(queTextArea.getText(), options, answerField.getText());
                    JOptionPane.showMessageDialog(btnAddQue, "Question Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btnAddQue, "Can't Add Question\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        buttonsPanel.add(btnAddQue, gbc);

        gbc.gridy = 1;
        JButton btnViewAllQuestions = new JButton("View All Questions");
        styleButton(btnViewAllQuestions, new Color(52, 152, 219), Color.WHITE);
        btnViewAllQuestions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllQuestions();
            }
        });
        buttonsPanel.add(btnViewAllQuestions, gbc);

        gbc.gridy = 2;
        JButton btnExit = new JButton("Exit");
        styleButton(btnExit, new Color(149, 165, 166), Color.WHITE);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonsPanel.add(btnExit, gbc);

        gbc.gridy = 3;
        JButton btnLogout = new JButton("Logout");
        styleButton(btnLogout, new Color(241, 196, 15), Color.WHITE);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminLogin2();
            }
        });
        buttonsPanel.add(btnLogout, gbc);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(180, 40)); // Adjust size for better appearance

        // Rounded edges and gradient effect
        button.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(20), // Rounded edges with radius 20
            BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding
        ));
        
        // Add a hover effect (change color when mouse is over)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    protected void showAllQuestions() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            ArrayList<Question> questions = DataBase.getQuestionAns();
            JTextArea qTextArea = new JTextArea();
            qTextArea.setLineWrap(true);
            qTextArea.setWrapStyleWord(true);
            qTextArea.setEditable(false);
            qTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            JScrollPane scroll = new JScrollPane(qTextArea,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            frame.add(scroll);
            for (Question question : questions) {
                qTextArea.append("\nQ." + question.getQuestion() + "\n" +
                        "1." + question.getOp1() + "\n" +
                        "2." + question.getOp2() + "\n" +
                        "3." + question.getOp3() + "\n" +
                        "4." + question.getOp4() + "\n" +
                        "Ans." + question.getAns() + "\n" +
                        "---------------------------------------------"
                );
            }
            frame.setSize(500, 400);
            frame.setTitle("Question List");
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Custom class for rounded button border
    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
