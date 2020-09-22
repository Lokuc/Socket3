import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
            BufferedInputStream br = new BufferedInputStream(new FileInputStream("res/out/t.jpg"));
            InputStreamReader isr = new InputStreamReader(new FileInputStream("res/out/t.jpg"));
            System.out.println(isr.getEncoding());
            sleep(500);
            do{
                tmp=br.read();
                System.out.println(tmp);
                tmp = (tmp == -1) ? -2 : tmp;
                outMes.println(tmp);
                outMes.flush();
            }while(tmp!=-2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
