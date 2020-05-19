import Shared.JsonConverter;
import Shared.Request;
import Shared.RequestTypes;
import Shared.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        Socket s;
        DataOutputStream dout;
        Request request = new Request(RequestTypes.CREATEUSER.toString(), new User("client2","qwerty","chan@bybs.com",'m','f',"ara","kakutis",23,"garis"));

        try
        {
            System.out.println("REQUEST client =" + request);
            String json = JsonConverter.convertObjectToJSONString(request);


            s = new Socket("localhost", 5000);
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeBytes(json);
            dout.flush();
            dout.close();
            s.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
