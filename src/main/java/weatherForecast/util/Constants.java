package weatherForecast.util;

/**
 * Author : Paritosh
 * Date : 04/03/21
 */
public class Constants {

    public enum MODES {WEATHER_BY_CITY, WEATHER_BY_CORDINATES}

    public static final String BASE_URL = "api.openweathermap.org/data/2.5/";
    public static final String APP_ID = "295db0a69b0d93738b7919533464e955";
    public static final String APP_ID_SUFFIX = "&appid=" + APP_ID;


    public static final String URL_FORECAST_BY_CITY = "weather?q=";
}
