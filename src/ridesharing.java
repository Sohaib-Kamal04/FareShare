import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ridesharing extends JFrame {

    private static final long serialVersionUID = 1L;
    JPanel contentPane;
    JTextField txtTotalFare;
    JLabel lblRideTimeInfo;
    JComboBox<String> pickupPointBox;
    JComboBox<String> dropoffPointBox;

    private final Map<String, Double> routeDistances = new HashMap<>();

    // Constructor that takes pickup and dropoff
    public ridesharing(String pickup, String dropoff) {
        super(); // Call JFrame constructor first to ensure proper initialization

        setTitle("Ride Sharing");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 966, 515);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Initialize the route distances
        initializeRouteDistances();

        // Initialize components and add them to the content pane
        pickupPointBox = new JComboBox<>(new String[] {
            "Saddar", "Pindi", "F10", "Gulzar-e-Quaid"
        });
        pickupPointBox.setBounds(100, 100, 150, 20);
        contentPane.add(pickupPointBox);

        dropoffPointBox = new JComboBox<>(new String[] {
            "Islamabad", "G10", "BlueArea", "Bahria"
        });
        dropoffPointBox.setBounds(300, 100, 150, 20);
        contentPane.add(dropoffPointBox);

        JButton btnCalcFare = new JButton("Calculate Fare");
        btnCalcFare.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnCalcFare.setBounds(100, 130, 150, 25);
        btnCalcFare.setBackground(new Color(173, 216, 230));
        btnCalcFare.addActionListener(e -> calculateFare());
        contentPane.add(btnCalcFare);

        JLabel lblAvailableRides = new JLabel("Available Shared Rides");
        lblAvailableRides.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblAvailableRides.setBounds(653, 123, 180, 13);
        contentPane.add(lblAvailableRides);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Oswa | Alto | RIA-1234 | 4.7 | Start: 4:00 PM",
            "Maheen | Cultus | RIB-5678 | 4.5 | Start: 3:45 PM",
            "Fatima | WagonR | RIC-9123 | 4.8 | Start: 4:10 PM",
            "Sohaib | City | RID-3412 | 4.3 | Start: 3:30 PM",
            "Hashim | Mehran | RIE-6532 | 4.1 | Start: 3:50 PM"
        }));
        comboBox.setBackground(SystemColor.controlHighlight);
        comboBox.setBounds(653, 158, 280, 21);
        comboBox.addActionListener(e -> updateRideTimeLabel((String)comboBox.getSelectedItem()));
        contentPane.add(comboBox);

        JLabel lblRideStart = new JLabel("Ride Time Info");
        lblRideStart.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblRideStart.setBounds(653, 189, 180, 21);
        contentPane.add(lblRideStart);

        lblRideTimeInfo = new JLabel("Select a ride to view time info.");
        lblRideTimeInfo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblRideTimeInfo.setBounds(653, 210, 300, 19);
        contentPane.add(lblRideTimeInfo);

        JLabel lblTotalFare = new JLabel("Total Fare");
        lblTotalFare.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblTotalFare.setBounds(656, 249, 132, 28);
        contentPane.add(lblTotalFare);

        txtTotalFare = new JTextField();
        txtTotalFare.setBounds(655, 280, 220, 19);
        contentPane.add(txtTotalFare);
        txtTotalFare.setColumns(10);

        JButton btnJoinRide = new JButton("Join Ride");
        btnJoinRide.setBackground(new Color(144, 238, 144));
        btnJoinRide.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnJoinRide.setBounds(712, 320, 115, 37);
        btnJoinRide.addActionListener(e -> {
            String selectedRide = (String)comboBox.getSelectedItem();
            String totalFare = txtTotalFare.getText();

            if (totalFare.isEmpty()) {
                JOptionPane.showMessageDialog(contentPane,
                        "Please calculate fare first",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(contentPane, "Successfully joined ride:\n" + selectedRide +
                            "\n\nFare: Rs " + totalFare,
                    "Ride Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });
        contentPane.add(btnJoinRide);

        JLabel lblTitle = new JLabel("Ride Sharing");
        lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 29));
        lblTitle.setBounds(681, 73, 206, 32);
        contentPane.add(lblTitle);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setBounds(623, 52, 350, 381);
        contentPane.add(editorPane);

        JLabel lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\banner-group-people-travel-companion-vector-23527834.jpg"));
        lblBackground.setBounds(-53, 10, 1089, 600);
        contentPane.add(lblBackground);

        // Set initial values for the combo boxes (pickup and dropoff points)
        pickupPointBox.setSelectedItem(pickup);
        dropoffPointBox.setSelectedItem(dropoff);
        calculateFare(); // Calculate the fare based on the selected pickup and dropoff
    }

    // Default constructor in case no pickup or dropoff is provided
    public ridesharing() {
        this("Saddar", "Islamabad"); // Default to these locations
    }

    private void initializeRouteDistances() {
        routeDistances.put("Saddar-Islamabad", 18.0);
        routeDistances.put("Saddar-G10", 15.0);
        routeDistances.put("Saddar-BlueArea", 12.0);
        routeDistances.put("Saddar-Bahria", 22.0);

        routeDistances.put("Pindi-Islamabad", 20.0);
        routeDistances.put("Pindi-G10", 17.0);
        routeDistances.put("Pindi-BlueArea", 14.0);
        routeDistances.put("Pindi-Bahria", 25.0);

        routeDistances.put("F10-Islamabad", 10.0);
        routeDistances.put("F10-G10", 5.0);
        routeDistances.put("F10-BlueArea", 8.0);
        routeDistances.put("F10-Bahria", 28.0);

        routeDistances.put("Gulzar-e-Quaid-Islamabad", 30.0);
        routeDistances.put("Gulzar-e-Quaid-G10", 28.0);
        routeDistances.put("Gulzar-e-Quaid-BlueArea", 26.0);
        routeDistances.put("Gulzar-e-Quaid-Bahria", 10.0);
    }

    private void calculateFare() {
        String pickup = (String) pickupPointBox.getSelectedItem();
        String dropoff = (String) dropoffPointBox.getSelectedItem();
        String routeKey = pickup + "-" + dropoff;

        Double distance = routeDistances.get(routeKey);
        if (distance == null) {
            JOptionPane.showMessageDialog(contentPane,
                    "No fare info available for selected route.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double baseFare = 50;
        double perKmRate = 20;
        double fare = baseFare + (perKmRate * distance);
        txtTotalFare.setText(String.format("%.2f", fare));
    }

    private void updateRideTimeLabel(String selected) {
        if (selected != null && selected.contains("Start:")) {
            String startTime = selected.substring(selected.indexOf("Start:") + 6).trim(); // Extract time only
            String pickup = (String) pickupPointBox.getSelectedItem();
            String dropoff = (String) dropoffPointBox.getSelectedItem();
            String routeKey = pickup + "-" + dropoff;

            Double distance = routeDistances.get(routeKey);
            if (distance != null) {
                // Estimate time assuming average speed = 30 km/h
                double estimatedMinutes = (distance / 30.0) * 60;
                int etaMinutes = (int) Math.round(estimatedMinutes);

                // Parse and add minutes to start time (format: 4:00 PM)
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("h:mm a");
                    java.util.Date date = sdf.parse(startTime);
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(java.util.Calendar.MINUTE, etaMinutes);

                    String arrivalTime = sdf.format(cal.getTime());
                    lblRideTimeInfo.setText("Ride starts at: " + startTime + " (Estimated Arrival: " + arrivalTime + ")");
                } catch (Exception e) {
                    lblRideTimeInfo.setText("Ride starts at: " + startTime + " (ETA unavailable)");
                }
            } else {
                lblRideTimeInfo.setText("Ride starts at: " + startTime + " (ETA unavailable)");
            }
        } else {
            lblRideTimeInfo.setText("Time info unavailable.");
        }
    }
}
