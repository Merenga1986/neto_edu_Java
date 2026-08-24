package java_core.csapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT + ", waiting for connections...");

            // ждём подключения клиента
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("New connection accepted");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                final String name = in.readLine();

                System.out.printf("Received message: \"%s\" from port %d%n", name, clientSocket.getPort());

                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }
        }
    }
}
