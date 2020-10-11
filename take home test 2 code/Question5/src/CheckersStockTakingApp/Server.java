//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package CheckersStockTakingApp;
    import java.io.*;
    import java.sql.*;
    import java.net.*;

public class Server {//declaring all variables and methods used
    private ServerSocket servSocket;
    private Socket socket;
    private Connection con;
    private DataOutputStream dos;
    private DataInputStream dis;
    //main server constructor the instantiate these methods and variables
    public Server(int Port, String URL, String user, String pass)throws IOException{
        try{
            this.servSocket = new ServerSocket(Port);
            try{
                this.con = DriverManager.getConnection(URL,user,pass);
                if(this.con == null){
                    System.err.println("Could not connect to database.");
                    System.exit(0);
                }
            }catch(SQLException s){
                System.err.println(s.getMessage());
            }
            System.out.println("Server is starting...");
            this.socket = servSocket.accept();
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos.writeUTF("Welcome to Checkers Stock Taking App");
        }catch(IOException e){
            throw e;
        }
    }
    //method to run the SQL insert statement and ensure the data has been added to database
    public void ProductInsert(String PName, String PType, double PPrice){
        try{
            PreparedStatement PStat = con.prepareStatement("INSERT INTO `checkersproducts`.`products`(`prodName`,`prodType`,`prodPrice`)"+"VALUE(?,?,?);");
            PStat.setString(1, PName);
            PStat.setString(2, PType);
            PStat.setDouble(3, PPrice);
            PStat.execute();
            try{
                this.dos.writeUTF("Server says: Product has been added");
            }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }catch(SQLException s){
            System.err.println(s.getMessage());
        }
    }
    //This checks the users input and ensures that the server receives them
    public String UserInput(){
        String result="";
        try{
            result = dis.readUTF();
            System.out.println("Server received Product Details: " + result);
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
    //check if client has stopped the server
    public boolean Completed(String CInput){
        if(CInput.compareToIgnoreCase("stop")==0){
            System.out.println("Server stopping...");
            return true;
        }
        return false;
    }
    //ensuring the input data is correct and sent to the server
    public boolean addProduct(){
        String PName, PType, inp;
        double PPrice;
        inp = UserInput();
        if(Completed(inp)){
            return false;
        }else{
            PName = inp;
        }
        inp = UserInput();
        if(Completed(inp)){
            return false;
        }else{
            PType = inp;
        }
        inp = UserInput();
        if(Completed(inp)){
            return false;
        }else{
            PPrice = Double.parseDouble(inp);
        }
        ProductInsert(PName,PType,PPrice);
        return true;
    }
}
