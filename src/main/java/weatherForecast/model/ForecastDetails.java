package weatherForecast.model;

import com.google.gson.annotations.SerializedName;
import weatherForecast.util.JsonTransformer;

/**
 * Author : Paritosh
 * Date : 04/03/21
 */
public class ForecastDetails {

    String name;
    String visibility;
    @SerializedName("sys")  SunriseAndSunset sunriseAndSunset;
    @SerializedName("main") String WeatherData;

    public static ForecastDetails fromJson(String json) {
        return JsonTransformer.gson.fromJson(json, ForecastDetails.class);
    }

    static class SunriseAndSunset {
        String country;
        String sunrise;
        String sunset;
    }

    class MainData {
        String temp;
        String pressure;
        String humidity;
        @SerializedName("feels_like")   String feelsLike;
        @SerializedName("temp_min")  String tempMin;
        @SerializedName("temp_max")  String tempMax;
        @SerializedName("sea_level")  String seaLevel;
        @SerializedName("grnd_level")  String grndLevel;
    }
}
