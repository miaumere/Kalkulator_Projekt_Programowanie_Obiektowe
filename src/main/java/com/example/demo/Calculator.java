package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField displayField;
    private double num1 = 0.0;
    private double num2 = 0.0;
    private String operator = "";
    private boolean start = true;
    private Stage primaryStage;

    public Calculator() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10.0);
        root.setVgap(10.0);
        root.setPadding(new Insets(25.0, 25.0, 25.0, 25.0));
        this.displayField = new TextField();
        this.displayField.setAlignment(Pos.CENTER_RIGHT);
        root.add(this.displayField, 0, 0, 4, 1);
        String[] buttons = new String[]{"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "C", "0", "=", "/"};

        for(int i = 0; i < buttons.length; ++i) {
            Button button = new Button(buttons[i]);
            button.setMinSize(40.0, 40.0);
            int row = i / 4;
            int col = i % 4;
            root.add(button, col, row + 1);
            button.setOnAction((event) -> {
                switch (((Button)event.getSource()).getText()) {
                    case "C":
                        this.start = true;
                        this.displayField.setText("");
                        this.operator = "";
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        this.operator = buttonText;
                        if (!this.start) {
                            this.num1 = Double.parseDouble(this.displayField.getText());
                            this.start = true;
                        }
                        break;
                    case "=":
                        if (!this.operator.isEmpty()) {
                            this.num2 = Double.parseDouble(this.displayField.getText());
                            double result = 0.0;
                            switch (this.operator) {
                                case "+":
                                    result = this.num1 + this.num2;
                                    break;
                                case "-":
                                    result = this.num1 - this.num2;
                                    break;
                                case "*":
                                    result = this.num1 * this.num2;
                                    break;
                                case "/":
                                    result = this.num1 / this.num2;
                            }

                            this.displayField.setText(String.valueOf(result));
                            this.start = true;
                        }
                        break;
                    default:
                        if (this.start) {
                            this.displayField.setText(buttonText);
                            this.start = false;
                        } else {
                            TextField var10000 = this.displayField;
                            String var10001 = this.displayField.getText();
                            var10000.setText(var10001 + buttonText);
                        }
                }

                Scene scene = new Scene(root, 250.0, 275.0);
                primaryStage.setTitle("Calculator");
                primaryStage.setScene(scene);
                primaryStage.show();
            });
        }

    }
}
}