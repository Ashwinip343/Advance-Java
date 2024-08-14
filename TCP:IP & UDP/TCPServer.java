// A Java program for a serverSide
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss= new ServerSocket(6900);
            Socket socket = ss.accept();
            // takes input from the client socket
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String data=in.readUTF();
            System.out.println("Client: "+data);
            // String data="";
            
            Socket s =  new Socket("127.0.0.1",9000);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

            String input = "";
            while(!input.equals("bye")){
                System.out.print("Server: ");
                input=sc.nextLine();
                out.writeUTF(input);
                data=in.readUTF();
                if(data.equals("bye")){
                    out.writeUTF("Byee client...");
                break;}

                System.out.println("Client:"+data);

            }
            System.out.println("connection closed!");
            s.close();
            sc.close();
            ss.close();
        }
        catch(Exception e ){e.printStackTrace();
        }
    }
}
           

	