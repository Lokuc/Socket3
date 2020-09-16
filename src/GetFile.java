
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GetFile extends Thread  {

    private Socket socket;
    private int port;
    private Scanner inMessage;
    OutputStreamWriter osw;

    public GetFile(int port){
        this.port=port;
        this.start();
    }



    @Override
    public void run() {
        int tmp;
        try{
            socket = new Socket("192.168.0.103",port);
            osw = new OutputStreamWriter(new FileOutputStream("res/in/testy.txt"));
            inMessage = new Scanner(socket.getInputStream());


        }catch (Exception e){
            e.printStackTrace();
        }

            while (true) {
                System.out.println("waits");
                System.out.println(inMessage.nextLine());
                tmp=inMessage.nextInt();
                System.out.println(tmp+"  htrr");
                try {
                    osw.write(tmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(tmp);
                System.out.println("oll");
                if(tmp==-2){
                    break;
                }

            }
        try {
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
