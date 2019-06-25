package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import sample.Client.Client;
import sample.Models.Constants;
import sample.Models.Message;
import sample.Models.Toy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class MyClientController implements Initializable {

    @FXML
    public JFXTextField portNumber;

    @FXML
    public JFXTextField serverName;

    @FXML
    public JFXButton btnConnect;

    @FXML
    public JFXTextArea messageBoard;

    @FXML
    public JFXTextField toyField;

    @FXML
    public JFXButton sendMessage;

    @FXML
    public JFXRadioButton clientStateRadioButton;

    private Client client;

    public void connectToServer(ActionEvent actionEvent){
        if(!portNumber.getText().isEmpty() && !serverName.getText().isEmpty()){
            client = new Client(Integer.parseInt(portNumber.getText()),serverName.getText(),this);
            client.connectToServer();

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All Fields Required!");
            alert.showAndWait();

        }

    }

    public void sendToy(ActionEvent actionEvent){
        if(!toyField.getText().isEmpty()){
            String message = toyField.getText().trim();
            Toy clientToy = new Toy();
            clientToy.setName(message);

            if (message.contains("Thank you") || message.contains("Thanks")) {
                UUID uuid = UUID.randomUUID();
                message= message.concat("-"+uuid.toString());
                clientToy.setName(message);

            }
            try{
                messageBoard.appendText("Client : "+ message +"\n");
                client.sendToy(clientToy);
                toyField.clear();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server name required!",ButtonType.CLOSE,ButtonType.CANCEL);
            alert.showAndWait();

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnConnect.setOnAction(this::connectToServer);
        sendMessage.setOnAction(this::sendToy);

    }

    public void postMessage(Message message){
        if(message.getMessageCode() == Constants.WELCOME){
            clientStateRadioButton.setSelected(true);
            clientStateRadioButton.setSelectedColor(new Color(0,255,0,1));
            clientStateRadioButton.setText("Connected");
        }

        messageBoard.appendText(message.getMessage()+"\n");
    }
}
