import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class homepageui extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public JButton bookRideBtn;
    public JButton shareRideBtn;
    public JButton loginBtn;
    public JButton signUpBtn;
    public JButton signOutBtn;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                homepageui frame = new homepageui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public homepageui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 588, 737);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        initializeHomePage();
        
        // Initially hide these buttons
        bookRideBtn.setVisible(false);
        shareRideBtn.setVisible(false);
        signOutBtn.setVisible(false);
    }

    public void simulateLogin(String type) {
    	showHomePage();
        bookRideBtn.setVisible(true);
        shareRideBtn.setVisible(true);
        signOutBtn.setVisible(true);
        loginBtn.setVisible(false);
        signUpBtn.setVisible(false);
        
        if (type.equals("login")) {
            System.out.println("User logged in successfully.");
        } else {
            System.out.println("User signed up successfully.");
        }
        
        // Refresh the UI
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void initializeHomePage() {
        contentPane.setLayout(null);
        
        JLabel headingLabel = new JLabel("WELCOME TO FARESHARE");
        headingLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        headingLabel.setBounds(86, 76, 540, 66);
        contentPane.add(headingLabel);

        JLabel taglineLabel = new JLabel("Your Trusted Carpool Solution");
        taglineLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        taglineLabel.setBounds(153, 138, 283, 14);
        contentPane.add(taglineLabel);

        bookRideBtn = new JButton("Book a Ride");
        bookRideBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        bookRideBtn.setBackground(new Color(255, 255, 221));
        bookRideBtn.setBounds(186, 208, 178, 42);
        bookRideBtn.addActionListener(e -> {
            ride shareFrame = new ride();
            shareFrame.setVisible(true);
        });
        contentPane.add(bookRideBtn);

        shareRideBtn = new JButton("Share Ride");
        shareRideBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        shareRideBtn.setBackground(new Color(255, 255, 213));
        shareRideBtn.setBounds(186, 287, 178, 42);
        shareRideBtn.addActionListener(e -> {
            ridesharing shareFrame = new ridesharing();
            shareFrame.setVisible(true);
        });
        contentPane.add(shareRideBtn);

        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        loginBtn.setBackground(new Color(255, 255, 221));
        loginBtn.setBounds(186, 373, 178, 42);
        loginBtn.addActionListener(e -> showLoginPanel());
        contentPane.add(loginBtn);

        signUpBtn = new JButton("Sign Up");
        signUpBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        signUpBtn.setBackground(new Color(255, 255, 221));
        signUpBtn.setBounds(186, 462, 178, 42);
        signUpBtn.addActionListener(e -> showSignupPanel());
        contentPane.add(signUpBtn);

        signOutBtn = new JButton("Sign Out");
        signOutBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        signOutBtn.setBackground(new Color(255, 255, 221));
        signOutBtn.setBounds(420, 20, 130, 30);
        signOutBtn.addActionListener(e -> {
            loginBtn.setVisible(true);
            signUpBtn.setVisible(true);
            bookRideBtn.setVisible(false);
            shareRideBtn.setVisible(false);
            signOutBtn.setVisible(false);
        });
        contentPane.add(signOutBtn);

        JLabel bgImage = new JLabel("");
        bgImage.setIcon(new ImageIcon("C:\\Users\\cskam\\Downloads\\Java workspace\\FareShare\\d5165e0a4f3b9244eca175b8dc64a5fa.jpg"));
        bgImage.setBounds(0, -195, 1280, 940);
        contentPane.add(bgImage);
        contentPane.setComponentZOrder(bgImage, contentPane.getComponentCount() - 1);
    }

    public void showSignupPanel() {
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();

        signupui signupPanel = new signupui(this);
        signupPanel.setBounds(0, 0, getWidth(), getHeight());
        contentPane.add(signupPanel);
        contentPane.repaint();
    }

    public void showLoginPanel() {
        contentPane.removeAll();  
        contentPane.setLayout(new BorderLayout()); 
        login loginPanel = new login(this);  
        contentPane.add(loginPanel, BorderLayout.CENTER);  
        contentPane.revalidate();  
        contentPane.repaint(); 
    }
    public void showHomePage() {
        contentPane.removeAll();
        initializeHomePage();
        contentPane.revalidate();
        contentPane.repaint();
    }

    public void reloadHomePage() {
        dispose();
        homepageui newHome = new homepageui();
        newHome.setVisible(true);
    }

    // Getters for test access
    public JButton getBookRideBtn() {
        return bookRideBtn;
    }

    public JButton getShareRideBtn() {
        return shareRideBtn;
    }

    public JButton getSignOutBtn() {
        return signOutBtn;
    }
}