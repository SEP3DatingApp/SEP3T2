package Shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonConverter /**Useless class which is used only in client*/
    {

        static ObjectMapper mapper = new ObjectMapper();
        static String  json = null;

        public static String convertObjectToJSONString(Object object)
        {
                try
                {
                    json = mapper.writeValueAsString(object);
                } catch (JsonProcessingException e)
                {
                    e.printStackTrace();
                }
                System.out.println("ResultingJSONstring = " + json);
            return json;
        }


}
 