package socketClientServer_07.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketAppl {
    public static void main(String[] args) {

        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;

        //Socket socket = new Socket(serverHost,port);
        try (Socket socket = new Socket(serverHost,port);) {

            // Байтовые потоки - сейчас планируем текстовый формат
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream= socket.getOutputStream();

            PrintWriter socketWriter = new PrintWriter(outputStream);

            // BufferedReader -потокобезопасный в мультисредовой среде
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));

            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Enter your message, or type exit for quit");

            String message = consoleScanner.nextLine();
            while (!"exit".equalsIgnoreCase(message)){
                socketWriter.println(message);
                String response = socketReader.readLine();
                System.out.println(response);
                System.out.println("Enter your message, or type exit for quit");
                message = consoleScanner.nextLine();


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
