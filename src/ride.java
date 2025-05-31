import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ride extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private final Map<String, Double> routeDistances = new HashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ride frame = new ride();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ride() {
        setTitle("Ride Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initializeRouteDistances();

        JLabel lblTitle = new JLabel("RIDE BOOKING");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitle.setBounds(100, 50, 272, 56);
        contentPane.add(lblTitle);

        JLabel lblRideType = new JLabel("Ride Type");
        lblRideType.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblRideType.setBounds(100, 120, 100, 20);
        contentPane.add(lblRideType);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Solo Ride", "Shared Ride"}));
        comboBox.setBounds(100, 145, 220, 25);
        contentPane.add(comboBox);

        JLabel lblPickup = new JLabel("Pickup Location");
        lblPickup.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblPickup.setBounds(100, 180, 126, 20);
        contentPane.add(lblPickup);

        JComboBox<String> comboBoxPickup = new JComboBox<>();
        comboBoxPickup.setModel(new DefaultComboBoxModel<>(new String[] {"Saddar", "Pindi", "F10", "Gulzar-e-Quaid", "Islamabad", "G10", "BlueArea", "Bahria"}));
        comboBoxPickup.setBounds(100, 205, 220, 25);
        contentPane.add(comboBoxPickup);

        JLabel lblDrop = new JLabel("Drop Location");
        lblDrop.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblDrop.setBounds(100, 240, 126, 20);
        contentPane.add(lblDrop);

        JComboBox<String> comboBoxDrop = new JComboBox<>();
        comboBoxDrop.setModel(new DefaultComboBoxModel<>(new String[] {"Saddar", "Pindi", "F10", "Gulzar-e-Quaid", "Islamabad", "G10", "BlueArea", "Bahria"}));
        comboBoxDrop.setBounds(100, 265, 220, 25);
        contentPane.add(comboBoxDrop);

        JButton btnFindRide = new JButton("Find Ride");
        btnFindRide.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnFindRide.setBounds(140, 320, 140, 35);
        contentPane.add(btnFindRide);

        btnFindRide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rideType = (String) comboBox.getSelectedItem();
                String pickup = (String) comboBoxPickup.getSelectedItem();
                String drop = (String) comboBoxDrop.getSelectedItem();

                if (pickup.isEmpty() || drop.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane,
                            "Please select both pickup and drop locations",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (rideType.equals("Shared Ride")) {
                    ridesharing rs = new ridesharing(pickup, drop);
                    rs.setVisible(true);
                } else {
                    String routeKey = pickup + "-" + drop;
                    Double distance = routeDistances.get(routeKey);

                    if (distance == null) {
                        JOptionPane.showMessageDialog(contentPane,
                                "No route info available for entered locations.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double fare = 50 + (distance * 25); // Solo rides have slightly higher rate
                    JOptionPane.showMessageDialog(contentPane,
                            "Solo ride fare from " + pickup + " to " + drop + ": Rs " + String.format("%.2f", fare),
                            "Fare Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void initializeRouteDistances() {
        String[] locations = {"Saddar", "Pindi", "F10", "Gulzar-e-Quaid", "Islamabad", "G10", "BlueArea", "Bahria"};

        for (String from : locations) {
            for (String to : locations) {
                if (!from.equals(to)) {
                    double randomDistance = 10 + Math.random() * 25;
                    routeDistances.put(from + "-" + to, randomDistance);
                }
            }
        }
    }
}
