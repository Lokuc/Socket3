import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendFile extends Thread  {

    private Socket socket;
    private int port;
    private PrintWriter outMes;

    public SendFile(int port){
        this.port=port;
        this.start();
    }



    @Override
    public void run() {
        try{
            int tmp;
            socket = new Socket("192.168.0.103",port);
            outMes = new PrintWriter(socket.getOutputStream());
            InputStreamReader isr = new InputStreamReader(new FileInputStream("res/out/test.txt"));
            sleep(3000);
            do{
                tmp=isr.read();
                System.out.println(tmp);
                tmp = (tmp == -1) ? -2 : tmp;
                outMes.println(tmp);
                outMes.flush();
                sleep(100);
            }while(tmp!=-2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
