import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

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
            InputStreamReader isr = new InputStreamReader(new FileInputStream("res/out/re.zip"),Charset.forName("UTF-8"));
            System.out.println(isr.getEncoding());
            sleep(3000);
            do{
                tmp=isr.read();
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
