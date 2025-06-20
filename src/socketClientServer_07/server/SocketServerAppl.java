package socketClientServer_07.server;

import socketClientServer_07.server.task.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServerAppl {
    public static void main(String[] args) throws InterruptedException {
        int port = 9000;
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server waiting....");
                Socket socket = serverSocket.accept();

                System.out.println("Connection established");
                System.out.println("Client host: " + socket.getInetAddress() + ":" + socket.getPort());

                // Передаём всё в ClientHandler, больше сами не трогаем сокет!
                executorService.execute(new ClientHandler(socket));
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
