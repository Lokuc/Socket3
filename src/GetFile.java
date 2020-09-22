
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GetFile extends Thread  {

    private Socket socket;
    private int port;
    private Scanner inMessage;
    BufferedOutputStream bos;

    public GetFile(int port){
        this.port=54322;
        this.start();
    }



    @Override
    public void run() {
        int tmp;
        try{
            socket = new Socket("192.168.0.103",port);
            bos = new BufferedOutputStream(new FileOutputStream("res/in/r.jpg"));
            inMessage = new Scanner(socket.getInputStream());


        }catch (Exception e){
            e.printStackTrace();
        }

            while (true) {
                if(inMessage.hasNext()) {
                    tmp = inMessage.nextInt();
                    if (tmp == -2) {
                        break;
                    }
                    try {
                        bos.write(tmp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tmp);
                }

            }
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
