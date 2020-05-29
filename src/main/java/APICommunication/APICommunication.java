package APICommunication;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public final class APICommunication
{
    private static CloseableHttpClient client = HttpClients.createDefault();
    private static HttpPost httpPost;

    public static synchronized JSONObject createAccount(JSONObject usr)
    {
        //todo add firstname,gender,sex
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
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        int responseCodeInt = response.getStatusLine().getStatusCode();
        JSONObject responseCode = new JSONObject("{\"ResponseCode\": \"" + responseCodeInt + "\"}");
        System.out.println(responseCode);
        return responseCode;
    }



    public static synchronized JSONObject getFisher(int id, String token)
    {
        System.out.println("GeTUSER ID AND TOK " + id + "    " + token);
        StringBuilder result = new StringBuilder();
        URL url = null;
        try
        {
            url = new URL("https://localhost:44380/users/getuser/" + id);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try
        {
            conn = (HttpURLConnection) url.openConnection();

            try
            {
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("GET");
            } catch (ProtocolException e)
            {
                e.printStackTrace();
            }
            BufferedReader rd;

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (conn.getResponseCode() != 200)
            {
                JSONObject JSONResult = new JSONObject("{\"ResponseCode\": \"" + conn.getResponseCode() + "\"}");
                return  JSONResult;
            }
            else {
                JSONObject JSONResult = new JSONObject(result.toString());
                System.out.println("GET USER = " + JSONResult);
                return JSONResult;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public static synchronized JSONObject login(String username, String password)
    {
        String responseBody = null;
        httpPost = new HttpPost("https://localhost:44380/users/Authenticate");
        String json = "{\"Username\": \"" + username + "\",\"Password\": \"" + password + "\"}";
        System.out.println("+++++++++++" + json);
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


        } catch (IOException e)
        {
            e.getMessage();
        }
        if (responseBody == null)
        {
            return new JSONObject("{\"ResponseCode\": \"Can't connect to T3 \"}");
        }
        else
        {
            return new JSONObject(responseBody);

        }
    }

        public static synchronized JSONObject editFisher(int id,JSONObject data, String token) //pass,sexpref,picref,discription
        {
            URL url = null;
            try
            {
                url = new URL("https://localhost:44380/users/" + id);
                System.out.println("we got the id " + id);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            HttpURLConnection httpCon = null;
            try
            {
                httpCon = (HttpURLConnection) url.openConnection();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            httpCon.setDoOutput(true);
            try
            {
                httpCon.setRequestProperty("Authorization", "Bearer " + token);
                httpCon.setRequestProperty("Content-Type", "application/json");
                httpCon.setRequestMethod("PUT");
            } catch (ProtocolException e)
            {
                e.printStackTrace();
            }
            try
            {
                String password;
                OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
                if (data.get("Password").toString().equals("null"))
                {
                    password = null;
                } else
                {
                    password = "\"" + data.get("Password").toString() + "\"";
                }

                String Jsondata = "{\"Password\": " + password + ",\"SexPref\": \"" + data.getString("SexPref") + "\",\"Email\": \"" + data.getString("Email") +  "\",\"Description\": \"" + data.getString("Description") +  "\",\"IsActive\": " + data.getBoolean("IsActive") + "}";
                System.out.println("JSON DATA PUT ++++"+ Jsondata);


                out.write(Jsondata);
                out.close();
                httpCon.getInputStream();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            JSONObject JSONResult = null;
            try
            {
                JSONResult = new JSONObject("{\"ResponseCode\": \"" + httpCon.getResponseCode() + "\"}");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return  JSONResult;
        }

    public static JSONObject getAllFishersAccordingToTheirPref(int yourid, String token) //todo , int prefValue will be made from sex pref and gender
    {
        /**GETTING DATA ABOUT THE USER BY ID*/
        JSONObject dataAboutUser = getFisher(yourid,token);
        String sexPref = dataAboutUser.getString("sexPref");
        String gender = dataAboutUser.getString("gender");

        /***/


//
//        httpPost = new HttpPost("https://localhost:44380/users/GetFishersPref");
//        String json = usr.toString();
//        StringEntity entity = null;
//        try
//        {
//            entity = new StringEntity(json);
//
//        } catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//        CloseableHttpResponse response = null;
//        try
//        {
//            response = client.execute(httpPost);
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        int responseCodeInt = response.getStatusLine().getStatusCode();
//        JSONObject responseCode = new JSONObject("{\"ResponseCode\": \"" + responseCodeInt + "\"}");
//        System.out.println(responseCode);
//        return responseCode;
        return null;
    }



}
