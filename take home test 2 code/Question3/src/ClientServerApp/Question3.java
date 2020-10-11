//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package ClientServerApp;
    import java.io.IOException;
    import java.io.ObjectOutputStream;
    import java.net.InetAddress;
    import java.net.Socket;
    import java.net.UnknownHostException;
public class Question3 {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
         //get the localhost IP address
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9000);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hello, I study ITJA321 at PIHE!");
            //close resources
            oos.close();
            socket.close();
    }
}
