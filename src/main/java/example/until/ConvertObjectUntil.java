package example.until;

import com.google.gson.Gson;

public class ConvertObjectUntil {
    public static String convertDtoToJson(Object dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);
    }
}
