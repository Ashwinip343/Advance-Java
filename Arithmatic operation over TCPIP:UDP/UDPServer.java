import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UDPServer {
   public static void main(String[] args)throws Exception {
    
   
        DatagramSocket serverSocket = new DatagramSocket(8000);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Expression recieved form the Client: " + receivedMessage);
        
        int result = calculate(receivedMessage);

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        sendData = (Integer.toString(result)).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);
        System.out.println("Result sent to client: " +result);
        serverSocket.close();

        }

        public static int calculate(String expression){
             int index=0;
            int result=0;
            int a[]= new int[2];
            Pattern pattern = Pattern.compile("(\\d+(?:.\\d+)?)|([\\+\\-\\*\\/])");
            Matcher matcher = pattern.matcher(expression);
            String operator="";
               
            while (matcher.find()) {
                String group = matcher.group();
                if (group.matches("\\d+(?:.\\d+)?")) {
                    a[index]=Integer.parseInt(group);
                    index++;  
                } else {
                    operator=group;
                }
            }
            switch (operator) {
                case "+": result=a[0]+a[1];                        
                    break;
                case "-": result=a[0]-a[1];
                    break; 
                case "*": result=a[0]*a[1];
                    break;
                case "/": result=a[0]/a[1];
                    break;

                default:
                    break;
                }
            return result;

        }

    
    
}
