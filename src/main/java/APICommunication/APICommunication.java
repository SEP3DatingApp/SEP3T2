package APICommunication;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class APICommunication
{
    private static CloseableHttpClient client = HttpClients.createDefault();
    private static HttpPost httpPost;

    public static int registerAccount(JSONObject usr)
    {
        httpPost = new HttpPost("https://localhost:44380/users/register");
        String json = usr.toString();
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
            //            client.close();//TODO Causes error Connection pool shut down
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine().getStatusCode());
        return response.getStatusLine().getStatusCode();
    }

    //TODO not used right now

    //    public static int getUser(String username, String password)
    //    {
    //        URL urlForGetRequest = null;
    //        try
    //        {
    //            urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/1");
    //        } catch (MalformedURLException e)
    //        {
    //            e.printStackTrace();
    //        }
    //        String readLine = null;
    //        HttpURLConnection conection = null;
    //        try
    //        {
    //            conection = (HttpURLConnection) urlForGetRequest.openConnection();
    //        } catch (IOException e)
    //        {
    //            e.printStackTrace();
    //        }
    //        try
    //        {
    //            conection.setRequestMethod("GET");
    //        } catch (ProtocolException e)
    //        {
    //            e.printStackTrace();
    //        }
    //        conection.setRequestProperty(username, password); // set userId its a sample here
    //        int responseCode = 0;
    //
    //
    //        try
    //        {
    //            if (responseCode == HttpURLConnection.HTTP_OK)
    //            {
    //                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
    //                StringBuffer response = new StringBuffer();
    //                while ((readLine = in.readLine()) != null)
    //                {
    //                    response.append(readLine);
    //                }
    //                in.close();
    //                // print result
    //                System.out.println("JSON String Result " + response.toString());
    //                //GetAndPost.POSTRequest(response.toString());
    //            } else
    //            {
    //                System.out.println("GET NOT WORKED");
    //            }
    //            responseCode = conection.getResponseCode();
    //        } catch (IOException e)
    //        {
    //            e.printStackTrace();
    //        }
    //        //TODO not finished
    //
    //        return responseCode;
    //    }


    public static JSONObject login(String username, String password)
    {
        String responseBody = null;
        httpPost = new HttpPost("https://localhost:44380/users/Authenticate");
        String json = "{\"Username\": \"" + username + "\",\"Password\": \"" + password + "\"}";
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

            responseBody = new String(response.getEntity().getContent().readAllBytes());
            //            client.close();
        } catch (IOException e)
        {
            e.getMessage();
        }

        //        System.out.println(responseBody);
        return new JSONObject(responseBody);
    }
}
