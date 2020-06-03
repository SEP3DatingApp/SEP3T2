package SocketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer
{
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public void start(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            int client = 0;
            while (true)
            {
                clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                Thread th = new Thread(new Connected(clientSocket));
                client++;
                System.out.println("client number" + client);
                th.start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
