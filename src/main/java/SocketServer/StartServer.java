package SocketServer;

import ObjectsFromAPI.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class StartServer {
    private ServerSocket serverSocket;
    private SocketAddress socketAddress;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        //GetSongListFromAPI();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            while(true)
            {
                clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                Thread th = new Thread(new Connected(clientSocket));
                th.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void GetSongListFromAPI()
    {
        String output = "abc";
        try {
            URL url = new URL("https://localhost:44340/Songs");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            String jsonString = "";
            while ((output = br.readLine()) != null) {
                jsonString += output;
                System.out.println(jsonString);
            }
            List<Song> songs = new ArrayList<>();
            //JSON parser object to parse read file
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonSong = jsonArray.getJSONObject(i);
                int songID = jsonSong.getInt("songID");
                int voteamount = jsonSong.getInt("voteAmount");
                int categoryID = jsonSong.getInt("categoryID");
                String title = jsonSong.getString("title");
                String artist = jsonSong.getString("artist");
                Song song = new Song();
                song.setSongID(songID);
                song.setVoteAmount(voteamount);
                song.setCategoryID(categoryID);
                song.setTitle(title);
                song.setArtist(artist);
                songs.add(song);
            }

            System.out.println(songs.get(0).artist + "\n"
            + songs.get(0).title + "\n");

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
