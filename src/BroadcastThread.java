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
                // TODO: Loopa igen alla klienter som är uppkopplade
                    // TODO: Om den aktuella socketen inte är avsändar socketen
                        // TODO: Skicka textmeddelandet till den aktuella socketen
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
