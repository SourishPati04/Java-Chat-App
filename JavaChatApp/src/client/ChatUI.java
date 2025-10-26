package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import util.Encryption;
import javafx.scene.control.TextField;
import client.ClientCode;



public  class ChatUI extends Application{
    private ClientCode client;
    private TextArea chatArea;
    private TextField inputField;



    public void start(Stage stage) throws Exception{
        client = new ClientCode("localhost", 12345);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        inputField = new TextField();
        Button sendButton = new Button("send");

        sendButton.setOnAction(e -> sendMessage());

        VBox layout = new VBox(10, chatArea, new HBox(10, inputField, sendButton));

        stage.setScene(new Scene(layout, 400, 300));
        stage.setTitle("Java Chat Client");
        stage.show();

        new Thread(() -> {
            try {
                String line;
                while ((line = client.getInput().readLine()) != null) {
                    String msg = util.Encryption.decrypt(line);
                    Platform.runLater(() -> chatArea.appendText(msg + "\n"));
                }
            } catch (IOException e) {
                Platform.runLater(() -> chatArea.appendText("Connection closed.\n"));
            }
        }).start();
    }

    private void sendMessage() {
        String msg = inputField.getText();
        if (!msg.isEmpty()) {
            client.sendMessage(msg);
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);


    }


}