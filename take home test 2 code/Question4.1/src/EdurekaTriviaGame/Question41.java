//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package EdurekaTriviaGame;
    import java.io.IOException;
public class Question41 { //main class of question 4 that will run the server code
    
    public static void main(String[] args)throws IOException{
        try{//try catch to ensure the server runs
            //implementing the server class on specific port 8000 and sending the max and min length of the random math
            Server server = new Server(8000,1,10);
            boolean contServer;
            do{ //while the server is active run continueServer() method
                contServer = server.continueServer();
            }while(contServer);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }  
}
