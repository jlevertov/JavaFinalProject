package il.ac.hit.project;

public class WeatherDataServiceExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WeatherDataServiceExeption(String msg) {
		super(msg);
	}

	public WeatherDataServiceExeption(String msg, Throwable cause) {
		super(msg, cause);
	}

}