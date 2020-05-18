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
        Socket s = null;
        DataOutputStream dout = null;
        Request request = new Request(RequestTypes.CREATEUSER.toString(), new User(1, "Fisher", "Bybis", "bybinini", "afs@fff.com", "male", "males", "1234", 24, true, "nesomani", "nzn krc", true));

        try
        {
            String json = JsonConverter.convertObjectToJSONString(request);

            s = new Socket("localhost", 5000);
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(json);
            dout.flush();
            dout.close();
            s.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
