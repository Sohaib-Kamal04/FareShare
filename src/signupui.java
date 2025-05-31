import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signupui extends JPanel {

    JTextField nameField;
    private JTextField emailField;
    JPasswordField passwordField;
    JButton signupButton;
    JLabel errorLabel;
    private homepageui home;

    public signupui(homepageui home) {
        this.home = home;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Create an Account");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(160, 60, 300, 40);
        add(title);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setBounds(100, 140, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210, 140, 200, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emailLabel.setBounds(100, 190, 100, 25);
        add(emailLabel);

        setEmailField(new JTextField());
        getEmailField().setBounds(210, 190, 200, 25);
        add(getEmailField());

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(100, 240, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 240, 200, 25);
        add(passwordField);

        // Error Label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(210, 275, 300, 20);
        add(errorLabel);
        errorLabel.setVisible(false);

        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.setBackground(new Color(144, 238, 144));
        signupButton.setBounds(210, 299, 150, 35);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateSignup(nameField.getText(), getEmailField().getText(), new String(passwordField.getPassword()));
            }
        });
        add(signupButton);

        JLabel loginLabel = new JLabel("Already have an account? Login");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(210, 350, 250, 20);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home.showLoginPanel();
            }
        });
        add(loginLabel);
    }

    public void simulateSignup(String name, String email, String password) {
        // Basic Validation
        if (name.isEmpty()) {
            showError("Name cannot be empty.");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showError("Invalid email address.");
            return;
        }

        if (password.length() < 6) {
            showError("Password must be at least 6 characters.");
            return;
        }

        // Simulate successful signup
        errorLabel.setVisible(false);
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");

        // Notify homepage
        home.simulateLogin("signup");
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }
}
