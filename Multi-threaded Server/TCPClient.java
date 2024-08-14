package practical_4;
import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8000);
            System.out.println("Connected to server...");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter a string: ");
            String message = userInput.readLine();

            out.println(message);

            // Receive the reversed string from the server
            String reversedString = in.readLine();
            System.out.println("Reversed string from server: " + reversedString);
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


