package il.ac.hit.project;

import il.ac.hit.project.exceptions.WeatherDataServiceExeption;

/**
 * Interface for getting a weather data.
 * @author Ran
 *
 */
public interface IWeatherDataService {
	
	/**
	 * Get weather data of a specific location (country and city).
	 * 
	 * @param location
	 *            The location (country and city) which the weather data related
	 *            to.
	 * @return Weather data of a specific location.
	 * @throws WeatherDataServiceExeption
	 */
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceExeption;
}
