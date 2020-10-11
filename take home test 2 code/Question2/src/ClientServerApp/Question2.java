//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package ClientServerApp;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.net.ServerSocket;
    import java.net.Socket;
public class Question2 {
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9000;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until program terminates
        while(true){
            System.out.println("Server is starting...");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message received from client : " + message);
            //close resources
            ois.close();
            socket.close();
            break;
        }
    }  
} 