package Shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonConverter
{

        static ObjectMapper mapper = new ObjectMapper();
        static String  json = null;

        public static String convertObjectToJSONString(Object object)
        {
            Request request = (Request) object;
            System.out.println(request);
//            if (object instanceof User)
//            {
                try
                {
                    json = mapper.writeValueAsString(object);
                } catch (JsonProcessingException e)
                {
                    e.printStackTrace();
                }
                System.out.println("ResultingJSONstring = " + json);
                //System.out.println(json);

//            }
            return json;
        }


}
 