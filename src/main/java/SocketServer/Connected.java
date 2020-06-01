package SocketServer;

import APICommunication.APICommunication;
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
            byte[] bytes = new byte[100];
            String token = null;
            while (true)
            {
                System.out.println("loop");
                while ((count = inputStream.read(bytes)) != 0)
                {
                    json += new String(bytes, 0, count);
                    //                    System.out.println(json);//testing if we receive any data at all
                    if (json.contains(";"))
                    {
                        json.replace(";", "");
                        break;
                    }
                }
                JSONObject jsonObject = new JSONObject(json);
                Request rq = new Request(jsonObject.getString("Type"), jsonObject.getJSONObject("Args"));//getting request type and arguments for it
                System.out.println(rq);

                /**CREATEACCOUNT*/
                if (rq.getType().equals(RequestTypes.CREATEUSER.toString()))
                {
                    System.out.println("createacc");
                    JSONObject responseCode = APICommunication.createAccount(rq.getArgs());
                    outputStream.write(responseCode.toString().getBytes());
                    json = "";
                }
                /**LOGIN*/
                else if (rq.getType().equals(RequestTypes.LOGIN.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    JSONObject responseFromAPILogin = APICommunication.login(arguments.getString("Username"), arguments.getString("Password"));
                    token = responseFromAPILogin.getString("token");
                    System.out.println("client token" + token);
                    outputStream.write(responseFromAPILogin.toString().getBytes());
                    json = "";

                }

                /**GETFISHER*/
                else if (rq.getType().equals(RequestTypes.GETFISHER.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    int id = arguments.getInt("id");
                    JSONObject responseFromAPI = APICommunication.getFisher(id, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";

                }
                /**EDITFISHERSDATA*/
                else if (rq.getType().equals(RequestTypes.EDITFISHER.toString()))
                {
                    System.out.println("EDITFISHER");
                    JSONObject arguments = rq.getArgs();
                    System.out.println("args froom ediitfisher +" + arguments);
                    int id = arguments.getInt("id");
                    JSONObject responseFromAPI = APICommunication.editFisher(id, arguments, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";

                }
                /**GETFISHESRBYPREFERENCE*/
                else if (rq.getType().equals(RequestTypes.GETFISHERSBYPREFERENCE.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    System.out.println("args froom GETFISHERSBYPREFERENCE +" + arguments);
                    int id = arguments.getInt("id");
                    JSONObject responseFromAPI =  APICommunication.getAllFishersAccordingToTheirPref(id, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";
                }
                /**CLOSES THE CONNECTIONS AND EXITS THE THREAD*/
                else if (rq.getType().equals(RequestTypes.LOGOUT.toString()))
                {
                    System.out.println("LOGOUT");
                    clientsocket.close();
                    outputStream.close();
                    inputStream.close();
                    break;
                }
                //todo like,reject to send only id which you like

            }
            System.out.println("Client have disconected");
            //TODO : else statements
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run()
    {
        InitializeConnection();
    }
}
