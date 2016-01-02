package il.ac.hit.project;

public interface IWeatherDataService {
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceExeption;
}
