import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class RideSharingUITest {

    private ridesharing rideSharingFrame;

    @Before
    public void setUp() {
        rideSharingFrame = new ridesharing("Saddar", "Islamabad");
    }

    // 1. Check UI components existence
    @Test
    public void testComponentsExist() {
        assertNotNull(rideSharingFrame.pickupPointBox);
        assertNotNull(rideSharingFrame.dropoffPointBox);
        assertNotNull(getButtonByText("Calculate Fare"));
        assertNotNull(getButtonByText("Join Ride"));
        assertNotNull(rideSharingFrame.txtTotalFare);
        assertNotNull(rideSharingFrame.lblRideTimeInfo);
    }

    // 2. Valid fare calculation
    @Test
    public void testValidFareCalculation() {
        rideSharingFrame.pickupPointBox.setSelectedItem("Saddar");
        rideSharingFrame.dropoffPointBox.setSelectedItem("Islamabad");

        getButtonByText("Calculate Fare").doClick();

        String fareText = rideSharingFrame.txtTotalFare.getText();
        assertNotNull(fareText);
        assertFalse(fareText.isEmpty());
        assertTrue(Double.parseDouble(fareText) > 0);
    }

    // 3. Join ride with fare
    @Test
    public void testJoinRideWithFare() {
        rideSharingFrame.pickupPointBox.setSelectedItem("Saddar");
        rideSharingFrame.dropoffPointBox.setSelectedItem("Islamabad");
        getButtonByText("Calculate Fare").doClick();

        assertFalse(rideSharingFrame.txtTotalFare.getText().isEmpty());

        getButtonByText("Join Ride").doClick();

        assertTrue(true); // No exception occurred
    }


    // 4. Ride time info should update when ride is selected
    @Test
    public void testRideTimeInfoUpdates() {
        JComboBox<String> ridesComboBox = getComboBoxWithRideOptions();
        assertNotNull(ridesComboBox);

        ridesComboBox.setSelectedIndex(1);

        String labelText = rideSharingFrame.lblRideTimeInfo.getText();
        assertNotNull(labelText);
        assertTrue(labelText.contains("Ride starts at:"));
    }


    // 5. Check combo box item count
    @Test
    public void testPickupAndDropoffComboBoxHasItems() {
        assertTrue(rideSharingFrame.pickupPointBox.getItemCount() > 0);
        assertTrue(rideSharingFrame.dropoffPointBox.getItemCount() > 0);
    }

    // 6. Clear fare field resets the value
    @Test
    public void testClearFareFieldManually() {
        rideSharingFrame.txtTotalFare.setText("500");
        rideSharingFrame.txtTotalFare.setText(""); // Clear

        assertEquals("", rideSharingFrame.txtTotalFare.getText());
    }

    // 7. Check GUI is visible
    @Test
    public void testFrameVisibility() {
        rideSharingFrame.setVisible(true);
        assertTrue(rideSharingFrame.isVisible());
    }


    private JButton getButtonByText(String text) {
        for (Component comp : rideSharingFrame.getContentPane().getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JComboBox<String> getComboBoxWithRideOptions() {
        for (Component comp : rideSharingFrame.getContentPane().getComponents()) {
            if (comp instanceof JComboBox) {
                JComboBox<?> box = (JComboBox<?>) comp;
                if (box.getItemCount() == 5) { 
                    return (JComboBox<String>) box;
                }
            }
        }
        return null;
    }
}
