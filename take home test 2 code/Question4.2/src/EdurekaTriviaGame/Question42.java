//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package EdurekaTriviaGame;
    import java.io.*;
    import java.net.Socket;
    import java.util.Scanner;
public class Question42 {
    //main method of the client application
    public static void main(String[] args){
        try{//setting the socket and data input and output streams
            Socket socket = new Socket("localhost",8000);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DataInputStream DIS = new DataInputStream(socket.getInputStream());
            System.out.println(DIS.readUTF());
            String rp;
            do{
                System.out.println(DIS.readUTF());
                Scanner scan = new Scanner(System.in);
                rp = scan.next();
                int reply;
                try{
                    reply = Integer.parseInt(DIS.readUTF());
                }catch(Exception s){
                    System.err.println(s.getMessage());
                    break;
                }
                DOS.writeUTF(rp);
                ReplyReview(rp,reply);
            }while(rp.compareToIgnoreCase("stop") == 0);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }      
    }
    //boolean to check if the user has ended the program and if the users input is correct or not
    public static boolean ReplyReview(String rp, int reply){
        try{
            if(Double.parseDouble(rp)==reply){
                System.out.println("Welldone, that's correct!");
            }else{
                System.out.println("Wrong Answer!, the correct answer is: " + reply);
            }
            return true;
        }catch(Exception e){
            if(rp.compareToIgnoreCase("stop") == 0){
                System.out.println("Thank you for playing, Good bye!");
                return false;
            }
            System.out.println(e.getMessage());
            return true;
        }
    }
}
