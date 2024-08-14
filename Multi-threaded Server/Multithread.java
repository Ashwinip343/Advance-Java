package practical_4;
import java.io.*;
import java.net.*;
public class Multithread {

    private static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                System.out.println("Server started...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Start a new thread to handle client request
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clientHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    // Reverse the received string
                    String reversedString = reverseString(clientMessage);

                    // Send the reversed string back to the client
                    out.println(reversedString);
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String reverseString(String str) {
            StringBuilder reversedString = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                reversedString.append(str.charAt(i));
            }
            return reversedString.toString();
        }
    }
}


