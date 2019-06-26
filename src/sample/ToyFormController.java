package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.Client.Client;
import sample.Models.Constants;
import sample.Models.Message;
import sample.Models.Toy;
import sample.Models.ToyManfacturer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToyFormController implements Initializable{
    private Client client;
    private Message message;
    private Toy toy;
    private ToyManfacturer toyManfacturer;
    @FXML
    public JFXTextField textFieldToyName;

    @FXML
    public JFXTextField textFieldToyCode;

    @FXML
    public JFXTextField textFieldToyDescription;

    @FXML
    public JFXTextField textFieldToyPrice;

    @FXML
    public JFXDatePicker datePicker;

    @FXML
    public JFXTextField textFieldManufacturerName;

    @FXML
    public JFXTextField textFieldManufacturerStreetAddress;

    @FXML
    public JFXTextField textFieldManufacturerZipCode;

    @FXML
    public JFXTextField textFieldManufacturerCountry;



    @FXML
    public JFXButton btnSubmitToy;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMessage(Message message){
        this.message = message;
    }


    public void submitToy(ActionEvent actionEvent){
        toy=new Toy();
        toyManfacturer = new ToyManfacturer();
        if(!textFieldToyName.getText().isEmpty() ) {
                toy.setName(textFieldToyName.getText().trim());
            }
        if(!textFieldToyCode.getText().isEmpty()) {
            toy.setCode(textFieldToyCode.getText().trim());
        }
        if(!textFieldToyDescription.getText().isEmpty()) {
            toy.setDescription(textFieldToyDescription.getText().trim());
        }
        if(!textFieldToyPrice.getText().isEmpty()) {
            toy.setPrice(Integer.parseInt(textFieldToyPrice.getText()));
        }
        if(datePicker.getValue()!=null){
            toy.setDom(datePicker.getValue().toString().trim());

        }
        if(!textFieldManufacturerName.getText().isEmpty()) {
            toyManfacturer.setCompanyName(textFieldManufacturerName.getText().trim());
        }
        if(!textFieldManufacturerZipCode.getText().isEmpty()) {
            toyManfacturer.setZipCode(textFieldManufacturerZipCode.getText().trim());
        }
        if(!textFieldManufacturerStreetAddress.getText().isEmpty()) {
            toyManfacturer.setStreetAddress(textFieldManufacturerStreetAddress.getText().trim());
        }
        if(!textFieldManufacturerCountry.getText().isEmpty()) {
            toyManfacturer.setCountry(textFieldManufacturerCountry.getText().trim());
        }
        toy.setToyManfacturer(toyManfacturer);
        try {
            client.sendToyForm(toy);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmitToy.setOnAction(this::submitToy);
    }
}
