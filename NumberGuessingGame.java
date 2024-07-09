import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int numberToGuess;
    private int numberOfTries = 0;
    private int maxTries = 10;
    private int score = 0;

    private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton;
    private JButton newGameButton;
    private JButton giveUpButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set background image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\HP\\Desktop\\B.Tech\\Internship\\Oasis Infobyte\\Projects\\Task 3\\NumberGussingGame\\NoImg.png");
        setContentPane(new JLabel(backgroundImage));
        setLayout(new GridBagLayout());

        // Generate a random number
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;

        guessField = new JTextField(15); // Medium size text field
        guessField.setFont(new Font("Arial", Font.PLAIN, 30));

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setSize(30,40);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 30));

        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 30));

        giveUpButton = new JButton("Give Up");
        giveUpButton.setFont(new Font("Arial", Font.BOLD, 30));

        // Add action listeners
        guessButton.addActionListener(new GuessButtonListener());
        newGameButton.addActionListener(new NewGameButtonListener());
        giveUpButton.addActionListener(new GiveUpButtonListener());

        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(messageLabel, gbc);

        gbc.gridy = 2;
        add(guessField, gbc);

        gbc.gridy = 3;
        add(guessButton, gbc);

        gbc.gridy = 4;
        add(giveUpButton, gbc);

        gbc.gridy = 5;
        add(newGameButton, gbc);

        newGameButton.setEnabled(false);  // Disable the new game button initially
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                numberOfTries++;

                if (guess < 1 || guess > 100) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Invalid input! Enter a number between 1 and 100.");
                } else if (guess < numberToGuess) {
                    messageLabel.setForeground(Color.ORANGE);
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > numberToGuess) {
                    messageLabel.setForeground(Color.ORANGE);
                    messageLabel.setText("Too high! Try again.");
                } else {
                    score += maxTries - numberOfTries + 1;  // Calculate score
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Correct! You guessed the number in " + numberOfTries + " tries. Score: " + score);
                    guessButton.setEnabled(false);  // Disable the guess button
                    newGameButton.setEnabled(true);  // Enable the new game button
                    giveUpButton.setEnabled(false);  // Disable the give up button
                }

                if (numberOfTries >= maxTries && guess != numberToGuess) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Out of attempts! The number was " + numberToGuess + ". Score: " + score);
                    guessButton.setEnabled(false);  // Disable the guess button
                    newGameButton.setEnabled(true);  // Enable the new game button
                    giveUpButton.setEnabled(false);  // Disable the give up button
                }
            } catch (NumberFormatException ex) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid input! Enter a number between 1 and 100.");
            }
        }
    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            numberToGuess = rand.nextInt(100) + 1;
            numberOfTries = 0;
            guessField.setText("");
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setText("Guess a number between 1 and 100:");
            guessButton.setEnabled(true);  // Enable the guess button
            newGameButton.setEnabled(false);  // Disable the new game button
            giveUpButton.setEnabled(true);  // Enable the give up button
        }
    }

    private class GiveUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("You gave up! The number was " + numberToGuess + ". Score: " + score);
            guessButton.setEnabled(false);  // Disable the guess button
            newGameButton.setEnabled(true);  // Enable the new game button
            giveUpButton.setEnabled(false);  // Disable the give up button
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}