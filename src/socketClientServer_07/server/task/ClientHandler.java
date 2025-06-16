package socketClientServer_07.server.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String message;
            while ((message = socketReader.readLine()) != null) {
                System.out.println("Server received: " + message);
                socketWriter.println(message + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress() + ":" + socket.getPort());
        }
    }
}
