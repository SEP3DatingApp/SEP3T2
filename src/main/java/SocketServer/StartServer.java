package SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
            while (true)
            {
                clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                Thread th = new Thread(new Connected(clientSocket));
                th.start();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    public void stop()
//    {
//        try
//        {
//            in.close();
//            out.close();
//            clientSocket.close();
//            serverSocket.close();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    //    public static void GetFishersFromList()
    //    {
    //        String output = "";
    //        try {
    //            URL url = new URL("https://localhost:44312/api/Fishers");
    //            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    //            conn.setRequestMethod("GET");
    //            conn.setRequestProperty("Accept", "application/json");
    //
    //            if (conn.getResponseCode() != 200) {
    //
    //                throw new RuntimeException("Failed : HTTP error code : "
    //                        + conn.getResponseCode());
    //            }
    //
    //            BufferedReader br = new BufferedReader(new InputStreamReader(
    //                    (conn.getInputStream())));
    //
    //            System.out.println("Output from Server .... \n");
    //            String jsonString = "";
    //            while ((output = br.readLine()) != null) {
    //                jsonString += output;
    //                System.out.println(jsonString);
    //            }
    //            List<User> users = new ArrayList<>();
    //            //JSON parser object to parse read file
    //            JSONArray jsonArray = new JSONArray(jsonString);
    //
    //            for (int i=0; i<jsonArray.length(); i++) {
    //                JSONObject jsonSong = jsonArray.getJSONObject(i);
    //                int UserID = jsonSong.getInt("userId");
    //                String username = jsonSong.getString("username");
    //                String password = jsonSong.getString("password");
    //                String usertype = jsonSong.getString("discriminator");
    //                String email = jsonSong.getString("email");
    //                String gender = jsonSong.getString("gender");
    //                String sexProef = jsonSong.getString("sexPref");
    //                String picRef = jsonSong.getString("picRef");
    //                int age = jsonSong.getInt("age");
    //                boolean isActive = jsonSong.getBoolean("isActive");
    //                String name = jsonSong.getString("name");
    //                String description = jsonSong.getString("description");
    //
    //                User usr = new User();
    //                usr.setUserId(UserID);
    //                usr.setUsername(username);
    //                usr.setPassword(password);
    //                usr.setUserType(usertype);
    //                usr.setEmail(email);
    //                usr.setGender(gender);
    //                usr.setSexPref(sexProef);
    //                usr.setPicRef(picRef);
    //                usr.setAge(age);
    //                usr.setisActive(isActive);
    //                usr.setName(name);
    //                usr.setDescription(description);
    //                users.add(usr);
    //            }
    //
    //            System.out.println("Username: " + users.get(0).getUsername());
    //
    //            conn.disconnect();
    //
    //        } catch (MalformedURLException e) {
    //            e.printStackTrace();
    //
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }

}
