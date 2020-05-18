package CRUDForAPI;

import ObjectsFromAPI.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonConverter
{

        static ObjectMapper mapper = new ObjectMapper();
        static String json;

        public static String convertObjectToJSONString(Object object)
        {
            System.out.println(object+"CONVERTOBJHECT OT");
            try
            {
                json = mapper.writeValueAsString(object);
                System.out.println(json + "CONVERT OBJECT");
            } catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
            System.out.println("ResultingJSONstring = " + json);
            return json;
        }


}
 