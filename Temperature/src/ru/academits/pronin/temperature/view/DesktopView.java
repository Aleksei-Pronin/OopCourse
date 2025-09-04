package ru.academits.pronin.temperature.view;

import ru.academits.pronin.temperature.scale.Celsius;
import ru.academits.pronin.temperature.scale.Fahrenheit;
import ru.academits.pronin.temperature.scale.Kelvin;
import ru.academits.pronin.temperature.scale.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private final List<ActionListener> listeners = new ArrayList<>();

    private JTextField outputField;
    private JTextField inputField;

    private final Scale[] scales = new Scale[]{
            new Celsius(),
            new Fahrenheit(),
            new Kelvin()
    };

    private JComboBox<Scale> fromScaleComboBox;
    private JComboBox<Scale> toScaleComboBox;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature converter");
            Image img = Toolkit.getDefaultToolkit().getImage("icon.png");

            frame.setIconImage(img);
            frame.setSize(500, 350);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);

            fromScaleComboBox = new JComboBox<>(scales);
            toScaleComboBox = new JComboBox<>(scales);

            inputField = new JTextField(10);
            outputField = new JTextField(10);
            outputField.setEditable(false);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel captionLabel = new JLabel("Convert temperature:");
            captionLabel.setFont(captionLabel.getFont().deriveFont(Font.BOLD, 16));

            JPanel captionPanel = new JPanel();
            captionPanel.add(captionLabel);

            JPanel scalesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            scalesPanel.add(new JLabel("From scale:"));
            scalesPanel.add(fromScaleComboBox);
            scalesPanel.add(new JLabel("To scale:"));
            scalesPanel.add(toScaleComboBox);

            JLabel inputLabel = new JLabel("Input temperature:");
            inputLabel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));

            JLabel outputLabel = new JLabel("Result:");
            outputLabel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(inputField, BorderLayout.CENTER);
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 70));

            JPanel outputPanel = new JPanel(new BorderLayout());
            outputPanel.add(outputField, BorderLayout.CENTER);
            outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 70));

            JPanel ioPanel = new JPanel(new GridLayout(2, 2));
            ioPanel.add(inputLabel);
            ioPanel.add(inputPanel);
            ioPanel.add(outputLabel);
            ioPanel.add(outputPanel);

            JPanel calculationPanel = new JPanel(new BorderLayout());
            calculationPanel.add(scalesPanel, BorderLayout.NORTH);
            calculationPanel.add(ioPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton convertButton = getJButton(frame);
            buttonPanel.add(convertButton);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

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
    public Scale getInputScale() {
        return (Scale) fromScaleComboBox.getSelectedItem();
    }

    @Override
    public Scale getOutputScale() {
        return (Scale) toScaleComboBox.getSelectedItem();
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