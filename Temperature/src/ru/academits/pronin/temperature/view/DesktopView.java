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

    private JComboBox<String> fromScaleComboBox;
    private JComboBox<String> toScaleComboBox;

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
            fromScaleComboBox = new JComboBox<>(scales);
            toScaleComboBox = new JComboBox<>(scales);

            inputField = new JTextField(10);
            outputField = new JTextField(10);
            outputField.setEditable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            mainPanel.setLayout(new BorderLayout(10, 40));

            JPanel captionPanel = new JPanel();
            JPanel calculationPanel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel();

            JLabel captionLabel = new JLabel ("Convert temperature:");
            captionLabel.setFont(captionLabel.getFont().deriveFont(Font.BOLD, 16));
            captionPanel.add(captionLabel);

            JPanel scalesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            scalesPanel.add(new JLabel("From scale:"));
            scalesPanel.add(fromScaleComboBox);
            scalesPanel.add(new JLabel("To scale:"));
            scalesPanel.add(toScaleComboBox);

            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Input value:"));
            inputPanel.add(inputField);
            inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            JPanel outputPanel = new JPanel();
            outputPanel.add(new JLabel("Result:"));
            outputPanel.add(outputField);
            outputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            calculationPanel.add(scalesPanel, BorderLayout.NORTH);
            calculationPanel.add(inputPanel, BorderLayout.CENTER);
            calculationPanel.add(outputPanel, BorderLayout.SOUTH);

            JButton convertButton = getJButton(frame);
            buttonPanel.add(convertButton);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

            mainPanel.add(captionPanel, BorderLayout.NORTH);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            mainPanel.add(calculationPanel, BorderLayout.CENTER);

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
        return (String) fromScaleComboBox.getSelectedItem();
    }

    @Override
    public String getOutputScale() {
        return (String) toScaleComboBox.getSelectedItem();
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