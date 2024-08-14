import java.io.*;
import java.net.*;
import java.util.*;
public class UDPClient {
    public static void main(String[] args) {
        try {
            try (
            DatagramSocket ds = new DatagramSocket()) {

                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

               
                InetAddress serverAddress = InetAddress.getByName("localhost");
                int serverPort = 9876;
                String receivedMessage="";
                try (Scanner sc = new Scanner(System.in)) {
                    while (!receivedMessage.equals("bye")) {
                        
                        System.out.print("Client: ");
                        String message= sc.nextLine();
                        sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                        ds.send(sendPacket);

                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        ds.receive(receivePacket);
                        receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Server: " + receivedMessage);
                      
                    }
                }
            }  
           

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
