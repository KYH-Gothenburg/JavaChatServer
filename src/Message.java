import java.net.Socket;

public class Message {
    Socket senderSocket;
    String message;

    public Message(Socket senderSocket, String message) {
        this.senderSocket = senderSocket;
        this.message = message;
    }

    public Socket getSenderSocket() {
        return senderSocket;
    }

    public void setSenderSocket(Socket senderSocket) {
        this.senderSocket = senderSocket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
