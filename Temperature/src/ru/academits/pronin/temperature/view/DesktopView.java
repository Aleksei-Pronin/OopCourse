package ru.academits.pronin.temperature.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DesktopView implements View {
    private final List<ActionListener> listeners = new ArrayList<>();
    private double celsiusTemperature;
    private JLabel resultLabel;

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("GUI Application");

            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField celsiusTemperatureField = new JTextField(20);

            JButton convertButton = new JButton("Convert");
            convertButton.addActionListener(e -> {
                try {
                    celsiusTemperature = Double.parseDouble(celsiusTemperatureField.getText());

                    for (ActionListener listener : listeners) {
                        listener.actionPerformed(e);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка", "Температура должна быть числом", JOptionPane.ERROR_MESSAGE);
                }
            });

            JLabel resultLabel = new JLabel();

            panel.add(celsiusTemperatureField);
            panel.add(convertButton);
            panel.add(resultLabel);

            frame.add(frame);

            frame.setVisible(true);
        });
    }

    @Override
    public void addConvertListener(ActionListener actionListener) {
        listeners.add(actionListener);
    }

    @Override
    public double getCelsiusTemperature() {
        return celsiusTemperature;
    }

    @Override
    public void showResults(double kelvinTemperature, double fahrenheitTemperature) {
        resultLabel.setText(String.format("Результат...", kelvinTemperature, fahrenheitTemperature));
    }
}
