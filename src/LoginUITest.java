import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginUITest {

    private homepageui homePage;
    private login loginPanel;

    @BeforeEach
    public void setUp() {
        homePage = new homepageui();
        loginPanel = new login(homePage);
    }

    // 1. Components exist
    @Test
    public void testFieldsExist() {
        assertNotNull(loginPanel.usernameField);
        assertNotNull(loginPanel.passwordField);
    }

    // 2. Successful login with correct credentials
    @Test
    public void testCorrectLogin() {
        loginPanel.usernameField.setText("admin");
        loginPanel.passwordField.setText("123");

        clickLoginButton();

        assertTrue(homePage.bookRideBtn.isVisible());
        assertTrue(homePage.shareRideBtn.isVisible());
        assertTrue(homePage.signOutBtn.isVisible());
    }

    // 3. Failed login with incorrect credentials
    @Test
    public void testIncorrectLogin() {
        loginPanel.usernameField.setText("wrong");
        loginPanel.passwordField.setText("wrong");

        clickLoginButton();

        assertTrue(homePage.loginBtn.isVisible());
        assertTrue(homePage.signUpBtn.isVisible());
        assertFalse(homePage.bookRideBtn.isVisible());
    }



    // 4. Login button is clickable
    @Test
    public void testLoginButtonClickable() {
        JButton loginButton = getLoginButton();
        assertNotNull(loginButton);
        assertTrue(loginButton.isEnabled());
    }

    // 5. Empty username field
    @Test
    public void testEmptyUsername() {
        loginPanel.usernameField.setText("");
        loginPanel.passwordField.setText("123");

        clickLoginButton();

        assertTrue(homePage.loginBtn.isVisible());
        assertFalse(homePage.bookRideBtn.isVisible());
    }

    // 6. Empty password field
    @Test
    public void testEmptyPassword() {
        loginPanel.usernameField.setText("admin");
        loginPanel.passwordField.setText("");

        clickLoginButton();

        assertTrue(homePage.loginBtn.isVisible());
        assertFalse(homePage.bookRideBtn.isVisible());
    }

    // 7. Both fields empty
    @Test
    public void testBothFieldsEmpty() {
        loginPanel.usernameField.setText("");
        loginPanel.passwordField.setText("");

        clickLoginButton();

        assertTrue(homePage.loginBtn.isVisible());
        assertFalse(homePage.bookRideBtn.isVisible());
    }

    // 8. Password case sensitivity check
    @Test
    public void testCaseSensitivePassword() {
        loginPanel.usernameField.setText("admin");
        loginPanel.passwordField.setText("123 "); // Extra space

        clickLoginButton();

        assertFalse(homePage.bookRideBtn.isVisible());
    }

    // 9. Username case sensitivity check
    @Test
    public void testCaseSensitiveUsername() {
        loginPanel.usernameField.setText("Admin"); // Capital A
        loginPanel.passwordField.setText("123");

        clickLoginButton();

        assertFalse(homePage.bookRideBtn.isVisible());
    }


    private void clickLoginButton() {
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                ((JButton) comp).doClick();
                break;
            }
        }
    }

    private JButton getLoginButton() {
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private void clickSignupLabel() {
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof JLabel && ((JLabel) comp).getText().contains("Sign up")) {
                MouseEvent clickEvent = new MouseEvent(comp, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false);
                for (MouseAdapter ma : comp.getListeners(MouseAdapter.class)) {
                    ma.mouseClicked(clickEvent);
                }
                break;
            }
        }
    }
}
