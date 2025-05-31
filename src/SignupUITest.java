import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignupUITest {

    private homepageui homePage;
    private signupui signupPanel;

    @BeforeEach
    public void setUp() {
        homePage = new homepageui();
        signupPanel = new signupui(homePage);
    }

    // 1. Check UI components exist
    @Test
    public void testSignupFieldsExist() {
        assertNotNull(signupPanel.nameField);
        assertNotNull(signupPanel.getEmailField());
        assertNotNull(signupPanel.passwordField);
        assertNotNull(signupPanel.signupButton);
    }

    // 2. Check name field accepts input
    @Test
    public void testNameFieldInput() {
        signupPanel.nameField.setText("Alice");
        assertEquals("Alice", signupPanel.nameField.getText());
    }

    // 3. Check email field accepts input
    @Test
    public void testEmailFieldInput() {
        signupPanel.getEmailField().setText("alice@example.com");
        assertEquals("alice@example.com", signupPanel.getEmailField().getText());
    }

    // 4. Check password field accepts input
    @Test
    public void testPasswordFieldInput() {
        signupPanel.passwordField.setText("mypassword");
        assertEquals("mypassword", new String(signupPanel.passwordField.getPassword()));
    }

    // 5. Check signup button is clickable
    @Test
    public void testSignupButtonClickable() {
        assertTrue(signupPanel.signupButton.isEnabled());
    }

    // 6. Signup with empty name shows error
    @Test
    public void testSignupEmptyName() {
        signupPanel.simulateSignup("", "email@example.com", "pass");
        assertTrue(signupPanel.errorLabel.isVisible());
    }

    // 7. Signup with invalid email
    @Test
    public void testSignupInvalidEmail() {
        signupPanel.simulateSignup("John", "not-an-email", "pass123");
        assertTrue(signupPanel.errorLabel.isVisible());
    }

    // 8. Signup with short password
    @Test
    public void testSignupShortPassword() {
        signupPanel.simulateSignup("John", "john@mail.com", "12");
        assertTrue(signupPanel.errorLabel.isVisible());
    }

    // 9. Signup with all valid inputs clears fields
    @Test
    public void testFieldsClearAfterSuccessfulSignup() {
        signupPanel.simulateSignup("John", "john@mail.com", "strongpass");
        assertEquals("", signupPanel.nameField.getText());
        assertEquals("", signupPanel.getEmailField().getText());
        assertEquals("", new String(signupPanel.passwordField.getPassword()));
    }
}
