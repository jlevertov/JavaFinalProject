package il.ac.hit.project;

import il.ac.hit.project.exceptions.WeatherDataServiceExeption;

/**
 * Generates weather data services (Factory).
 * 
 * @author Ran
 *
 */
public class WeatherDataServiceFactory {

	/**
	 * OpenWeatherMap web service ID.
	 */
	public static final int OPEN_WEATHER_MAP = 0;

	/**
	 * Generates weather data service by given web service ID.
	 * 
	 * @param service
	 *            Web service ID.
	 * @return Weather data service.
	 * @throws WeatherDataServiceExeption
	 */
	public static IWeatherDataService getWeatherDataService(int service) throws WeatherDataServiceExeption {
		IWeatherDataService weatherService = null;
		switch (service) {
		case OPEN_WEATHER_MAP:
			weatherService = OpenWeatherMapService.getInstance();
			break;
		default:
			break;
		}

		return weatherService;
	}

	private WeatherDataServiceFactory() {
	}
}
