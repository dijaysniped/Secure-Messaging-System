package com.example.messagingprojoop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class ChatDashboard {

    public static Scene create(Stage stage, String currentUser) {

        BorderPane root = new BorderPane();


        //root.setStyle("-fx-background-color: #dee6de;");

        VBox usersBox = new VBox(10);
        ScrollPane scrollPane1 = new ScrollPane(usersBox);
        usersBox.setStyle("-fx-background-color: #dee6de;");
        scrollPane1.setFitToWidth(true);


        usersBox.setPadding(new Insets(20));

        usersBox.setAlignment(Pos.TOP_CENTER);

        Label heading = new Label("Users");

        heading.setStyle("-fx-font-size: 22; -fx-font-weight: bold;");

        usersBox.getChildren().add(heading);

        List<String> users = UserManager.getAllUsers();

        for (String user : users) {

            if (!user.equals(currentUser)) {

                Button userBtn = new Button(user);

                userBtn.setPrefWidth(220);

                userBtn.setStyle(
                        "-fx-background-color: #6a8f6b;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 15;"
                );

                userBtn.setOnAction(e -> {

                    Scene chatScene =
                            ChatWindow.create(
                                    stage,
                                    currentUser,
                                    user
                            );

                    stage.setScene(chatScene);
                });

                usersBox.getChildren().add(userBtn);
            }
        }

        root.setCenter(scrollPane1);



        return new Scene(root, 375, 650);
    }
}
