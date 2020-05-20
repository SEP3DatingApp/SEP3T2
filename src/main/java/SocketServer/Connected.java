package SocketServer;

import CRUDForAPI.APICommunication;
import Shared.Request;
import Shared.RequestTypes;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connected implements Runnable
{

    private Socket clientsocket;


    public Connected(Socket socket)
    {
        clientsocket = socket;
    }

    public void InitializeConnection()
    {


        try
        {
            System.out.println("connection initialized");
            String json = "";

            InputStream inputStream = clientsocket.getInputStream();
            OutputStream outputStream = clientsocket.getOutputStream();

            int count;
            byte[] bytes = new byte[100] ;
            while (true)
            {
                System.out.println("loop");
                while ((count = inputStream.read(bytes)) != 0)
                {
                    json+= new String(bytes,0,count);
                    System.out.println(json);//testing if we receive any data at all
                    if(json.contains(";"))
                    {
                        json.replace(";","");
                        break;
                    }
                }
//                json = new String(inputStream.readAllBytes());
                System.out.println("after loopsaf");
                JSONObject jsonObject = new JSONObject(json);
                Request rq = new Request(jsonObject.getString("Type"), jsonObject.getJSONObject("Args"));//getting request type and arguments for it



                /**CREATEACCOUNT*/
                if (rq.getType().equals(RequestTypes.CREATEUSER.toString()))
                {
                    System.out.println("createacc");
                    CreateUser(rq.getArgs());

                }
                /**LOGIN*/
                else if (rq.getType().equals(RequestTypes.LOGIN.toString()))
                {
                    JSONObject arguments = jsonObject.getJSONObject("Args");
                    //                                    System.out.println("SHOULD BE USERNAME HERE "+arguments.getString("Username") + "pass " + arguments.getString("Password"));
                    JSONObject responseFromAPILogin = LoginUser(arguments.getString("Username"), arguments.getString("Password"));
                    byte[] JSONBytes = responseFromAPILogin.toString().getBytes();
                    System.out.println("RESPONSE SENT TO CLIENT " + new String(JSONBytes));
                    outputStream.write(JSONBytes);

                }
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

    public synchronized void CreateUser(JSONObject usr)
    {

        //prints response info 200 if successful
        APICommunication.registerAccount(usr);
    }

    public synchronized JSONObject LoginUser(String username, String password)
    {
        //return
        //{"role":"Fisher","userID":9,"username":"client2","token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjkiLCJyb2xlIjoiRmlzaGVyIiwibmJmIjoxNTg5OTgwMDE2LCJleHAiOjE1ODk5ODM2MTYsImlhdCI6MTU4OTk4MDAxNn0.VsuyRw3i7mGd1-pr5uiOcYX9blJVh2hTcA-h9jtswmI"}
        return APICommunication.login(username, password);
    }
    @Override
    public void run()
    {
        InitializeConnection();
    }
}
