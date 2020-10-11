//Lomar Conradie | DQ61ZP3G5 | Take Home Test 2
package CheckersStockTakingApp;
    import java.io.IOException;
public class Question5 {

    public static void main(String[] args)throws IOException{
        try{
            Server server = new Server(8000,"jdbc:mysql//localhost:3306/checkersproducts","root","y7DxbFxtYnecfZAg");
            boolean contServer;
            do{
                contServer = server.addProduct();
            }while(contServer);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    } 
}
