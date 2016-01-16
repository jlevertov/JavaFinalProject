package il.ac.hit.project;

import javax.json.*;

import il.ac.hit.project.exceptions.*;
import il.ac.hit.project.weather.*;

import java.io.InputStream;

/**
 * Enables getting weather data by OpenWeatherMap web service.
 * 
 * @author Ran
 *
 */
public class OpenWeatherMapService implements IWeatherDataService {

	private static final String apiId = "8cf5d7e809490b23eaa717baac6a99c2";

	private static OpenWeatherMapService instance = null;

	private OpenWeatherMapService() {
	}

	/**
	 * Get 'OpenWeatherMapService' singleton instance.
	 * 
	 * @return 'OpenWeatherMapService' singleton instance.
	 */
	public static OpenWeatherMapService getInstance() {
		if (instance == null) {
			instance = new OpenWeatherMapService();
		}
		return instance;
	}

	/**
	 * Get weather data of a specific location (country and city).
	 * 
	 * @param location
	 *            The location (country and city) which the weather data related
	 *            to.
	 * @return Weather data of a specific location.
	 * @throws WeatherDataServiceExeption
	 */
	@Override
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceExeption {
		String exceptionMessage = "Failed on getting weather data!";
		String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + location.getCity() + ","
				+ location.getCountry() + "&appid=" + apiId;

		InputStream is = null;

		JsonReader jReader = null;
		JsonObject jMainObj = null;
		JsonObject jWeatherObj = null;
		JsonObject jTempObj = null;
		JsonObject jWindObj = null;

		JsonNumber jWindSpeed = null;
		JsonNumber jWindDegree = null;

		WeatherDescription wDescription = null;
		Temperature wTemperature = null;
		Wind wWind = null;
		WeatherData wData = null;

		try {
			is = UrlRequestWrapper.getInputStream(urlStr);
			jReader = Json.createReader(is);
			jMainObj = jReader.readObject();

			jWeatherObj = jMainObj.getJsonArray("weather").getJsonObject(0);
			wDescription = new WeatherDescription(jWeatherObj.getString("main"), jWeatherObj.getString("description"),
					jWeatherObj.getString("icon"));

			jTempObj = jMainObj.getJsonObject("main");
			wTemperature = new Temperature(
					MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getJsonNumber("temp").doubleValue()), 2),
					MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getJsonNumber("temp_min").doubleValue()), 2),
					MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getJsonNumber("temp_max").doubleValue()), 2),
					jTempObj.getJsonNumber("humidity").doubleValue());

			jWindObj = jMainObj.getJsonObject("wind");
			jWindSpeed = jWindObj.getJsonNumber("speed");
			jWindDegree = jWindObj.getJsonNumber("deg");

			if (jWindSpeed != null && jWindDegree != null) {
				wWind = new Wind(MathUtils.round(jWindSpeed.doubleValue(), 2),
						MathUtils.round(jWindDegree.doubleValue(), 2));
			}

			wData = new WeatherData(wDescription, wTemperature, wWind);

		} catch (UrlRequestWrapperException e) {
			throw new WeatherDataServiceExeption("Selected location is unsupported!", e);
		} catch (IllegalArgumentException e) {
			throw new WeatherDataServiceExeption(exceptionMessage + " (IllegalArgumentException)", e);
		} catch (Exception e) {
			throw new WeatherDataServiceExeption(exceptionMessage + " (Exception)", e);
		}

		return wData;
	}

	/**
	 * Get API ID of OpenWeatherMap account.
	 * 
	 * @return API ID of OpenWeatherMap account.
	 */
	public static String getApiId() {
		return apiId;
	}

}
