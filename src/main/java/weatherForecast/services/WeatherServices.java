package weatherForecast.services;

import spark.Request;
import spark.Response;
import weatherForecast.exception.WeatherForecastException;
import weatherForecast.model.ForecastDetails;
import weatherForecast.util.Constants;
import weatherForecast.util.RestClient;
import weatherForecast.util.Util;

import static weatherForecast.util.Constants.URL_FORECAST_BY_CITY;

/**
 * Author : Paritosh
 * Date : 04/03/21
 * <p>
 * Path: /weather
 */
public class WeatherServices {

    /**
     * Path: by-city
     *
     * @param request
     * @param response
     * @return
     * @throws WeatherForecastException
     */
    public static ForecastDetails getCityForecast(Request request, Response response) throws WeatherForecastException {
        String city = request.queryParams("city");
        if (Util.isEmpty(city))
            throw new WeatherForecastException.WrongInputException(Constants.MODES.WEATHER_BY_CITY, "city name");

        String weatherDataString = RestClient.get(URL_FORECAST_BY_CITY + city, Constants.MODES.WEATHER_BY_CITY);
        if (Util.isEmpty(weatherDataString))
            throw new WeatherForecastException.NullResponseException(Constants.MODES.WEATHER_BY_CITY);

        return ForecastDetails.fromJson(weatherDataString);
    }
}
