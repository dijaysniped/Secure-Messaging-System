package com.example.messagingprojoop;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignInScene {

    public static Scene create(Stage stage, Scene signUpScene) {
        // ROOT GRID
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #dee6de;");
        root.setPadding(new Insets(20));

        // CARD GRID
        GridPane card = new GridPane();
        card.setPrefWidth(320);
        card.setVgap(20);
        card.setPadding(new Insets(40, 30, 40, 30));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #fefdf9; -fx-background-radius: 30;");

        int row = 0;

        // Title
        Label title = new Label("Welcome Back");
        title.setFont(Font.font(22));
        title.setStyle("-fx-font-weight: bold;");
        card.add(title, 0, row++);
        GridPane.setHalignment(title, HPos.CENTER);

        Label subtitle = new Label("Login to your account");
        subtitle.setStyle("-fx-text-fill: #555;");
        card.add(subtitle, 0, row++);
        GridPane.setHalignment(subtitle, HPos.CENTER);

        // Reusing your style for inputs
        TextField email = createStyledInput("Username or E-mail");
        PasswordField password = createStyledPassword("Password");

        card.add(email, 0, row++);
        card.add(password, 0, row++);

        // Sign In Button
        Button loginBtn = new Button("Login");
        loginBtn.setPrefSize(260, 45);
        loginBtn.setStyle(
                "-fx-background-color: #6a8f6b; -fx-text-fill: white; " +
                "-fx-font-size: 15; -fx-font-weight: bold; -fx-background-radius: 25;"
        );
        card.add(loginBtn, 0, row++);
        GridPane.setHalignment(loginBtn, HPos.CENTER);

        // Back to Sign Up
        Hyperlink backToSignUp = new Hyperlink("Don't have an account? Sign Up");
        backToSignUp.setStyle("-fx-text-fill: #6a8f6b; -fx-underline: false;");
        backToSignUp.setOnAction(e -> stage.setScene(signUpScene));
        
        card.add(backToSignUp, 0, row++);
        GridPane.setHalignment(backToSignUp, HPos.CENTER);

        root.add(card, 0, 0);
        return new Scene(root, 375, 650);
    }

    // Helper methods to match your existing UI style
    private static TextField createStyledInput(String prompt) {
        TextField f = new TextField();
        f.setPromptText(prompt);
        f.setPrefHeight(45);
        f.setStyle("-fx-background-radius: 20; -fx-background-color: #dfe6dc; -fx-padding: 10 15;");
        return f;
    }

    private static PasswordField createStyledPassword(String prompt) {
        PasswordField f = new PasswordField();
        f.setPromptText(prompt);
        f.setPrefHeight(45);
        f.setStyle("-fx-background-radius: 20; -fx-background-color: #dfe6dc; -fx-padding: 10 15;");
        return f;
    }
}
