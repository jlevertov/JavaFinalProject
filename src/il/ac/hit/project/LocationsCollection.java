package il.ac.hit.project;

import java.io.*;
import javax.json.*;
import java.util.*;

public class LocationsCollection {

	private static Hashtable<String, ArrayList<String>> countriesToCities = new Hashtable<>();

	public static void loadCities() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("countriesToCities.json");

		JsonReader jReader = Json.createReader(fis);
		JsonObject jMainObj = jReader.readObject();

		JsonArray citiesArr = null;
		ArrayList<String> citiesList = null;

		for (String countryName : jMainObj.keySet()) {
			citiesArr = jMainObj.getJsonArray(countryName);
			citiesList = new ArrayList<>();
			for (JsonValue cityObj : citiesArr) {
				citiesList.add(cityObj.toString());
			}
			countriesToCities.put(countryName, citiesList);
		}
	}
	
	public static Set<String> getCountries()
	{
		return countriesToCities.keySet();
	}
	
	public static ArrayList<String> getCities(String country)
	{
		ArrayList<String> cities = new ArrayList<String>();
		for(String city : countriesToCities.get(country))
		{
			cities.add(city.replaceAll("\"", ""));
		}
		return cities;
	}

}
