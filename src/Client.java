
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    // адрес сервера
    private static final String SERVER_HOST = "192.168.0.103";
    // порт
    private static final int SERVER_PORT = 54321;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;
    // имя клиента
    private String clientName = "";
    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }
    private Scanner sc;


    public Client() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc = new Scanner(System.in);
        // в отдельном потоке начинаем работу с сервером
        System.out.println("go");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        if(sc.hasNext()){
                            if(sc.next().equals("g")){
                                //sendFile();
                            }
                        }
                        // если есть входящее сообщение
                        //if (inMessage.hasNext()) {
                        //    // считываем его
                        //    String inMes = inMessage.nextLine();
                        //    String clientsInChat = "Клиентов в чате = ";
                                // выводим сообщение
                        //}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            public void sendMsg(String msg) {
                // отправляем сообщение
                System.out.println("send:"+msg);
                outMessage.println(msg);
                outMessage.flush();
            }
        }).start();

    }

    private void close(){
        try {
            // отправляем служебное сообщение, которое является признаком того, что клиент вышел из чата
            outMessage.println("##session##end##");
            outMessage.flush();
            outMessage.close();
            inMessage.close();
            clientSocket.close();
        } catch (IOException ignored) {

        }
    }

    // отправка сообщения
    public void sendMsg(String msg) {
        // отправляем сообщение
        System.out.println("send:"+msg);
        outMessage.println(msg);
        outMessage.flush();
       }

}
