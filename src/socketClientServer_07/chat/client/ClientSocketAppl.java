package socketClientServer_07.chat.client;

import socketClientServer_07.chat.task.MessageReceiver;
import socketClientServer_07.chat.task.MessageSender;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketAppl {
    public static void main(String[] args) {
        // If only host is given, use default port 9000
        if (args.length == 1) {
            args = new String[]{args[0], "9000"};
        }

        // If nothing is given, use default host and port
        if (args.length == 0) {
            args = new String[]{"127.0.0.1", "9000"};
        }

        String serverHost = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            // Connect to server
            Socket socket = new Socket(serverHost, port);

            // Start thread to receive messages from server
            new MessageReceiver(socket);

            // Start thread to send messages to server
            new Thread(new MessageSender(socket)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
