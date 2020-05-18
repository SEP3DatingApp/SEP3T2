import SocketServer.StartServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerMain
{
    public static void main(String[] args) {
        StartServer server = new StartServer();
        server.start(5000);
    }
}
