package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorJavaFX extends Application {

    private final TextField display = new TextField();
    private String currentOperator = "";
    private double firstOperand = 0;

    @Override
    public void start(Stage stage) {
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px; -fx-pref-height: 50px;");
        display.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        String[][] buttonTexts = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };

        for (int row = 0; row < buttonTexts.length; row++) {
            for (int col = 0; col < buttonTexts[row].length; col++) {
                String text = buttonTexts[row][col];
                Button button = new Button(text);
                button.setStyle("-fx-font-size: 18px; -fx-pref-width: 60px; -fx-pref-height: 60px;");
                button.setOnAction(e -> handleInput(text));
                grid.add(button, col, row);
            }
        }

        VBox root = new VBox(10, display, grid);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 280, 350);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void handleInput(String value) {
        switch (value) {
            case "C":
                display.clear();
                currentOperator = "";
                firstOperand = 0;
                break;
            case "=":
                calculate();
                break;
            case "+": case "-": case "*": case "/":
                if (!display.getText().isEmpty()) {
                    firstOperand = Double.parseDouble(display.getText());
                    currentOperator = value;
                    display.clear();
                }
                break;
            default:
                display.appendText(value);
        }
    }

    private void calculate() {
        if (currentOperator.isEmpty() || display.getText().isEmpty()) return;
        double secondOperand = Double.parseDouble(display.getText());
        double result = switch (currentOperator) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> (secondOperand != 0) ? firstOperand / secondOperand : 0;
            default -> 0;
        };

        display.setText(String.valueOf(result));
        currentOperator = "";
        firstOperand = 0;
    }

    public static void main(String[] args) {
        launch();
    }
}
