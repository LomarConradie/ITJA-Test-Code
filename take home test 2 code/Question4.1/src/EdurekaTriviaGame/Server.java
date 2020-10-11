//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package EdurekaTriviaGame;
    import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.net.Socket;
    import java.util.Random;
    import java.io.IOException;
    import java.net.ServerSocket;
//declaring the socket, serversocket, dataoutput and input streams and additional variables
public class Server {
    private ServerSocket servSocket;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private final char operators[];
    private final int Upper;
    private final int Lower;
    //use of constructor to instantiate the variables
    public Server(int Port, int Lower, int Upper)throws IOException{
        this.operators = new char[]{'+','-','*','/'};
        this.Lower = Lower;
        this.Upper = Upper;
        try{//starting the server and declaring the variables
            this.servSocket = new ServerSocket(8000);
            System.out.println("The Server is starting...");
            this.socket = servSocket.accept();
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos.writeUTF("Welcome to Edureka Trivia Game");
        }catch(IOException e){
            throw e;
        }
    }
    //methid used to calculate the math equation used
    public int CalculateMath(int num1, int num2, int index){
        switch(index){
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                return Math.round(num1/num2);
            default:
                return -1;
        }
    }
    //outputs the server math question
    public void Question(int num1, int num2, int index)throws IOException{
        String question = "Question from the Server: What is "+num1+" "+this.operators[index]+" "+num2+" ?";
        try{
            this.dos.writeUTF(question);
        }catch(IOException e){
            throw e;
        }
    }
    //determening the answer from the client
    public String Answer(){
        try{
            String UTF = this.dis.readUTF();
            System.out.println("Answer from Client: " + UTF);
            return UTF;
        }catch(IOException e){
            return "Incorrect answer";
        }
    }
    //declaring the stop procedure the client will use to end the server running
    public boolean UserAnswer(String UInput){
        if(UInput.compareToIgnoreCase("Stop") == 0){
            System.out.println("Client ended the program.");
            return false;
        }
        return true;
    }
    //returning the answer from the math equation
    public void returnAnswer(int Result)throws IOException{
        this.dos.writeUTF(String.valueOf(Result));
    }
    //setting the randomizer and variables that link to the random cenerated operator and number, also returns the final results and completed or not answer
    public boolean continueServer()throws IOException{
        Random rand = new Random();
        final int num1 = rand.nextInt(this.Upper)+this.Lower;
        final int num2 = rand.nextInt(this.Upper)+this.Lower;
        final int index = rand.nextInt(this.operators.length);
        final int result = this.CalculateMath(num1,num2,index);
        try{
            this.Question(num1,num2,index);
        }catch(IOException e){
            e.printStackTrace();
        }
        this.returnAnswer(result);
        String UserResult = this.Answer();
        final boolean complete;
        complete = this.UserAnswer(UserResult);
        return complete;
    }
}
