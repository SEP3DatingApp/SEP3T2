import SocketServer.StartServer;

public class ServerMain
{
    public static void main(String[] args) {
        StartServer server = new StartServer();
        server.start(5000);
    }
}
