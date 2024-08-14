import java.io.DataOutputStream;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TCPServer {
    public static void main(String[] args) throws Exception{
      
            Socket s= new Socket("127.0.0.1",6900);
            System.out.println("Client connected: "+ s.getInetAddress().getHostAddress());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            LocalDate datee = LocalDate.now();
            LocalTime timee = LocalTime.now();
            String str= datee+" "+timee;
         
            out.writeUTF(str);
      
            s.close();

    }
    
}
