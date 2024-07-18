package org.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class Quiz2 extends JFrame {
    ArrayList<Question> questions = null;
    private int count = 0;
    private int score = 0;

    public Quiz2() {
        setTitle("Welcome to Quiz");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Custom background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("D:\\Quizzy\\neon-sign-online-quiz-with-brick-wall-background-free-vector.jpg");
        backgroundPanel.setLayout(new BorderLayout(20, 20));
        setContentPane(backgroundPanel);
        
        // Timer Panel
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false);
        JLabel timerLabel = new JLabel(String.format("%02d:%02d", 600 / 60, 600 % 60));
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerLabel.setForeground(new Color(192, 57, 43)); // Red color for contrast
        timerPanel.add(timerLabel, BorderLayout.EAST);
        add(timerPanel, BorderLayout.NORTH);

        startTimer(600, timerLabel);

        // Main Panel to hold Question and Options
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setOpaque(false);
        add(mainPanel, BorderLayout.CENTER);

        // Question Panel
        JPanel quePanel = new JPanel();
        quePanel.setLayout(new BoxLayout(quePanel, BoxLayout.Y_AXIS));
        quePanel.setOpaque(false);
        quePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        mainPanel.add(quePanel, BorderLayout.NORTH);

        JTextArea queTextArea = new JTextArea();
        queTextArea.setFont(new Font("SansSerif", Font.BOLD, 22));
        queTextArea.setLineWrap(true);
        queTextArea.setWrapStyleWord(true);
        queTextArea.setEditable(false);
        queTextArea.setOpaque(false);
        queTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        queTextArea.setForeground(Color.WHITE); // Set question text color to white
        quePanel.add(queTextArea);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JRadioButton rdbtnOp1 = createRadioButton("Option 1");
        JRadioButton rdbtnOp2 = createRadioButton("Option 2");
        JRadioButton rdbtnOp3 = createRadioButton("Option 3");
        JRadioButton rdbtnOp4 = createRadioButton("Option 4");

        optionsPanel.add(rdbtnOp1);
        optionsPanel.add(rdbtnOp2);
        optionsPanel.add(rdbtnOp3);
        optionsPanel.add(rdbtnOp4);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnOp1);
        bg.add(rdbtnOp2);
        bg.add(rdbtnOp3);
        bg.add(rdbtnOp4);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(buttonsPanel, BorderLayout.SOUTH);

        JButton btnNext = new JButton("Next");
        btnNext.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnNext.setBackground(new Color(41, 128, 185));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusPainted(false);
        btnNext.setPreferredSize(new Dimension(100, 40));
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bg.getSelection() == null) {
                    JOptionPane.showMessageDialog(quePanel, "Please select an Answer");
                } else {
                    checkAnswer(count, bg);
                    count++;
                    if (questions.size() > count) {
                        queTextArea.setText(questions.get(count).getQuestion());
                        rdbtnOp1.setText(questions.get(count).getOp1());
                        rdbtnOp2.setText(questions.get(count).getOp2());
                        rdbtnOp3.setText(questions.get(count).getOp3());
                        rdbtnOp4.setText(questions.get(count).getOp4());
                        bg.clearSelection();
                    } else {
                        displayScore();
                    }
                }
            }
        });
        buttonsPanel.add(btnNext);

        try {
            questions = DataBase.getQuestionAns();
            queTextArea.setText(questions.get(count).getQuestion());
            rdbtnOp1.setText(questions.get(count).getOp1());
            rdbtnOp2.setText(questions.get(count).getOp2());
            rdbtnOp3.setText(questions.get(count).getOp3());
            rdbtnOp4.setText(questions.get(count).getOp4());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        radioButton.setOpaque(false);
        radioButton.setFocusPainted(false);
        radioButton.setForeground(Color.WHITE); // Set options text color to white
        return radioButton;
    }

    private void checkAnswer(int count, ButtonGroup bg) {
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected() && button.getText().equals(questions.get(count).getAns())) {
                score++;
            }
        }
    }

    private void displayScore() {
        dispose();
        JOptionPane.showMessageDialog(this, "Thanks for playing the Quiz\nYour Score was: " + score,
                "Happy Playing Quiz Mates...", JOptionPane.PLAIN_MESSAGE);
    }

    private void startTimer(int timeInSecs, JLabel timerLabel) {
        Timer timer = new Timer(1000, new ActionListener() {
            int timeLeft = timeInSecs;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText(String.format("%02d:%02d", timeLeft / 60, timeLeft % 60));
                } else {
                    ((Timer) e.getSource()).stop();
                    displayScore();
                }
            }
        });

        timer.start();
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(fileName).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // public static void main(String[] args) {
    //     new Quiz2();
    // }
}
