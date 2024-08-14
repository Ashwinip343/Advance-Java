package practical_5;
import java.io.*;
import java.net.*;
public class Fileclient {
    
        public static void main(String[] args) {
            try {
                Socket socket = new Socket("localhost", 9000);
                System.out.println("Connected to server...");
    
                // Get the input stream of the socket
                InputStream inputStream = socket.getInputStream();
    
                // Create file output stream to save the received file
                FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
    
                // Receive the file from the server
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    bufferedOutputStream.write(buffer, 0, bytesRead);
                }
    
                // Close streams and socket
                bufferedOutputStream.close();
                fileOutputStream.close();
                inputStream.close();
                socket.close();
    
                System.out.println("File received from server and saved as received_file.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

