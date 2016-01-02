package il.ac.hit.project;

public class WeatherDataServiceFactory {

	public static final int OPEN_WEATHER_MAP = 0;

	public static IWeatherDataService getWeatherDataService(int service) throws WeatherDataServiceExeption {
		IWeatherDataService weatherService = null;
		switch (service) {
		case OPEN_WEATHER_MAP:
			weatherService = new OpenWeatherMapService();
			break;
		default:
			break;
		}
		
		return weatherService;

//		return new IWeatherDataService() {
//
//			@Override
//			public WeatherData getWeatherData(Location location) throws WeatherDataServiceExeption {
//				return null;
//			}
//		}; // temporary
	}

	private WeatherDataServiceFactory() {
	}
}
