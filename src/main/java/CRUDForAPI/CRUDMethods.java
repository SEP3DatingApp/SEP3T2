package CRUDForAPI;

import ObjectsFromAPI.User;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpResponse;

public final class CRUDMethods
{
    public static int create(JSONObject usr)
    {
        String postData;
        URL url;
        HttpsURLConnection conn = null;
        try
        { url = new URL("https://localhost:44380/users/register");
            conn = (HttpsURLConnection) url.openConnection();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("CREATECRUD");
        try
        {
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");

            postData = usr.toString();
            System.out.println("bybis+++++++++++++" + postData);


            // For POST only - START
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();

            os.write(postData.getBytes());//postData will be jason file
            os.flush();
            os.close();
            // For POST only - END

            if (conn.getResponseCode() != 200)
            {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            return conn.getResponseCode();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
