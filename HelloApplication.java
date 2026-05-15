package com.example.messagingprojoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Discorf");

                // ROOT GRID (BACKGROUND)
                GridPane root = new GridPane();
                root.setAlignment(Pos.CENTER);
                root.setStyle("-fx-background-color: #dee6de;");
                root.setPadding(new Insets(20));

                // CARD GRID
                GridPane card = new GridPane();
                card.setPrefWidth(320);
                card.setVgap(16);
                card.setPadding(new Insets(30));
                card.setAlignment(Pos.CENTER);
                card.setStyle(
                        "-fx-background-color: #fefdf9;" +
                                "-fx-background-radius: 30;"
                );

                // Row index tracker
                int row = 0;

                // Title
                Label title = new Label("Welcome to Discorf");
                title.setFont(Font.font(18));
                title.setStyle("-fx-font-weight: bold;");
                card.add(title, 0, row++);
                GridPane.setHalignment(title, HPos.CENTER);

                Label subtitle = new Label("Take the first step");
                subtitle.setStyle("-fx-text-fill: #555;");
                card.add(subtitle, 0, row++);
                GridPane.setHalignment(subtitle, HPos.CENTER);

                // Inputs
                TextField email = input("E-mail");
                TextField username = input("Username");
                PasswordField password = password("Password");
                PasswordField confirm = password("Confirm password");

                card.add(email, 0, row++);
                card.add(username, 0, row++);
                card.add(password, 0, row++);
                card.add(confirm, 0, row++);

                // Sign Up Button
                Button signUp = new Button("Sign up");
                signUp.setPrefSize(260, 45);
                signUp.setStyle(
                        "-fx-background-color: #6a8f6b;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 15;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 25;"
                );
                card.add(signUp, 0, row++);
                GridPane.setHalignment(signUp, HPos.CENTER);

                // Divider (GridPane trick)
                Separator left = new Separator();
                Separator right = new Separator();
                Label or = new Label("or");

                GridPane divider = new GridPane();
                divider.setHgap(10);
                divider.add(left, 0, 0);
                divider.add(or, 1, 0);
                divider.add(right, 2, 0);
                divider.setAlignment(Pos.CENTER);

                left.setPrefWidth(80);
                right.setPrefWidth(80);

                card.add(divider, 0, row++);

                // Footer
                Label footer = new Label("Already have an account?");
                footer.setStyle("-fx-font-size: 12; -fx-text-fill: #555;");
        Button signin = new Button("Sign In");
        signin.setStyle(
                "-fx-background-radius: 20;" +
                        "-fx-background-color: white;" +
                        "-fx-border-color: transparent;"
        );
                card.add(footer, 0, row++);
                 card.add(signin, 0, row++);
                GridPane.setHalignment(footer, HPos.CENTER);
        GridPane.setHalignment(signin, HPos.CENTER);

        Scene scene2 = new Scene(card, 375, 650);//filler
        signin.setOnAction(e -> {
            stage.setScene(scene2);
        });
                // Add card to root
                root.add(card, 0, 0);

                Scene scene = new Scene(root, 375, 650);

                stage.setScene(scene);
                stage.show();
            }

            private TextField input(String prompt) {
                TextField field = new TextField();
                field.setPromptText(prompt);
                field.setPrefHeight(45);
                field.setStyle(
                        "-fx-background-radius: 20;" +
                                "-fx-background-color: #dfe6dc;" +
                                "-fx-border-color: transparent;" +
                                "-fx-padding: 10 15;"
                );
                return field;
            }

            private PasswordField password(String prompt) {
                PasswordField field = new PasswordField();
                field.setPromptText(prompt);
                field.setPrefHeight(45);
                field.setStyle(
                        "-fx-background-radius: 20;" +
                                "-fx-background-color: #dfe6dc;" +
                                "-fx-border-color: transparent;" +
                                "-fx-padding: 10 15;"
                );
                return field;
            }



    }

