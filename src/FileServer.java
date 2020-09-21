import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileServer extends Thread{


    static final int PORT = 54322;

    public FileServer() {
        start();
        System.out.println("fg");
    }



    public void run(){
        // серверный сокет
        Socket fileOut = null;
        Socket fileIn;
        ServerSocket serverSocket = null;
        // исходящее сообщение
        PrintWriter outMessage = null;
        String tmp;
        // входящее собщение
        boolean creat=false;
        Scanner inMessage = null;
        try {
            // создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
            // запускаем бесконечный цикл
            while (true) {
                // таким образом ждём подключений от сервера
                // каждое подключение клиента обрабатываем в новом потоке
                if(fileOut==null){
                    fileOut = serverSocket.accept();
                    inMessage=new Scanner(fileOut.getInputStream());
                }else {
                    fileIn = serverSocket.accept();
                    outMessage = new PrintWriter(fileIn.getOutputStream());
                    creat=true;
                    break;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        while (true){
            if(creat){
                if(inMessage.hasNext()){
                    tmp=inMessage.next();
                    outMessage.println(tmp);
                    outMessage.flush();
                }
            }
        }
    }

}
