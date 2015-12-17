package il.ac.hit.project;

public class WeatherDataServiceFactory {

	public static IWeatherDataService OPEN_WEATHER_MAP;
	public static IWeatherDataService getWeatherDataService(IWeatherDataService i_service) throws WeatherDataServiceExeption
	{
		return i_service; // temporary
	}
}
