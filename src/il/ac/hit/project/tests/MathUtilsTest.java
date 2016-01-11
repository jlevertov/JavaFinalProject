package il.ac.hit.project.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import il.ac.hit.project.MathUtils;

public class MathUtilsTest {
	private double num1;
	private double num2;
	private double expected;
	private int decimalPlaces;
	private double delta;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		num1 = 2.2486432132;
		num2 = 2.2500000001;
		expected = 2.25;
		decimalPlaces = 2;
		delta = 0.0001;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRound() {
		assertEquals(expected, MathUtils.round(num1, decimalPlaces), delta);
		assertEquals(expected, MathUtils.round(num2, decimalPlaces), delta);
	}

}
