package il.ac.hit.project.tests;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.json.*;
import org.junit.*;

import il.ac.hit.project.Location;
import il.ac.hit.project.MathUtils;
import il.ac.hit.project.OpenWeatherMapService;
import il.ac.hit.project.UrlRequestWrapper;
import il.ac.hit.project.WeatherData;
import il.ac.hit.project.weather.Temperature;
import il.ac.hit.project.weather.WeatherDescription;
import il.ac.hit.project.weather.Wind;

public class OpenWeatherMapServiceTest {

	private OpenWeatherMapService weatherService;
	private Location locationToTest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		weatherService = OpenWeatherMapService.getInstance();
		locationToTest = new Location("israel", "holon");
	}

	@After
	public void tearDown() throws Exception {
		weatherService = null;
		locationToTest = null;
	}

	@Test
	public void testGetWeatherData() throws Exception {
		WeatherData actualWeatherData = weatherService.getWeatherData(locationToTest);
		WeatherData expectedWeatherData = getWeatherDataByNewParser(locationToTest);

		assertEquals(expectedWeatherData, actualWeatherData);
	}

	private WeatherData getWeatherDataByNewParser(Location location) throws Exception {
		String urlStr = "http://api.openweathermap.org/data/2.5/weather?q=" + location.getCity() + ","
				+ location.getCountry() + "&appid=" + OpenWeatherMapService.getApiId();
		InputStream is = UrlRequestWrapper.getInputStream(urlStr);

		JSONTokener jToken = new JSONTokener(is);
		JSONObject jMainObj = new JSONObject(jToken);

		JSONObject jWeatherObj = jMainObj.getJSONArray("weather").getJSONObject(0);
		WeatherDescription wDescription = new WeatherDescription(jWeatherObj.getString("main"),
				jWeatherObj.getString("description"), jWeatherObj.getString("icon"));

		JSONObject jTempObj = jMainObj.getJSONObject("main");
		Temperature wTemperature = new Temperature(
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp")), 2),
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp_min")), 2),
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp_max")), 2),
				MathUtils.round(jTempObj.getDouble("humidity"), 2));

		JSONObject jWindObj = jMainObj.getJSONObject("wind");

		Double windSpeed = null;
		Double windDegree = null;
		try {
			windSpeed = jWindObj.getDouble("speed");
			windDegree = jWindObj.getDouble("deg");
		} catch (JSONException e) {
		}

		Wind wWind = null;
		if (windSpeed != null && windDegree != null) {
			wWind = new Wind(MathUtils.round(windSpeed, 2), MathUtils.round(windDegree, 2));
		}

		WeatherData wData = new WeatherData(wDescription, wTemperature, wWind);

		return wData;
	}
}
