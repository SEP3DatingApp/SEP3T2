package SocketServer;

import APICommunication.APICommunication;
import Shared.Request;
import Shared.RequestTypes;
import org.json.JSONArray;
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
            String token = "";
            while (true)
            {
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

                /**CREATEACCOUNT*/
                if (rq.getType().equals(RequestTypes.CREATEUSER.toString()))
                {
                    JSONObject responseCode = APICommunication.createAccount(rq.getArgs());
                    outputStream.write(responseCode.toString().getBytes());
                    json = "";
                }
                /**LOGIN*/
                else if (rq.getType().equals(RequestTypes.LOGIN.toString()))
                {

                    JSONObject arguments = rq.getArgs();
                    JSONObject responseFromAPILogin = APICommunication.login(arguments.getString("Username"), arguments.getString("Password"));
                    if (!(responseFromAPILogin.toString().equals("{\"message\":\"Username or password is incorrect\"}")))
                    {
                        System.out.println(responseFromAPILogin.toString());
                        token = responseFromAPILogin.getString("Token");
                        outputStream.write(responseFromAPILogin.toString().getBytes());
                        json = "";
                    } else
                    {
                        outputStream.write(responseFromAPILogin.toString().getBytes());
                        json = "";
                    }

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
                    JSONObject arguments = rq.getArgs();
                    int id = arguments.getInt("id");
                    JSONObject responseFromAPI = APICommunication.editFisher(id, arguments, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";

                }
                /**GETFISHESRBYPREFERENCE*/
                else if (rq.getType().equals(RequestTypes.MATCHLIST.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    int id = arguments.getInt("id");
                    JSONArray responseFromAPI = APICommunication.getAllFishersAccordingToTheirPref(id, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";
                }
                /**LIKE*/

                else if (rq.getType().equals(RequestTypes.LIKE.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    int id = arguments.getInt("OtherId");
                    JSONObject responseFromAPI = APICommunication.like(id, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";
                } else if (rq.getType().equals(RequestTypes.REJECT.toString()))
                {
                    JSONObject arguments = rq.getArgs();
                    int id = arguments.getInt("OtherId");
                    JSONObject responseFromAPI = APICommunication.reject(id, token);
                    outputStream.write(responseFromAPI.toString().getBytes());
                    json = "";
                }
                /**CLOSES THE CONNECTIONS AND EXITS THE THREAD*/
                else if (rq.getType().equals(RequestTypes.LOGOUT.toString()))
                {
                    System.out.println("LOGOUT");
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
