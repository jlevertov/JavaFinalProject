package il.ac.hit.project.exceptions;

/**
 * Implements an exception for 'UrlRequestWrapper'.
 * 
 * @author Ran
 *
 */
public class UrlRequestWrapperException extends Exception {

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
	public UrlRequestWrapperException(String msg) {
		super(msg);
	}

	/**
	 * C'tor.
	 * 
	 * @param msg
	 *            Exception message.
	 * @param cause
	 *            Inner exception.
	 */
	public UrlRequestWrapperException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
