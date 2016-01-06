package il.ac.hit.project.weather;

/**
 * Represents weather temperature.
 * 
 * @author Ran
 *
 */
public class Temperature {
	private static final double minimalTemp = -80.0;
	private static final double maximalTemp = 80.0;
	private double main;
	private double min;
	private double max;
	private double humidity;

	/**
	 * C'tor.
	 * 
	 * @param main
	 *            Main temperature (in Celsius).
	 * @param min
	 *            Minimum temperature (in Celsius).
	 * @param max
	 *            Maximum temperature (in Celsius).
	 * @param humidity
	 *            Humidity percent.
	 * @throws Exception
	 */
	public Temperature(double main, double min, double max, double humidity) throws Exception {
		setMain(main);
		setMin(min);
		setMax(max);
		setHumidity(humidity);
	}

	/**
	 * Converts temperature from Kelvin units to Celsius.
	 * 
	 * @param kelvinValue
	 *            Temperature in Kelvin units.
	 * @return Temperature in Celsius units.
	 */
	public static double kelvinToCelsius(double kelvinValue) {
		return kelvinValue - 273.15;
	}

	/**
	 * Get main temperature in Celsius.
	 * 
	 * @return Main temperature (in Celsius).
	 */
	public double getMain() {
		return main;
	}

	/**
	 * Set main temperature in Celsius (with validation).
	 * 
	 * @param main
	 *            Main temperature (in Celsius).
	 * @throws Exception
	 */
	private void setMain(double main) throws Exception {
		if (main >= minimalTemp && main <= maximalTemp) {
			this.main = main;
		} else {
			throw new Exception("Main temperature is out of its limits! (" + main + ")");
		}
	}

	/**
	 * Get minimal temperature in Celsius.
	 * 
	 * @return Minimal temperature (in Celsius).
	 */
	public double getMin() {
		return min;
	}

	/**
	 * Set minimal temperature in Celsius (with validation).
	 * 
	 * @param min
	 *            Minimal temperature (in Celsius).
	 * @throws Exception
	 */
	private void setMin(double min) throws Exception {
		if (min >= minimalTemp && min <= maximalTemp) {
			this.min = min;
		} else {
			throw new Exception("Minimal temperature is out of its limits! (" + min + ")");
		}
	}

	/**
	 * Get maximal temperature in Celsius.
	 * 
	 * @return Maximal temperature (in Celsius).
	 */
	public double getMax() {
		return max;
	}

	/**
	 * Set main temperature in Celsius (with validation).
	 * 
	 * @param max
	 *            Maximal temperature (in Celsius).
	 * @throws Exception
	 */
	private void setMax(double max) throws Exception {
		if (min >= minimalTemp && min <= maximalTemp) {
			this.max = max;
		} else {
			throw new Exception("Maximal temperature is out of its limits! (" + max + ")");
		}
	}

	/**
	 * Get humidity percent.
	 * 
	 * @return Humidity percent.
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Set humidity percent (with validation).
	 * 
	 * @param humidity
	 *            Humidity percent.
	 * @throws Exception
	 */
	private void setHumidity(double humidity) throws Exception {
		if (humidity >= 0.0 && humidity <= 100.0) {
			this.humidity = humidity;
		} else {
			throw new Exception("Humidity percent is out of its limits! (" + humidity + ")");
		}
	}

	/**
	 * Calculates hash code for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(humidity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(main);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(max);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(min);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Decides whether two 'Temperature' objects equal each other or not.
	 * 
	 * @param other
	 *            Second 'Temperature' object.
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
		Temperature other = (Temperature) obj;
		if (Double.doubleToLongBits(this.humidity) != Double.doubleToLongBits(other.humidity))
			return false;
		if (Double.doubleToLongBits(this.main) != Double.doubleToLongBits(other.main))
			return false;
		if (Double.doubleToLongBits(this.max) != Double.doubleToLongBits(other.max))
			return false;
		if (Double.doubleToLongBits(this.min) != Double.doubleToLongBits(other.min))
			return false;
		return true;
	}

	/**
	 * Represent 'Temperature' object as string.
	 */
	@Override
	public String toString() {
		return "Temperature [main=" + main + ", min=" + min + ", max=" + max + ", humidity=" + humidity + "]";
	}

}
