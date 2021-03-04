package weatherForecast.util;

import weatherForecast.exception.WeatherForecastException;
import weatherForecast.exception.WeatherForecastException.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static weatherForecast.util.Constants.APP_ID_SUFFIX;
import static weatherForecast.util.Constants.BASE_URL;

/**
 * Author : Paritosh
 * Date : 04/03/21
 */
public class RestClient {

    public static final Map<String, String> commonHeaders = new HashMap<>();

    static {
        commonHeaders.put("Accept", "application/json");
    }

    public static String get(String urlSuffix, Constants.MODES mode) throws WeatherForecastException {
        return get(urlSuffix, commonHeaders, mode);
    }

    public static String get(String urlSuffix, Map<String, String> headers, Constants.MODES mode) throws WeatherForecastException {
        if (headers == null) headers = new HashMap<>();
        try {
            URL url = new URL(BASE_URL + urlSuffix + APP_ID_SUFFIX);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            for (String headerName : headers.keySet())
                conn.setRequestProperty(headerName, headers.get(headerName));

            if (conn.getResponseCode() != 200) {
                throw new WeatherApiException(mode, conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String line;
            StringBuilder output = new StringBuilder();
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            conn.disconnect();
            return output.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURL(mode, e.getMessage());
        } catch (IOException e) {
            throw new WeatherForecastException(mode, "IOException while reading response data: [" + e.getMessage() + "]");
        }
    }
}
