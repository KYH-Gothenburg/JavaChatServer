import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9876);

            System.out.println("Waiting for connection...");
            // Wait for connection - BLOCKING
            // Lägg raden nedan i en loop
            Socket clientSocket = serverSocket.accept();
            // starta en thread för denna klient
            // när vi skapar tråden behöver vi skicka över clientSocket

            // Allt nedanför skall göras i tråden

            // Got a connection
            System.out.println("Got connection from " + clientSocket.getInetAddress());
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            while(true) {
                // Wait for message from client - BLOCKING
                String message = input.readLine();
                System.out.println("Got message: " + message);
                // Exit if client sends the text exit
                if(message.equals("exit")) {
                    break;
                }
                // Send message to client
                output.println("You said: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
