package sample.Server;

import sample.Models.Constants;
import sample.Models.Message;
import sample.Models.Toy;
import sample.MyServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int serverPort;
    private boolean isConnected = false;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private Message message;
    private MyServer serverController;

    public Server(int port, MyServer myServer) {
        serverPort = port;
        serverController = myServer;
    }

    public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                System.out.println("Waiting for clients to connect...");
                isConnected= true;
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    clientProcessingPool.submit(new ClientTask(clientSocket));
                }
            } catch (IOException e) {
                System.err.println("Unable to process client request");
                e.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public boolean isConnected(){
        return isConnected;

    }

    public void requestToyName() throws IOException {
        if(isConnected){
            message = new Message();
            message.setMessage(Constants.MESSAGE_A);
            message.setMessageCode(Constants.A);
            objectOutputStream.writeObject(message);
        }
    }

    public void requestMessage() throws IOException{
        if(isConnected){
            message = new Message();
            message.setMessage(Constants.MESSAGE_D);
            message.setMessageCode(Constants.D);
            objectOutputStream.writeObject(message);
        }
    }

    public void requestManufacturerDetails() throws IOException {
        if(isConnected){
            message = new Message();
            message.setMessage(Constants.MESSAGE_C);
            message.setMessageCode(Constants.C);
            objectOutputStream.writeObject(message);
        }
    }

    private class ClientTask implements Runnable {
        private final Socket clientSocket;

        private ClientTask(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                outputStream = clientSocket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);

                inputStream = clientSocket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try{
                while (clientSocket.getInputStream()!=null){
                    Toy clientToy = (Toy)objectInputStream.readObject();
                    System.out.println("Received from client: "+clientToy.getName());
                    serverController.postToy(clientToy);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

