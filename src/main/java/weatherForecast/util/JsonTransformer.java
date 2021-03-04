package weatherForecast.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Author : Paritosh
 * Date : 04/03/21
 */

public class JsonTransformer implements ResponseTransformer {

    public static Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
