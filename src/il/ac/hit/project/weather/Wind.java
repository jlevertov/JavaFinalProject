package il.ac.hit.project.weather;

/**
 * Represents weather wind.
 * 
 * @author Ran
 *
 */
public class Wind {
	private double speed;
	private double degree;

	/**
	 * C'tor.
	 * 
	 * @param speed
	 *            Wind speed (in meter/second).
	 * @param degree
	 *            Wind direction (in degrees).
	 * @throws Exception
	 */
	public Wind(double speed, double degree) throws Exception {
		setSpeed(speed);
		setDegree(degree);
	}

	/**
	 * Get wind speed.
	 * 
	 * @return Wind speed.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Set wind speed (with validation).
	 * 
	 * @param speed
	 *            Wind speed.
	 * @throws Exception
	 */
	private void setSpeed(double speed) throws Exception {
		if (speed >= 0 && speed <= 500) {
			this.speed = speed;
		} else {
			throw new Exception("Wind speed is out of its limits! (" + speed + ")");
		}
	}

	/**
	 * Get wind direction in degrees.
	 * 
	 * @return Wind direction (in degrees).
	 */
	public double getDegree() {
		return degree;
	}

	/**
	 * Set wind direction in degrees (with validation).
	 * 
	 * @param degree
	 *            Wind direction (in degrees).
	 * @throws Exception
	 */
	private void setDegree(double degree) throws Exception {
		if (degree >= 0 && degree <= 360) {
			this.degree = degree;
		} else {
			throw new Exception("Wind direction is out of its limits! (" + degree + ")");
		}
	}

	/**
	 * Get wind details as formatted string.
	 * 
	 * @return Wind details as formatted string.
	 */
	public String getWindStr() {
		return "Wind: " + Double.toString(speed) + " mps, " + Double.toString(degree) + "°";
	}

	/**
	 * Calculates hash code for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(speed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Decides whether two 'Wind' objects equal each other or not.
	 * 
	 * @param other
	 *            Second 'Wind' object.
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
		Wind other = (Wind) obj;
		if (Double.doubleToLongBits(this.degree) != Double.doubleToLongBits(other.degree))
			return false;
		if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed))
			return false;
		return true;
	}

	/**
	 * Represent 'Wind' object as string.
	 */
	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", degree=" + degree + "]";
	}

}
