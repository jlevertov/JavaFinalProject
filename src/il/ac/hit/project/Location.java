package il.ac.hit.project;

/**
 * Describes a location by country and city.
 * 
 * @author Ran
 *
 */
public class Location {
	private String country;
	private String city;

	/**
	 * C'tor.
	 * 
	 * @param country
	 *            Country name.
	 * @param city
	 *            City name.
	 * @throws Exception
	 */
	public Location(String country, String city) throws Exception {
		setCountry(country);
		setCity(city);
	}

	/**
	 * Get country name.
	 * 
	 * @return Country name.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set country name (with validation).
	 * 
	 * @param country
	 *            A country to set.
	 * @throws Exception
	 */
	protected void setCountry(String country) throws Exception {
		if (country != null && !country.isEmpty()) {
			this.country = country;
		} else {
			throw new Exception("Country is null or empty!");
		}
	}

	/**
	 * Get city name.
	 * 
	 * @return City name.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set city name (with validation).
	 * 
	 * @param city
	 *            A city to set.
	 * @throws Exception
	 */
	protected void setCity(String city) throws Exception {
		if (city != null && !city.isEmpty()) {
			this.city = city;
		} else {
			throw new Exception("City is null or empty!");
		}
	}

	/**
	 * Represent 'Location' object as string.
	 */
	@Override
	public String toString() {
		return "Location [country=" + country + ", city=" + city + "]";
	}
}
