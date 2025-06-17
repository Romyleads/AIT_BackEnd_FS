package socketClientServer_07.chat.client;

import socketClientServer_07.chat.task.MessageReceiver;
import socketClientServer_07.chat.task.MessageSender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocketOptApp {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 9000;

        // override port if given
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        // reconnect loop
        while (true) {
            try (Socket socket = new Socket()) {
                System.out.println("Trying to connect to server...");

                // 5-second timeout for connect
                socket.connect(new InetSocketAddress(host, port), 5000);

                // notify successful connection
                System.out.println("Connected to server: " + socket);

                // start receiver thread
                new MessageReceiver(socket);

                // start sender thread
                new Thread(new MessageSender(socket)).start();

                // keep alive until closed
                while (!socket.isClosed()) {
                    Thread.sleep(1000);
                }

                break; // exit after disconnect

            } catch (IOException | InterruptedException e) {
                // connection failed handler
                System.out.println("Connection failed: " + e.getMessage());
                System.out.println("Retrying in 3 seconds...\n");

                // wait before retry
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    // interrupted exit
                    System.out.println("Interrupted, exiting...");
                    break;
                }
            }
        }
    }
}
