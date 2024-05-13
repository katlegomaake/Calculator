import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {

    private JTextField display;

    private double firstNumber;
    private String operator;

    public CalculatorApp() {
        setTitle("Basic Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4)); // Increased rows to accommodate new buttons

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "Del", "C", "Ans" // Added buttons for delete, clear, and answer
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "+":
                case "-":
                case "*":
                case "/":
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("");
                    break;
                case "=":
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = calculateResult(firstNumber, secondNumber, operator);
                    display.setText(String.valueOf(result));
                    break;
                case "C": // Clear
                    display.setText("");
                    break;
                case "Del": // Delete
                    String currentText = display.getText();
                    if (currentText.length() > 0) {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
                case "Ans": // Answer
                    // Optionally implement a feature to store and recall previous answers
                    break;
                default:
                    display.setText(display.getText() + command);
            }
        }
    }

    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber != 0)
                    return firstNumber / secondNumber;
                else
                    return Double.NaN; // Indicate division by zero
            default:
                return Double.NaN;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }
}
