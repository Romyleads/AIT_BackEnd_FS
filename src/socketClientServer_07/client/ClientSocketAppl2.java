package socketClientServer_07.client;

import socketClientServer_07.chat.task.MessageReceiver;
import socketClientServer_07.chat.task.MessageSender;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketAppl2 {
    public static void main(String[] args) {

        if (args.length == 1) {

            args = new String[]{args[0], "9000"};

        }

        if (args.length == 0) {

            args = new String[]{"127.0.0.1", "9000"};

        }

        String serverHost = args[0];

        int port = Integer.parseInt(args[1]);

        try {

            Socket socket = new Socket(serverHost, port);

            new MessageReceiver(socket);

            new Thread(new MessageSender(socket)).start();

        } catch (IOException e) {

            throw new RuntimeException(e);


        }

    }
}