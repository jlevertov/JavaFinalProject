package il.ac.hit.project;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OpenWeatherMapServiceTest {

	private OpenWeatherMapService[] WeatherMapArray;
	private Location LocationToTest;
	private URL url;
	private HttpURLConnection Connection;
	private InputStream is;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		LocationToTest = new Location("israel", "Holon");
		WeatherMapArray = new OpenWeatherMapService[1];
		WeatherMapArray[0] = new OpenWeatherMapService();
	}

	@After
	public void tearDown() throws Exception {
		WeatherMapArray = null;
	}

	@Test
	public void testGetWeatherData() throws Exception {
		WeatherMapArray[0].getWeatherData(LocationToTest);
		TestLocation(LocationToTest);
	}
	
	private void TestLocation(Location locate) throws Exception
	{
		url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + locate.getCity() + "," + locate.getCountry() + 
				"&appid=c4d385d27d48a67c88b0626add008b46");
		Connection = (HttpURLConnection) url.openConnection();
		Connection.setRequestMethod("GET");
		Connection.connect();
		is = Connection.getInputStream();
		JsonReader jReader = Json.createReader(is);
		JsonObject objec = jReader.readObject();
		String expectedCod = "200";
		String expectedName = "Holon";
		String actualCod = objec.getString("cod");
		String actualName = objec.getString("name");
		System.out.println("ActualCod: " + actualCod + ".");
		assertEquals("cod Check", expectedCod, actualCod);
		assertEquals("Name Check", expectedName, actualName);
	}
}
