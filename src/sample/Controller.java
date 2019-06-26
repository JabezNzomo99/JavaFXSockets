package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class Controller {
    @FXML
    private JFXButton serverButton;

    @FXML
    private JFXButton clientButton;

    @FXML
    private Label portLabel;

    @FXML
    public void openServerWindow(){
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("MyServer.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Server Window");
                    stage.setScene(new Scene(root,650,500));
                    stage.setResizable(false);
                    stage.show();
                }catch (IOException exception){
                    exception.printStackTrace();
                }

            }


    @FXML
    private void openClientWindow(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("MyClient.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Client Window");
            stage.setScene(new Scene(root,700,450));
            stage.setResizable(false);
            stage.show();
        }catch (IOException exception){
            exception.printStackTrace();
        }

    }

    @FXML
    public void showAvailablePort(ActionEvent actionEvent) {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            String availablePortNumber = String.valueOf(serverSocket.getLocalPort());
            portLabel.setDisable(false);
            portLabel.setText("PORT: "+availablePortNumber);
        } catch (IOException e) {
            portLabel.setDisable(false);
            portLabel.setText("ERROR OCCURRED");
            e.printStackTrace();
        }
    }
}
