package il.ac.hit.project.exceptions;

/**
 * Implements an exception for any class implementing 'IWeatherDataService'
 * interface.
 * 
 * @author Ran
 *
 */
public class WeatherDataServiceExeption extends Exception {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * C'tor.
	 * 
	 * @param msg
	 *            Exception message.
	 */
	public WeatherDataServiceExeption(String msg) {
		super(msg);
	}

	/**
	 * C'tor.
	 * 
	 * @param msg
	 *            Exception message.
	 * @param cause
	 *            Inner message.
	 */
	public WeatherDataServiceExeption(String msg, Throwable cause) {
		super(msg, cause);
	}

}
