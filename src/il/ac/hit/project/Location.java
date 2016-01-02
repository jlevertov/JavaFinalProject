package il.ac.hit.project;

public class Location {
	private String country;
	private String city;

	public Location(String country, String city) {
		setCountry(country);
		setCity(city);
	}

	public String getCountry() {
		return country;
	}

	protected void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	protected void setCity(String city) {
		this.city = city;
	}

}
