import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Map;

public class RideUITest {

    private ride rideFrame;

    @BeforeEach
    public void setUp() {
        rideFrame = new ride();
    }

    @Test
    public void testRouteDistancesAreInitialized() throws Exception {
        Field field = ride.class.getDeclaredField("routeDistances");
        field.setAccessible(true);
        Map<String, Double> distances = (Map<String, Double>) field.get(rideFrame);

        assertFalse(distances.isEmpty(), "Route distances should be initialized");
        assertTrue(distances.containsKey("Saddar-Pindi"), "Route Saddar-Pindi should exist");
    }

    @Test
    public void testFareCalculationSoloRide() throws Exception {
        Field field = ride.class.getDeclaredField("routeDistances");
        field.setAccessible(true);
        Map<String, Double> distances = (Map<String, Double>) field.get(rideFrame);

        double testDistance = 10.0;
        distances.put("Islamabad-G10", testDistance);

        double expectedFare = 50 + (testDistance * 25); // Solo fare
        double actualFare = 50 + (distances.get("Islamabad-G10") * 25);

        assertEquals(expectedFare, actualFare, 0.01, "Fare should be calculated correctly for solo ride");
    }

    @Test
    public void testNoFareForSamePickupAndDrop() throws Exception {
        Field field = ride.class.getDeclaredField("routeDistances");
        field.setAccessible(true);
        Map<String, Double> distances = (Map<String, Double>) field.get(rideFrame);

        Double fare = distances.get("Pindi-Pindi");

        assertNull(fare, "Fare should be null when pickup and drop are same");
    }

    @Test
    public void testRandomDistanceRange() throws Exception {
        Field field = ride.class.getDeclaredField("routeDistances");
        field.setAccessible(true);
        Map<String, Double> distances = (Map<String, Double>) field.get(rideFrame);

        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            double dist = entry.getValue();
            assertTrue(dist >= 10 && dist <= 35, "Distance should be between 10 and 35");
        }
    }

    @Test
    public void testDifferentLocationsHaveDifferentDistances() throws Exception {
        Field field = ride.class.getDeclaredField("routeDistances");
        field.setAccessible(true);
        Map<String, Double> distances = (Map<String, Double>) field.get(rideFrame);

        Double d1 = distances.get("Saddar-Pindi");
        Double d2 = distances.get("Pindi-Saddar");

        assertNotEquals(d1, d2, "Saddar-Pindi and Pindi-Saddar should have different distances (directional)");
    }
}
