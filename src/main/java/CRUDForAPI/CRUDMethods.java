package CRUDForAPI;

import ObjectsFromAPI.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpResponse;

public final class CRUDMethods
{
    public static int create(JSONObject usr)
    {


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://localhost:44380/users/register");

        String json = usr.toString();
        System.out.println("JSON" + json);
        StringEntity entity = null;
        try
        {
            entity = new StringEntity(json);

        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = null;
        try
        {
            response = client.execute(httpPost);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            client.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }


}
