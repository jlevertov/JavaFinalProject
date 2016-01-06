package il.ac.hit.project;

import java.math.*;

/**
 * Provides extended math operations.
 * @author Ran
 *
 */
public class MathUtils {
	private MathUtils() {
	}

	/**
	 * Round double number according to specified decimal places.
	 * 
	 * @param value
	 *            Input double number.
	 * @param places
	 *            Number of decimal places.
	 * @return Double number rounded to the specified decimal places.
	 * @throws IllegalArgumentException
	 */
	public static double round(double value, int places) throws IllegalArgumentException {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
