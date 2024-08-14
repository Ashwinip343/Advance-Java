package practical_5;
import java.io.*;
import java.net.*;
public class Fileserver {

    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(9000)) {
                System.out.println("File Server started...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
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
             
                File file = new File("sample.txt");
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                OutputStream outputStream = clientSocket.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

             
                bufferedInputStream.close();
                fileInputStream.close();
                outputStream.close();
                clientSocket.close();
                System.out.println("File sent to client.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


