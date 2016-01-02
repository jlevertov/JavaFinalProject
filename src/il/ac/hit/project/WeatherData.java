package il.ac.hit.project;

public class WeatherData {
	private int id;
	private String main;
	private String description;

	public WeatherData(int id, String main, String description) {
		setId(id);
		setMain(main);
		setDescription(description);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	private void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "WeatherData [id=" + id + ", main=" + main + ", description=" + description + "]";
	}

}
