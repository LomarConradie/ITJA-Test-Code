//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package CheckersStockTakingApp;
    import java.io.*;
    import java.net.Socket;
    import java.util.Scanner;
public class Question5Client {
//client main class to run the client applciation
    public static void main(String[] args) {
        try{//using a try catch to ensure the code runs and the connection is made
            Socket socket = new Socket("localhost",8000);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DIS = new DataInputStream(socket.getInputStream());
            System.out.println(DIS.readUTF());
            //string rp to check the reply 
            String rp = "";
            while(true){//while the serve ris still runnning
                Scanner Scan = new Scanner(System.in);
                System.out.println("Enter Product Name:");
                rp = Scan.nextLine();
                DOS.writeUTF(rp);
                CheckedReply(rp);
                System.out.println("Enter Product Type:");
                rp = Scan.nextLine();
                DOS.writeUTF(rp);
                CheckedReply(rp);
                System.out.println("Enter Product Price:");
                rp = Scan.nextLine();
                DOS.writeUTF(rp);
                CheckedReply(rp);
                System.out.println(DIS.readUTF());
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    //checks the reply to ensure the server closes on stop input
    public static void CheckedReply(String rp){
        if(rp.compareToIgnoreCase("stop") == 0){
            System.out.println("Exiting Checkers Stock Taking App, Good Bye!");
        }
    }
}
