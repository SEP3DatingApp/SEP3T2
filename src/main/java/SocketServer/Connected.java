package SocketServer;

import Shared.Request;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class Connected implements Runnable{

    private Socket clientsocket;
    public Connected(Socket socket)
    {
        clientsocket = socket;
    }
    public void InitializeConnection()
    {
        try {
            String json = "";
            String newjson = "";
            InputStream in = clientsocket.getInputStream();
            byte[] bytes = new byte[100];
            int count;
            while ((count = in.read(bytes)) != 0) {
                json += new String(bytes,0,count);
                if (json.contains(";"))
                {
                    newjson = json.replace(";" , "");
                    break;
                }
            }
            JSONObject jsonObject = new JSONObject(newjson);
            Request rq = new Request(jsonObject.getString("Type"),jsonObject.getJSONObject("Arg"));
            if ( rq.getType().equals("CREATEUSER"))
            {
                CreateUser(rq.getArgs());
                System.out.println("IT IS CREATE USER");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            clientsocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateUser(Object usr)
    {
    }

    @Override
    public void run() {
        InitializeConnection();
    }
}
