package weatherForecast;

import weatherForecast.exception.WeatherForecastException;
import weatherForecast.services.WeatherServices;
import weatherForecast.util.JsonTransformer;

import static spark.Spark.*;
/**
 * Author : Paritosh
 * Date : 04/03/21
 */
public class WeatherForecastMain {
    public static void main(String[] args) {
        port(6080);

        //Starting server and handling exceptions while starting
        initExceptionHandler((e) ->  {
            System.out.println("Snap! Something went wrong in starting the server"+ e.getMessage());
            System.exit(100);
        });

        // handling wrong api paths
        notFound("<html><body><h1>Custom 404 handling</h1></body></html>");

        //adding exception handler
        exception(WeatherForecastException.class, (exception, request, response) -> {
            System.out.println("Error occurred: " + exception.getMessage());
            response.status(400);
            response.body("Error occurred. Please check logs");
        });

        //path routes
        path("/api", () -> {
            path("/weather", () -> {
                get("/by-city", WeatherServices::getCityForecast, new JsonTransformer());
            });
        });
        System.out.println("Server started at localhost:6080");
    }
}
