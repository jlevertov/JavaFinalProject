package il.ac.hit.project;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.json.*;
import org.junit.*;

import il.ac.hit.project.weather.Temperature;
import il.ac.hit.project.weather.WeatherDescription;
import il.ac.hit.project.weather.Wind;

public class OpenWeatherMapServiceTest {

	private OpenWeatherMapService weatherService;
	private Location locationToTest;
	private WeatherData expectedWeatherData;
	private WeatherData actualWeatherData;

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
	}

	@Test
	public void testGetWeatherData() throws Exception {
		actualWeatherData = weatherService.getWeatherData(locationToTest);
		expectedWeatherData = getWeatherDataByNewParser(locationToTest);

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
				jWeatherObj.getString("description"));

		JSONObject jTempObj = jMainObj.getJSONObject("main");
		Temperature wTemperature = new Temperature(
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp")), 2),
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp_min")), 2),
				MathUtils.round(Temperature.kelvinToCelsius(jTempObj.getDouble("temp_max")), 2),
				MathUtils.round(jTempObj.getDouble("humidity"), 2));

		JSONObject jWindObj = jMainObj.getJSONObject("wind");
		Wind wWind = new Wind(MathUtils.round(jWindObj.getDouble("speed"), 2),
				MathUtils.round(jWindObj.getDouble("deg"), 2));

		WeatherData wData = new WeatherData(wDescription, wTemperature, wWind);

		return wData;
	}
}
