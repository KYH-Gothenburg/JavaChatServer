import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket clientSocket;
    Server server;

    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // Got a connection
            System.out.println("Got connection from " + clientSocket.getInetAddress());
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                // Wait for message from client - BLOCKING
                String msg = input.readLine();
                Message message = new Message(clientSocket, msg);
                server.getBroadcastQueue().put(message);
                System.out.println("Got message: " + msg);
                // Exit if client sends the text exit
                if (msg.equals("exit")) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Client left the chat");
            server.getClientSockets().remove(clientSocket);
        }

    }
}
