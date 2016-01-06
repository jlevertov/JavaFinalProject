package il.ac.hit.project;

import java.io.*;
import java.net.*;

import il.ac.hit.project.exceptions.UrlRequestWrapperException;

/**
 * Wraps web requests.
 * 
 * @author Ran
 *
 */
public class UrlRequestWrapper {

	private UrlRequestWrapper() {
	}

	/**
	 * Get input stream by URL web request.
	 * 
	 * @param url
	 *            Web request as URL.
	 * @return input stream of the web response.
	 * @throws UrlRequestWrapperException
	 */
	public static InputStream getInputStream(URL url) throws UrlRequestWrapperException {
		String exceptionMessage = "Failed on sending request to URL!";

		HttpURLConnection connection = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			return connection.getInputStream();

		} catch (FileNotFoundException e) {
			throw new UrlRequestWrapperException(exceptionMessage + " (FileNotFoundException)", e);
		} catch (MalformedURLException e) {
			throw new UrlRequestWrapperException(exceptionMessage + " (MalformedURLException)", e);
		} catch (ProtocolException e) {
			throw new UrlRequestWrapperException(exceptionMessage + " (ProtocolException)", e);
		} catch (IOException e) {
			throw new UrlRequestWrapperException(exceptionMessage + " (IOException)", e);
		}
	}

	/**
	 * Get input stream by string web request.
	 * 
	 * @param urlStr
	 *            Web request as string.
	 * @return input stream of the web response.
	 * @throws UrlRequestWrapperException
	 */
	public static InputStream getInputStream(String urlStr) throws UrlRequestWrapperException {
		URL url = null;

		try {
			url = new URL(urlStr);

		} catch (MalformedURLException e) {
			throw new UrlRequestWrapperException("Failed on initializing URL! (MalformedURLException)", e);
		}

		return getInputStream(url);
	}

}
