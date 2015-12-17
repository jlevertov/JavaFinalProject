package il.ac.hit.project;

public class Location {
	private String Country;
	private String City;
	public Location(String country, String city) {
		super();
		Country = country;
		City = city;
	}
	public String getCountry() {
		return Country;
	}
	public String getCity() {
		return City;
	}
	
}
