package il.ac.hit.project;

public class Program {

	public static void main(String[] args) {

		try {
			IWeatherDataService service = WeatherDataServiceFactory
					.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
			service.getWeatherData(null);
		} catch (WeatherDataServiceExeption e) {
			e.printStackTrace();
		}

	}

}
