import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {
    ServerSocket serverSocket;
    ArrayList<Socket> clientSockets;
    BlockingQueue<Message> broadcastQueue;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSockets = new ArrayList<>();
            broadcastQueue = new LinkedBlockingDeque<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            new BroadcastThread(this).start();
            System.out.println("Waiting for connection...");
            // Wait for connection - BLOCKING
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);
                new ClientThread(clientSocket, this).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Socket> getClientSockets() {
        return clientSockets;
    }

    public BlockingQueue<Message> getBroadcastQueue() {
        return broadcastQueue;
    }
}
