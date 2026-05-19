package com.example.messagingprojoop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

public class ChatWindow {

    public static Scene create(
            Stage stage,
            String currentUser,
            String otherUser
    ) {

        BorderPane root = new BorderPane();

        root.setStyle("-fx-background-color: #fefdf9;");

        VBox messagesBox = new VBox(10);

        messagesBox.setPadding(new Insets(15));


        ScrollPane scrollPane = new ScrollPane(messagesBox);
        scrollPane.setFitToWidth(true);

        loadMessages(messagesBox, currentUser, otherUser);

        TextField messageField = new TextField();

        messageField.setPromptText("Type message...");

        messageField.setPrefHeight(40);

        Button sendBtn = new Button("Send");

        sendBtn.setStyle(
                "-fx-background-color: #6a8f6b;" +
                "-fx-text-fill: white;"
        );

        sendBtn.setOnAction(e -> {

            String text = messageField.getText();

            if (!text.isEmpty()) {

                Message msg = new Message(
                        currentUser,
                        otherUser,
                        text
                );

                MessageManager.sendMessage(msg);

                loadMessages(messagesBox,
                        currentUser,
                        otherUser);

                messageField.clear();
            }
        });

        HBox bottom = new HBox(10, messageField, sendBtn);

        bottom.setPadding(new Insets(10));

        root.setCenter(scrollPane);

        root.setBottom(bottom);
        Timeline autoRefresh = new Timeline(

        new KeyFrame(
                Duration.seconds(1),
                e -> loadMessages(
                        messagesBox,
                        currentUser,
                        otherUser
                )
        )
);

autoRefresh.setCycleCount(
        Timeline.INDEFINITE
);

autoRefresh.play();

        return new Scene(root, 375, 650);
    }

    private static void loadMessages(
        VBox messagesBox,
        String currentUser,
        String otherUser
){

    messagesBox.getChildren().clear();

    List<String> chats =
            MessageManager.loadMessages(
                    currentUser,
                    otherUser
            );

    for(String msg : chats){

        Label label = new Label(msg);

        label.setWrapText(true);

        label.setMaxWidth(250);

        label.setPadding(
                new Insets(10)
        );

        HBox box = new HBox(label);

        if(msg.startsWith(currentUser + ":")){

            box.setAlignment(
                    Pos.CENTER_RIGHT
            );

            label.setStyle(
                    "-fx-background-color:#6a8f6b;" +
                    "-fx-text-fill:white;" +
                    "-fx-background-radius:15;"
            );

        }
        else{

            box.setAlignment(
                    Pos.CENTER_LEFT
            );

            label.setStyle(
                    "-fx-background-color:#dfe6dc;" +
                    "-fx-background-radius:15;"
            );
        }

        messagesBox.getChildren().add(box);
    }
}
 }
    