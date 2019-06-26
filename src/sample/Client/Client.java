package sample.Client;

import javafx.scene.paint.Color;
import sample.Models.Message;
import sample.Models.Toy;
import sample.MyClientController;
import sample.Server.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private int serverPort;
    private String serverName;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private MyClientController myClientController;
    private ObjectOutputStream objectOutputStream;
    private OutputStream outputStream;

    public Client(int port, String name, MyClientController myClientController){
        serverPort = port;
        serverName = name;
        this.myClientController = myClientController;
    }

    public void connectToServer(){
        Runnable serverTask = () -> {
            try {
                Socket clientSocket = new Socket(serverName,serverPort);
                System.out.println("Client Connected...");
                myClientController.clientStateRadioButton.setSelected(true);
                myClientController.clientStateRadioButton.setDisable(false);
                inputStream = clientSocket.getInputStream();
                objectInputStream= new ObjectInputStream(inputStream);
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                while (clientSocket.getInputStream()!=null){
                    Message message = (Message)objectInputStream.readObject();
                    myClientController.postMessage(message);
                }

            } catch (IOException e) {
                System.err.println("An error occurred");
                e.printStackTrace();
            }catch (ClassNotFoundException classNotFound){
                classNotFound.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public void sendToy(Toy clientToy) throws IOException{
        System.out.println("Client Sending ......:"+ clientToy.getName());
        objectOutputStream.writeObject(clientToy);
    }

    public void sendToyForm(Toy clientToy) throws IOException{
        myClientController.printToyObject(clientToy);
        System.out.println("Client Sending ......:"+ clientToy.getName());
        objectOutputStream.writeObject(clientToy);

    }
}
