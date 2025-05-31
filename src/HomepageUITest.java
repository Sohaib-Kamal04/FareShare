import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HomepageUITest {

    private homepageui homePage;

    @BeforeEach
    public void setUp() {
        homePage = new homepageui();
    }

    // 1. Initial visibility of buttons
    @Test
    public void testInitialButtonVisibility() {
        assertTrue(homePage.loginBtn.isVisible());
        assertTrue(homePage.signUpBtn.isVisible());
        assertFalse(homePage.getBookRideBtn().isVisible());
        assertFalse(homePage.getShareRideBtn().isVisible());
        assertFalse(homePage.getSignOutBtn().isVisible());
    }

    // 2. Simulate user login
    @Test
    public void testSimulateLogin() {
        homePage.simulateLogin("login");

        assertTrue(homePage.getBookRideBtn().isVisible());
        assertTrue(homePage.getShareRideBtn().isVisible());
        assertTrue(homePage.getSignOutBtn().isVisible());
        assertFalse(homePage.loginBtn.isVisible());
        assertFalse(homePage.signUpBtn.isVisible());
    }

    // 3. Simulate sign out
    @Test
    public void testSignOutButton() {
        homePage.simulateLogin("login");
        homePage.getSignOutBtn().doClick();

        assertFalse(homePage.getBookRideBtn().isVisible());
        assertFalse(homePage.getShareRideBtn().isVisible());
        assertFalse(homePage.getSignOutBtn().isVisible());
        assertTrue(homePage.loginBtn.isVisible());
        assertTrue(homePage.signUpBtn.isVisible());
    }

    // 4. Book button click (UI state unchanged, but clickable)
    @Test
    public void testBookRideButtonClickable() {
        homePage.simulateLogin("login");
        assertTrue(homePage.getBookRideBtn().isEnabled());
        homePage.getBookRideBtn().doClick(); 
    }

    // 5. Share button click (UI state unchanged, but clickable)
    @Test
    public void testShareRideButtonClickable() {
        homePage.simulateLogin("login");
        assertTrue(homePage.getShareRideBtn().isEnabled());
        homePage.getShareRideBtn().doClick(); 
    }

    // 6. Login when already logged in (should not crash)
    @Test
    public void testLoginWhenAlreadyLoggedIn() {
        homePage.simulateLogin("login");
        homePage.simulateLogin("login"); 
        assertTrue(homePage.getSignOutBtn().isVisible()); 
    }

    // 7. SignOut button disabled before login
    @Test
    public void testSignOutButtonInitiallyDisabled() {
        assertFalse(homePage.getSignOutBtn().isVisible());
    }

    // 8. simulateLogin with unexpected role (fallback to normal login)
    @Test
    public void testSimulateLoginWithUnknownRole() {
        homePage.simulateLogin("admin"); 
        assertTrue(homePage.getBookRideBtn().isVisible()); 
    }

    // 9. Sign up button clickable
    @Test
    public void testSignUpButtonClickable() {
        assertTrue(homePage.signUpBtn.isEnabled());
        homePage.signUpBtn.doClick(); 
    }

    // 10. Login button clickable
    @Test
    public void testLoginButtonClickable() {
        assertTrue(homePage.loginBtn.isEnabled());
        homePage.loginBtn.doClick(); 
    }
}
