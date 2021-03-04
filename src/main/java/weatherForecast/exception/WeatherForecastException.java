package weatherForecast.exception;

import weatherForecast.util.Constants;

/**
 * Author : Paritosh
 * Date : 04/03/21
 */
public class WeatherForecastException extends Exception {

    String errorMessage;
    Constants.MODES modes;

    public WeatherForecastException(Constants.MODES modes, String message) {
        this.errorMessage = "[" + modes.name() + "] : " + message;
        this.modes = modes;
    }

    public static class MalformedURL extends WeatherForecastException {
        public MalformedURL(Constants.MODES mode) {
            super(mode, "Malformed URL found");
        }

        public MalformedURL(Constants.MODES mode, String exceptionMessage) {
            super(mode, exceptionMessage);
        }
    }

    public static class WeatherApiException extends WeatherForecastException {
        public WeatherApiException(Constants.MODES mode, int responseCode) {
            super(mode, "Error in external api call -> Response code: [" + responseCode + "]");
        }
    }

    public static class NullResponseException extends WeatherForecastException {
        public NullResponseException(Constants.MODES mode) {
            super(mode, "Null or Empty response found");
        }
    }

    public static class WrongInputException extends WeatherForecastException {
        public WrongInputException(Constants.MODES mode, String inputName) {
            super(mode, "Input parameter [" + inputName + "] passed is wrong");
        }
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
