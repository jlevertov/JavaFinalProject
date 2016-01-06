package il.ac.hit.project;

import il.ac.hit.project.weather.*;

/**
 * Represents weather data.
 * 
 * @author Ran
 *
 */
public class WeatherData {
	private WeatherDescription description;
	private Temperature temperature;
	private Wind wind;

	/**
	 * C'tor.
	 * 
	 * @param description
	 *            Verbal weather description.
	 * @param temperature
	 *            Weather temperature and humidity values.
	 * @param wind
	 *            Wind speed and direction.
	 * @throws Exception
	 */
	public WeatherData(WeatherDescription description, Temperature temperature, Wind wind) throws Exception {
		setDescription(description);
		setTemperature(temperature);
		setWind(wind);
	}

	/**
	 * Get verbal weather description.
	 * 
	 * @return Verbal weather description.
	 */
	public WeatherDescription getDescription() {
		return description;
	}

	/**
	 * Set verbal weather description (with validation).
	 * 
	 * @param description
	 *            Verbal weather description.
	 * @throws Exception
	 */
	private void setDescription(WeatherDescription description) throws Exception {
		if (description != null) {
			this.description = description;
		} else {
			throw new Exception("Weather description object is null!");
		}
	}

	/**
	 * Get weather temperature and humidity.
	 * 
	 * @return Weather temperature and humidity.
	 */
	public Temperature getTemperature() {
		return temperature;
	}

	/**
	 * Set weather temperature and humidity (with validation).
	 * 
	 * @param temperature
	 *            Weather temperature and humidity.
	 * @throws Exception
	 */
	private void setTemperature(Temperature temperature) throws Exception {
		if (temperature != null) {
			this.temperature = temperature;
		} else {
			throw new Exception("Weather temperature object is null!");
		}
	}

	/**
	 * Get wind parameters.
	 * 
	 * @return Wind parameters.
	 */
	public Wind getWind() {
		return wind;
	}

	/**
	 * Set wind parameters (with validation).
	 * 
	 * @param wind
	 *            Wind parameters.
	 * @throws Exception
	 */
	private void setWind(Wind wind) throws Exception {
		if (wind != null) {
			this.wind = wind;
		} else {
			throw new Exception("Weather wind object is null!");
		}
	}

	/**
	 * Calculates hash code for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((wind == null) ? 0 : wind.hashCode());
		return result;
	}

	/**
	 * Decides whether two 'WeatherData' objects equal each other or not.
	 * 
	 * @param other
	 *            Second 'WeatherData' object.
	 * @return True - if the objects equal each other, otherwise - false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherData other = (WeatherData) obj;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!this.temperature.equals(other.temperature))
			return false;
		if (this.wind == null) {
			if (other.wind != null)
				return false;
		} else if (!this.wind.equals(other.wind))
			return false;
		return true;
	}

	/**
	 * Represent 'WeatherData' object as string.
	 */
	@Override
	public String toString() {
		return "WeatherData [\n\tdescription=" + description + ", \n\ttemperature=" + temperature + ", \n\twind=" + wind + "\n]";
	}

}
