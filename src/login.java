import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JPanel {

    JTextField usernameField;
    JPasswordField passwordField;
    homepageui home;

    public login(homepageui home) {
        this.home = home;
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 28));
        lblLogin.setBounds(220, 40, 200, 40);
        add(lblLogin);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUsername.setBounds(100, 120, 100, 25);
        add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(200, 120, 200, 25);
        add(usernameField);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPassword.setBounds(100, 170, 100, 25);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 170, 200, 25);
        add(passwordField);

        JCheckBox rememberBox = new JCheckBox("Remember me");
        rememberBox.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberBox.setBackground(Color.WHITE);
        rememberBox.setBounds(200, 210, 150, 25);
        add(rememberBox);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(144, 238, 144));
        btnLogin.setBounds(200, 260, 120, 35);
        btnLogin.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                home.simulateLogin("login");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnLogin);

        JLabel signupLink = new JLabel("Don't have an account? Sign up");
        signupLink.setFont(new Font("Arial", Font.PLAIN, 14));
        signupLink.setForeground(Color.BLUE);
        signupLink.setBounds(180, 310, 250, 25);
        signupLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                home.showSignupPanel();
            }
        });
        add(signupLink);
    }
}
