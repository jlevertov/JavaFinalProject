package il.ac.hit.project;

import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OpenWeatherMapService implements IWeatherDataService {

	@Override
	public WeatherData getWeatherData(Location location) throws WeatherDataServiceExeption {
		URL url = null;
		HttpURLConnection con = null;
		InputStream is = null;

		try {
			url = new URL(
					"http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=2de143494c0b295cca9337e1e96b00e0");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			is = con.getInputStream();
			JsonReader jReader = Json.createReader(is);
			//JsonArray jArray = jReader.readArray();
			JsonObject objec = jReader.readObject();
			for (JsonObject ob : objec.getJsonArray("weather").getValuesAs(JsonObject.class))
			{
//				System.out.println(ob.getJsonString("id"));
				System.out.println(ob.getJsonString("main"));
				System.out.println(ob.getJsonString("description"));
				System.out.println(ob.getJsonString("icon"));
			}
//			String bla = objec.getJsonString("weather").getString();
//			System.out.println(bla);
//			for (JsonObject ob : jArray.getValuesAs(JsonObject.class)) {
//				System.out.println(ob.getJsonString("weather"));
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
