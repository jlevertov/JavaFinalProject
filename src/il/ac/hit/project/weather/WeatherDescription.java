package il.ac.hit.project.weather;

/**
 * Represents weather data.
 * 
 * @author Ran
 *
 */
public class WeatherDescription {
	private String main;
	private String detailed;
	private String icon;

	/**
	 * C'tor.
	 * 
	 * @param main
	 *            Main weather name.
	 * @param detailed
	 *            Weather detailed description.
	 * @throws Exception
	 */
	public WeatherDescription(String main, String detailed, String icon) throws Exception {
		setMain(main);
		setDetailed(detailed);
		setIcon(icon);
	}

	/**
	 * Get main weather name.
	 * 
	 * @return Main weather name.
	 */
	public String getMain() {
		return main;
	}

	/**
	 * Set main weather name (with validation).
	 * 
	 * @param main
	 *            Main weather name.
	 * @throws Exception
	 */
	private void setMain(String main) throws Exception {
		if (main != null && !main.isEmpty()) {
			this.main = main;
		} else {
			throw new Exception("Main is null or empty!");
		}
	}

	/**
	 * Get detailed weather description.
	 * 
	 * @return Detailed weather description.
	 */
	public String getDetailed() {
		return detailed;
	}

	/**
	 * Set detailed weather description (with validation).
	 * 
	 * @param detailed
	 *            Detailed weather description.
	 * @throws Exception
	 */
	private void setDetailed(String detailed) throws Exception {
		if (detailed != null && !detailed.isEmpty()) {
			this.detailed = detailed;
		} else {
			throw new Exception("Detailed description is null or empty!");
		}
	}

	/**
	 * Get icon ID.
	 * 
	 * @return Icon ID.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Set icon ID (with validation).
	 * 
	 * @param icon
	 *            Icon ID.
	 * @throws Exception
	 */
	private void setIcon(String icon) throws Exception {
		if (icon != null && !icon.isEmpty()) {
			this.icon = icon;
		} else {
			throw new Exception("Icon ID is null or empty!");
		}
	}

	/**
	 * Calculates hash code for this object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detailed == null) ? 0 : detailed.hashCode());
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		return result;
	}

	/**
	 * Decides whether two 'WeatherDescription' objects equal each other or not.
	 * 
	 * @param other
	 *            Second 'WeatherDescription' object.
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
		WeatherDescription other = (WeatherDescription) obj;
		if (this.detailed == null) {
			if (other.detailed != null)
				return false;
		} else if (!this.detailed.equals(other.detailed))
			return false;
		if (this.main == null) {
			if (other.main != null)
				return false;
		} else if (!this.main.equals(other.main))
			return false;
		return true;
	}

	/**
	 * Represent 'WeatherDescription' object as string.
	 */
	@Override
	public String toString() {
		return "WeatherDescription [main=" + main + ", detailed=" + detailed + "]";
	}

}
