import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
       Server server = new Server(9876);
       server.run();
    }
}
