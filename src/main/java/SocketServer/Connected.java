package SocketServer;

import CRUDForAPI.CRUDMethods;
import ObjectsFromAPI.User;
import Shared.Request;
import Shared.RequestTypes;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class Connected implements Runnable{

    private Socket clientsocket;
    private Object RequestTypes;

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

//
//            while (in.read(bytes) != 0)
//            {
                json = new String(in.readAllBytes());


//                if (json.contains(";"))
//                {
//                    newjson = json.replace(";" , "");
//                    break;
//                }
//            }

            JSONObject jsonObject = new JSONObject(json);
            Request rq = new Request( jsonObject.getString("Type"), jsonObject.getJSONObject("Args")); //TODO: should work maybe??
            if ( rq.getType().equals(Shared.RequestTypes.CREATEUSER.toString()))
            {
                CreateUser(rq.getArgs());

            }
            //TODO : else statements
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            clientsocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void CreateUser(JSONObject usr)
    {

        //prints response info 200 if successful
        System.out.println(CRUDMethods.create(usr)); ;// TODO create user in api using post

    }

    @Override
    public void run() {
        InitializeConnection();
    }
}
