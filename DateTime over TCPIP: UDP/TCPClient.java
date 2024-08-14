import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPClient {
    public static void main(String[] args)throws Exception  {

            ServerSocket ss= new ServerSocket(6900);
            Socket socket = ss.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            String data=in.readUTF();

            System.out.println("Date & time recieved :  "+data);
            ss.close();

    }
}
