
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Scanner;

public class GetFile extends Thread  {

    private Socket socket;
    private int port;
    private Scanner inMessage;
    OutputStreamWriter osw;

    public GetFile(int port){
        this.port=54322;
        this.start();
    }



    @Override
    public void run() {
        int tmp;
        try{
            socket = new Socket("192.168.0.103",port);
            osw = new OutputStreamWriter(new FileOutputStream("res/in/r.zip"), Charset.forName("UTF-8").newEncoder());
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
                        osw.write(tmp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tmp);
                }

            }
        try {
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("end");
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
