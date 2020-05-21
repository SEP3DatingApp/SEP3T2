import Shared.JsonConverter;
import Shared.Request;
import Shared.RequestTypes;
import Shared.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        Socket s;

        DataInputStream inputStream;
        DataOutputStream dout;
        Request request = new Request(RequestTypes.LOGIN.toString(), new User("client2", "qwerty", "chan@bybs.com", 'm', 'f', "ara", "kakutis", 23, "garis"));

        try
        {
            s = new Socket("localhost", 5000);
            System.out.println("REQUEST client =" + request);
            String json = JsonConverter.convertObjectToJSONString(request);
            System.out.println(json);

//            inputStream = new DataInputStream(s.getInputStream());

//            dout = new DataOutputStream(s.getOutputStream());
//            dout.writeBytes(json);
            //
//            System.out.println(new String(inputStream.readAllBytes()));

//            dout.flush();
//            dout.close();
            //            inputStream.close();
            s.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
