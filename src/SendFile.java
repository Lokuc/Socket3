import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendFile extends Thread  {

    private Socket socket;
    private int port;
    private Scanner inMes;
    private PrintWriter outMes;

    public SendFile(int port){
        this.port=port;
    }



    @Override
    public void run() {
        try{
            socket = new Socket("192.168.0.103",port);
            inMes = new Scanner(socket.getInputStream());
            outMes = new PrintWriter(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        while(true){

        }
    }
}
