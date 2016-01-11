package il.ac.hit.project.tests;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import il.ac.hit.project.UrlRequestWrapper;
import il.ac.hit.project.exceptions.UrlRequestWrapperException;

public class UrlRequestWrapperTest {

	private String urlStr;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlStr = "http://api.openweathermap.org/data/2.5/weather?q=tel_aviv,israel&appid=8cf5d7e809490b23eaa717baac6a99c2";
	}

	@After
	public void tearDown() throws Exception {
		urlStr = null;
	}

	@Test
	public void testGetInputStreamURL() {

		InputStream isExpected = null;

		try {
			isExpected = sendGet(urlStr);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		String expectedContent = Json.createReader(isExpected).readObject().toString();

		URL url = null;

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		InputStream isActual = null;

		try {
			isActual = UrlRequestWrapper.getInputStream(url);
		} catch (UrlRequestWrapperException e) {
			fail(e.getMessage());
		}

		String actualContent = Json.createReader(isActual).readObject().toString();

		assertEquals(expectedContent, actualContent);
	}

	@Test
	public void testGetInputStreamString() {
		InputStream isExpected = null;

		try {
			isExpected = sendGet(urlStr);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		String expectedContent = Json.createReader(isExpected).readObject().toString();

		InputStream isActual = null;

		try {
			isActual = UrlRequestWrapper.getInputStream(urlStr);
		} catch (UrlRequestWrapperException e) {
			fail(e.getMessage());
		}

		String actualContent = Json.createReader(isActual).readObject().toString();

		assertEquals(expectedContent, actualContent);
	}

	private InputStream sendGet(String urlStr) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlStr);

		HttpResponse response = client.execute(request);

		return response.getEntity().getContent();
	}
}
