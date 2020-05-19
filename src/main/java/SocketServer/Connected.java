package SocketServer;

import CRUDForAPI.CRUDMethods;
import Shared.Request;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Connected implements Runnable
{

    private Socket clientsocket;
    private Object RequestTypes;

    public Connected(Socket socket)
    {
        clientsocket = socket;
    }

    public void InitializeConnection()
    {
        try
        {
            String json;
            InputStream in = clientsocket.getInputStream();
            json = new String(in.readAllBytes());

            JSONObject jsonObject = new JSONObject(json);
            Request rq = new Request(jsonObject.getString("Type"), jsonObject.getJSONObject("Args"));

            if (rq.getType().equals(Shared.RequestTypes.CREATEUSER.toString()))
            {
                CreateUser(rq.getArgs());

            }
            //TODO : else statements
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            clientsocket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void CreateUser(JSONObject usr)
    {

        //prints response info 200 if successful
        System.out.println(CRUDMethods.create(usr));


    }


    @Override
    public void run()
    {
        InitializeConnection();
    }
}
