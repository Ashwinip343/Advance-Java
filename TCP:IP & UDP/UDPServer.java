import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer {
    public static void main(String[] args) {
        try {
            try (
            DatagramSocket serverSocket = new DatagramSocket(9876)) {
              
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                String receivedMessage="";

                try (Scanner sc = new Scanner(System.in)) {
                    while (!receivedMessage.equals("bye")) {
                     
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        serverSocket.receive(receivePacket);
                        receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Client: " + receivedMessage);

              
                        InetAddress clientAddress = receivePacket.getAddress();
                        int clientPort = receivePacket.getPort();
                        System.out.print("Server: ");
                        String message = sc.nextLine();
                        sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                        serverSocket.send(sendPacket);
    
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
