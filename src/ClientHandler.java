import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {


    // экземпляр нашего сервера
    private Server server;
    // исходящее сообщение
    private PrintWriter outMessage;
    // входящее собщение
    private Scanner inMessage;
    private static final String HOST = "192.168.0.103";
    private static final int PORT = 54321;
    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиента в чате, статичное поле
    public static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            System.out.println("+1");
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // сервер отправляет сообщение
                if(clients_count==1){
                    server.sendMessageToAllClients("SetName:admin");
                }else{
                    server.sendMessageToAllClients("SetName:Client"+(clients_count-1));
                }
                server.sendMessageToAllClients("Новый участник вошёл в чат!");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }
            SendFile sf  = new SendFile(54322);
            sendMsg("System sendFile givePortToYou 54322");

            while (true) {
                // Если от клиента пришло сообщение
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    // если клиент отправляет данное сообщение, то цикл прерывается и
                    // клиент выходит из чата
                    if (clientMessage.equalsIgnoreCase("##session##end##")) { //TODO:
                        break;
                    }
                    System.out.println(clientMessage);

                }
                // останавливаем выполнение потока на 100 мс
                //Thread.sleep(5);
            }
        }
        //catch (InterruptedException ex) {
        //    ex.printStackTrace();
        //}
        finally {
            this.close();
        }
    }
    // отправляем сообщение
    public void sendMsg(int msg){
        sendMsg(msg+"");
    }
    public void sendMsg(String msg) {
        try {
            System.out.println(msg);
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // клиент выходит из чата
    public void close() {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }
}
