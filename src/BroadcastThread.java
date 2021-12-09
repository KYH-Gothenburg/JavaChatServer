import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BroadcastThread extends Thread{
    Server server;

    public BroadcastThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = server.getBroadcastQueue().take();
                for(var clientSocket : server.getClientSockets()) {
                    if(clientSocket != message.getSenderSocket()) {
                        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                        output.println(message.getMessage());
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}

