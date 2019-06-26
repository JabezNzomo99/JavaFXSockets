package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import sample.Models.Constants;
import sample.Models.Toy;
import sample.Server.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyServer implements Initializable {

    @FXML
    public JFXTextField portNumberField;

    @FXML
    public JFXButton startServer;

    @FXML
    public JFXRadioButton serverState;

    @FXML
    public JFXButton requestToyName;

    @FXML
    public JFXButton requestThankYouMessage;

    @FXML
    public JFXButton requestManufacturer;

    @FXML
    public JFXTextArea serverMessageBoard;

    @FXML
    public JFXButton requestToyInformation;

    @FXML
    public JFXButton requestAllToyInformation;

    private Server server;


    private void startServerEvent(javafx.event.ActionEvent event){
        if(!portNumberField.getText().isEmpty()) {
            String portNumber = portNumberField.getText();
            Integer port = Integer.parseInt(portNumber);
            System.out.println("Port Number is" + portNumber);
            server = new Server(port, this);
            server.startServer();
            serverState.setSelected(true);
            serverState.setDisable(false);
            serverState.setText("Server Started");
            requestToyName.setDisable(false);
            requestThankYouMessage.setDisable(false);
            requestManufacturer.setDisable(false);
            requestToyInformation.setDisable(false);
            requestAllToyInformation.setDisable(false);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All Fields Required!");
            alert.showAndWait();

        }
    }

    public void requestToyName(ActionEvent actionEvent){
        if(server.isConnected()){
            serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+Constants.MESSAGE_A+"\n");
            try {
                server.requestToyName();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public void requestMessage(ActionEvent actionEvent){
        if(server.isConnected()){
            serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+Constants.MESSAGE_D+"\n");
            try {
                server.requestMessage();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public void requestManufacturerDetails(ActionEvent actionEvent){
        if(server.isConnected()){
            serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+Constants.MESSAGE_C+"\n");
            try {
                server.requestManufacturerDetails();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public void requestToyInformarion(ActionEvent actionEvent){
        if(server.isConnected()){
            serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+Constants.MESSAGE_B+"\n");
            try {
                server.requestToyInformation();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
    @FXML
    public void requestAllToyInformation(ActionEvent actionEvent){
        if(server.isConnected()){
            serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"| "+Constants.MESSAGE_E+"\n");
            try {
                server.requestAllToyInformation();
            }catch (IOException exception){
                exception.printStackTrace();
            }

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startServer.setOnAction(this::startServerEvent);
        requestToyName.setOnAction(this::requestToyName);
        requestThankYouMessage.setOnAction(this::requestMessage);
        requestManufacturer.setOnAction(this::requestManufacturerDetails);
        requestToyInformation.setOnAction(this::requestToyInformarion);
        requestAllToyInformation.setOnAction(this::requestAllToyInformation);
    }

    public void postToy(Toy clientToy) {

        serverMessageBoard.appendText("|"+Util.Companion.getCurrentDateTime()+"|"+" Client : "+Util.Companion.printToyObject(clientToy)+"\n");
    }



}
