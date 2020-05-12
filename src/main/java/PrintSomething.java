import SocketServer.StartServer;

public class PrintSomething {
    public static void main(String[] args) {
        StartServer client = new StartServer();
        client.start(5000);
    }
}
