package il.ac.hit.project;

import il.ac.hit.project.exceptions.WeatherDataServiceExeption;

/**
 * Executes web requests to web services by java services to get weather data.
 * 
 * @author Ran
 *
 */
public class Program {

	/**
	 * Main.
	 * 
	 * @param args
	 *            Input arguments.
	 */
	public static void main(String[] args) {

		IWeatherDataService service = null;
		WeatherData data = null;

		Location location = null;
		try {
			location = new Location("fr", "paris");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		System.out.println(location);

		try {
			service = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
			data = service.getWeatherData(location);

		} catch (WeatherDataServiceExeption e) {
			e.printStackTrace();
			return;
		}

		System.out.println(data);

	}

}
