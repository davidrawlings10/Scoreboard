package scoreboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    public static String getJsonList(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        String standingJSON = json.substring(1, json.length() -1);
        return "{\"list\":[" + standingJSON + "]}";
    }

    public static String getJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json =  mapper.writeValueAsString(object);
        return json.substring(1, json.length() -1);
    }
}
