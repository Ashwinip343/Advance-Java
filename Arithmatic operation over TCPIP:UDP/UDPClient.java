import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
public class UDPClient {
    public static void main(String[] args) throws Exception {
    
        DatagramSocket ds = new DatagramSocket();

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expression : " );
        String exp = sc.nextLine();
        sendData= exp.getBytes();
        DatagramPacket dp = new DatagramPacket(sendData, sendData.length,InetAddress.getByName("localhost"),8000);
        ds.send(dp);

     
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        ds.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Result recieved from server :  " + receivedMessage);
        
        sc.close();
        ds.close();

    }
}
