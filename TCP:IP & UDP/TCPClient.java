
import java.io.*;
import java.net.*;
import java.util.*;
public class TCPClient {
    // A Java program for a serverSide
    public static void main(String[] args) {
        try {
            Socket s= new Socket("127.0.0.1",6900);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.print("Client: ");
            String input =sc.nextLine();
            out.writeUTF(input);
          
            ServerSocket ss= new ServerSocket(9000);

            Socket socket = ss.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String data="";
           
            while(!input.equals("bye")){
                data=in.readUTF();
                System.out.println("Server:"+data);
                if(data.equals("bye")){
                    out.writeUTF("Byee server...");

                break;}
                System.out.print("Client: ");
                input=sc.nextLine();
                out.writeUTF(input);
            }

            System.out.println(in.readUTF());
            System.out.println("connection close here too.....");
            s.close();
            sc.close();
            ss.close();

        }
        catch(Exception e ){e.printStackTrace();
        }
    }
}
           
    
