package ru.academits.pronin.temperature.view;

import javax.swing.*;
import java.awt.*;
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
            Image img = Toolkit.getDefaultToolkit().getImage("icon.png");
            frame.setIconImage(img);

            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            String[] scales = {"Celsius (°C)", "Kelvin (K)", "Fahrenheit (°F)"};
            fromScaleCombo = new JComboBox<>(scales);
            toScaleCombo = new JComboBox<>(scales);

            inputField = new JTextField(10);
            outputField = new JTextField(10);
            outputField.setEditable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            mainPanel.setLayout(new BorderLayout(10, 40));

            JPanel scalesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            JPanel ioPanel = new JPanel(new GridLayout(2, 2, 15, 15));
            JPanel inputPanel = new JPanel();
            JPanel outputPanel = new JPanel();
            JPanel buttonPanel = new JPanel();

            scalesPanel.add(new JLabel("From scale:"));
            scalesPanel.add(fromScaleCombo);
            scalesPanel.add(new JLabel("To scale:"));
            scalesPanel.add(toScaleCombo);

            inputPanel.add(new JLabel("Input value:"));
            inputPanel.add(inputField);

            outputPanel.add(new JLabel("Result:"));
            outputPanel.add(outputField);

            JButton convertButton = getJButton(frame);
            buttonPanel.add(convertButton);

            ioPanel.add(inputPanel);
            ioPanel.add(outputPanel);

            mainPanel.add(scalesPanel, BorderLayout.NORTH);
            mainPanel.add(ioPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            frame.add(mainPanel);
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