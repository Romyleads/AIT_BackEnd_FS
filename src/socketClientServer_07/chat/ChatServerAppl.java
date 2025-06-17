package socketClientServer_07.chat;

import socketClientServer_07.chat.task.ChatServerReceiver;
import socketClientServer_07.chat.task.ChatServerSender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ChatServerAppl {
    public static void main(String[] args) throws InterruptedException {
        int port = 9000;

        // Sender reads messages from this queue
        BlockingQueue<String> messageBox = new ArrayBlockingQueue<>(10);

        // Sends messages to all clients
        ChatServerSender sender = new ChatServerSender(messageBox);
        Thread senderThread = new Thread(sender);
        senderThread.setDaemon(true);
        senderThread.start();

        // Receivers threads for reading messages from clients
        ExecutorService executorService = Executors.newFixedThreadPool(10); // увеличен пул

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server waiting....");
                Socket socket = serverSocket.accept();

                System.out.println("Connection established");
                System.out.println("Client host: " + socket.getInetAddress() + ":" + socket.getPort());

                // Receiver listens to the client
                ChatServerReceiver receiver = new ChatServerReceiver(socket, messageBox);
                executorService.execute(receiver);

                // Sender sends messages to this client
                sender.addClient(socket);

                // executorService.execute(new ClientHandler(socket)); - old school
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println("Server finished");
        }
    }
}
