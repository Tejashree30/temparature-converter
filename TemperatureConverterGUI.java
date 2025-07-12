import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame implements ActionListener {

    private JTextField inputField;
    private JComboBox<String> fromUnitBox, toUnitBox;
    private JLabel resultLabel;
    private JButton convertButton;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputField = new JTextField();

        JLabel fromLabel = new JLabel("From:");
        fromUnitBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});

        JLabel toLabel = new JLabel("To:");
        toUnitBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        add(inputLabel);
        add(inputField);
        add(fromLabel);
        add(fromUnitBox);
        add(toLabel);
        add(toUnitBox);
        add(new JLabel()); // Empty space
        add(convertButton);
        add(new JLabel()); // Empty space
        add(resultLabel);

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit = (String) toUnitBox.getSelectedItem();

            double result = convertTemperature(inputTemp, fromUnit, toUnit);
            resultLabel.setText(String.format("Result: %.2f %s", result, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double convertTemperature(double temp, String from, String to) {
        // Convert from input unit to Celsius first
        if (from.equals("Fahrenheit")) {
            temp = (temp - 32) * 5 / 9;
        } else if (from.equals("Kelvin")) {
            temp = temp - 273.15;
        }

        // Convert from Celsius to target unit
        if (to.equals("Fahrenheit")) {
            return (temp * 9 / 5) + 32;
        } else if (to.equals("Kelvin")) {
            return temp + 273.15;
        } else {
            return temp; // Celsius to Celsius
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterGUI::new);
    }
}
