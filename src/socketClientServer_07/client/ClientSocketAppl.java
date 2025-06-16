package socketClientServer_07.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketAppl {
    public static void main(String[] args) {


        if (args.length==1){

            args = new String[]{args[0],"9000"};
        }
        if (args.length==0){

            args = new String[]{"127.0.0.1","9000"};
        }

        String serverHost = args[0];
        int port = Integer.parseInt(args[1]);



        //String serverHost = "127.0.0.1"; // localhost
        //int port = 9000;

        try (Socket socket = new Socket(serverHost, port);
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true); // autoFlush = true
             Scanner consoleScanner = new Scanner(System.in)) {

            System.out.println("Enter your message, or type 'exit' to quit");

            String message = consoleScanner.nextLine();
            while (!"exit".equalsIgnoreCase(message)) {
                socketWriter.println(message); // autoFlush работает
                String response = socketReader.readLine();
                System.out.println("Server response: " + response);
                System.out.println("Enter your message, or type 'exit' to quit");
                message = consoleScanner.nextLine();
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}
