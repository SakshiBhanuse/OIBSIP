import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ATMSystemGUI extends JFrame {

    private String[] validUserIds = {"user1", "user2"};
    private String[] validPins = {"1234", "5678"};
    private int[] userBalances = {1000, 2000};

    private int currentUserIndex = -1;

    private JFrame loginFrame;
    private JFrame mainFrame;

    public ATMSystemGUI() {
        initializeLogin();
    }

    private void initializeLogin() {
        loginFrame = new JFrame("ATM Interface");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(900, 600);
        loginFrame.setLayout(new BorderLayout());


        // Set background image
    try {
        BufferedImage image = ImageIO.read(new File("a3.png"));
        loginFrame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    
        JLabel titleLabel = new JLabel("ATM Interface", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLUE);
    
        JLabel userLabel = new JLabel("User ID:");
        userLabel.setFont(new Font("Arial",Font.BOLD,20));
        userLabel.setForeground(Color.MAGENTA);
        JTextField userIdField = new JTextField(15);
        userIdField.setPreferredSize(new Dimension(150, 30));
    
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial",Font.BOLD,20));
        pinLabel.setForeground(Color.MAGENTA);
        JPasswordField pinField = new JPasswordField(15);
        pinField.setPreferredSize(new Dimension(150, 30));
    
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.darkGray);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(100, 30));
    
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String pin = new String(pinField.getPassword());
    
                boolean isAuthenticated = false;
                for (int i = 0; i < validUserIds.length; i++) {
                    if (validUserIds[i].equals(userId) && validPins[i].equals(pin)) {
                        isAuthenticated = true;
                        currentUserIndex = i;
                        break;
                    }
                }
    
                if (isAuthenticated) {
                    loginFrame.setVisible(false);
                    initializeMain();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials. Please try again.");
                }
            }
        });
    
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(titleLabel, gbc);
    
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(userLabel, gbc);
    
        gbc.gridx = 1;
        inputPanel.add(userIdField, gbc);
    
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(pinLabel, gbc);
    
        gbc.gridx = 1;
        inputPanel.add(pinField, gbc);
    
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        inputPanel.add(loginButton, gbc);
    
        loginFrame.add(inputPanel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }

    private void initializeMain() {
        mainFrame = new JFrame("ATM Main Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 600);
        mainFrame.setLayout(new GridLayout(7, 1));

        // Set background image
        try {
            BufferedImage image = ImageIO.read(new File("a1.png"));
            mainFrame.setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel titleLabel1 = new JLabel("ATM Main Menu");
        titleLabel1.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel1.setForeground(Color.BLUE);

        JButton transactionsButton = new JButton("Transactions History");
        transactionsButton.setPreferredSize(new Dimension(200, 50));
        transactionsButton.setBackground(Color.BLACK);
        transactionsButton.setForeground(Color.RED);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setPreferredSize(new Dimension(200, 50));
        withdrawButton.setBackground(Color.BLACK);
        withdrawButton.setForeground(Color.RED);

        JButton depositButton = new JButton("Deposit");
        depositButton.setPreferredSize(new Dimension(200, 50));
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.RED);

        JButton transferButton = new JButton("Transfer");
        transferButton.setPreferredSize(new Dimension(200, 50));
        transferButton.setBackground(Color.BLACK);
        transferButton.setForeground(Color.RED);

        JButton balanceButton = new JButton("Available Balance");
        balanceButton.setPreferredSize(new Dimension(200, 50));
        balanceButton.setBackground(Color.BLACK);
        balanceButton.setForeground(Color.RED);

        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.RED);

        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTransactionHistory();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransfer();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAvailbleBalance();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Thank you for using the ATM System!!");
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(titleLabel1);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(quitButton);

        mainFrame.add(buttonPanel);
        mainFrame.setVisible(true);
    }

    private void displayTransactionHistory() {
        JFrame historyFrame = new JFrame("Transactions History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setSize(400, 300);
        historyFrame.setLayout(new BorderLayout());

        JTextArea historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);

        // Mock implementation: Displaying a simple transaction history
        historyTextArea.append("Transaction History for " + validUserIds[currentUserIndex] + ":\n");

        // Add sample transactions (you would typically load from a data source)
        historyTextArea.append("Date: 2024-07-10, Type: Deposit, Amount: $500\n");
        historyTextArea.append("Date: 2024-07-09, Type: Withdrawal, Amount: $200\n");

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        historyFrame.add(scrollPane, BorderLayout.CENTER);

        historyFrame.setVisible(true);
    }

    private void performWithdraw() {
        JFrame withdrawFrame = new JFrame("Withdraw");
        withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        withdrawFrame.setSize(300, 200);
        withdrawFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JLabel amountLabel = new JLabel("Enter amount to withdraw:");
        JTextField amountField = new JTextField(10);
        amountField.setPreferredSize(new Dimension(150, 30));
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setPreferredSize(new Dimension(100, 30));

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(withdrawFrame, "Invalid amount.");
                    } else if (amount > userBalances[currentUserIndex]) {
                        JOptionPane.showMessageDialog(withdrawFrame, "Insufficient balance.");
                    } else {
                        userBalances[currentUserIndex] -= amount;
                        JOptionPane.showMessageDialog(withdrawFrame, "Withdrawal successful.");
                        mainFrame.setVisible(true); // Show main frame after transaction
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(withdrawFrame, "Invalid amount format.");
                }
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);

        withdrawFrame.add(panel, BorderLayout.CENTER);
        withdrawFrame.setVisible(true);
    }

    private void performDeposit() {
        JFrame depositFrame = new JFrame("Deposit");
        depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositFrame.setSize(300, 200);
        depositFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JLabel amountLabel = new JLabel("Enter amount to deposit:");
        JTextField amountField = new JTextField(10);
        amountField.setPreferredSize(new Dimension(150, 30));
        JButton depositButton = new JButton("Deposit");
        depositButton.setPreferredSize(new Dimension(100, 30));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(depositFrame, "Invalid amount.");
                    } else {
                        userBalances[currentUserIndex] += amount;
                        JOptionPane.showMessageDialog(depositFrame, "Deposit successful.");
                        mainFrame.setVisible(true); // Show main frame after transaction
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(depositFrame, "Invalid amount format.");
                }
            }
        });

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);

        depositFrame.add(panel, BorderLayout.CENTER);
        depositFrame.setVisible(true);
    }

    private void performTransfer() {
        JFrame transferFrame = new JFrame("Transfer");
        transferFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transferFrame.setSize(350, 250);
        transferFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setOpaque(false);

        JLabel recipientLabel = new JLabel("Enter recipient's User ID:");
        JTextField recipientField = new JTextField();
        recipientField.setPreferredSize(new Dimension(150, 30));

        JLabel amountLabel = new JLabel("Enter amount to transfer:");
        JTextField amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(150, 30));

      JButton transferButton = new JButton("Transfer");
        transferButton.setPreferredSize(new Dimension(100, 30));

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String recipientId = recipientField.getText();
                    int amount = Integer.parseInt(amountField.getText());

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(transferFrame, "Invalid amount.");
                    } else if (amount > userBalances[currentUserIndex]) {
                        JOptionPane.showMessageDialog(transferFrame, "Insufficient balance.");
                    } else {
                        boolean recipientFound = false;
                        for (int i = 0; i < validUserIds.length; i++) {
                            if (validUserIds[i].equals(recipientId)) {
                                userBalances[currentUserIndex] -= amount;
                                userBalances[i] += amount;
                                recipientFound = true;
                                JOptionPane.showMessageDialog(transferFrame, "Transfer successful.");
                                mainFrame.setVisible(true); // Show main frame after transaction
                                break;
                            }
                        }
                        if (!recipientFound) {
                            JOptionPane.showMessageDialog(transferFrame, "Recipient not found.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(transferFrame, "Invalid amount format.");
                }
            }
        });

        panel.add(recipientLabel);
        panel.add(recipientField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(transferButton);

        transferFrame.add(panel, BorderLayout.CENTER);
        transferFrame.setVisible(true);
    }

    private void displayAvailbleBalance() {
        JFrame balanceFrame = new JFrame("Availble Balance");
        balanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        balanceFrame.setSize(400, 300);
        balanceFrame.setLayout(new BorderLayout());

        JLabel balanceLabel = new JLabel("Available Balance: $" + userBalances[currentUserIndex]);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(balanceLabel);
        balanceFrame.add(scrollPane, BorderLayout.CENTER);

        balanceFrame.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMSystemGUI();
            }
        });
    }
}