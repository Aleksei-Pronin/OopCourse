package ru.academits.pronin.temperature.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private final List<ActionListener> listeners = new ArrayList<>();
    private JTextField outputField;
    private JTextField inputField;

    private JComboBox<String> fromScaleCombo;
    private JComboBox<String> toScaleCombo;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");

            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            String[] scales = {"Celsius (°C)", "Kelvin (K)", "Fahrenheit (°F)"};
            fromScaleCombo = new JComboBox<>(scales);
            toScaleCombo = new JComboBox<>(scales);

            inputField = new JTextField(10);
            outputField = new JTextField(10);
            outputField.setEditable(false);

            JPanel panel = new JPanel();

            JButton convertButton = getJButton(frame);

            panel.add(new JLabel("From scale:"));
            panel.add(fromScaleCombo);

            panel.add(new JLabel("Input value:"));
            panel.add(inputField);

            panel.add(new JLabel("To scale:"));
            panel.add(toScaleCombo);

            panel.add(new JLabel("Result:"));
            panel.add(outputField);

            panel.add(convertButton);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private JButton getJButton(JFrame frame) {
        JButton convertButton = new JButton("Convert");

        convertButton.addActionListener(e -> {
            try {
                for (ActionListener listener : listeners) {
                    listener.actionPerformed(e);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The temperature must be a number",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "The temperature cannot be below absolute zero",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return convertButton;
    }

    @Override
    public void addConvertListener(ActionListener actionListener) {
        listeners.add(actionListener);
    }

    @Override
    public String getInputScale() {
        return (String) fromScaleCombo.getSelectedItem();
    }

    @Override
    public String getOutputScale() {
        return (String) toScaleCombo.getSelectedItem();
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputField.getText());
    }

    @Override
    public void showOutputTemperature(double outputTemperature) {
        outputField.setText(String.format("%.2f", outputTemperature));
    }
}