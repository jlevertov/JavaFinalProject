package il.ac.hit.project;

import javax.swing.*;

import il.ac.hit.project.exceptions.*;
import il.ac.hit.project.weather.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class ClientGUI {

	private IWeatherDataService service;

	private JFrame frmMain;
	private JLabel lblHeader;
	private JLabel lblCountry;
	private JComboBox<String> cboCountry;
	private JLabel lblCity;
	private JComboBox<String> cboCity;
	private JButton btnGo;
	private JPanel pnlWeather;

	// private Image imgIcon;
	// Try: JLabel lblIcon; lblIcon.SetIcon(new ImageIcon(imgIcon));
	private JLabel lblMinMaxTemperature;
	private JLabel lblMainTemperature;
	private JLabel lblMainDescription;
	private JLabel lblDetailedDescription;
	private JLabel lblHumidity;
	private JLabel lblWind;

	public ClientGUI() {
		service = null;
		try {
			service = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
		} catch (WeatherDataServiceExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frmMain = new JFrame("Weather Service - Java Final Project - Ran&Jacob");
		lblHeader = new JLabel("Weather");
		lblCountry = new JLabel("Country");
		cboCountry = new JComboBox<>();
		lblCity = new JLabel("City");
		cboCity = new JComboBox<>();
		btnGo = new JButton("Go!");
		pnlWeather = new JPanel();

		// imgIcon=new
		lblMinMaxTemperature = new JLabel();
		lblMainTemperature = new JLabel();
		cboCountry = new JComboBox<>();
		cboCity = new JComboBox<>();
		lblMainDescription = new JLabel();
		lblDetailedDescription = new JLabel();
		lblHumidity = new JLabel();
		lblWind = new JLabel();
	}

	public static void main(String[] args) {
		ClientGUI client = new ClientGUI();
		client.loadData();
		client.loadFrame();
		client.addListeners();
	}

	private void addListeners() {
		cboCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cboCountry.getSelectedItem() == null) {
					return;
				}

				cboCity.removeAllItems();
				cboCity.addItem("");

				String selectedCountry = cboCountry.getSelectedItem().toString();
				if (selectedCountry.equals("")) {
					return;
				}

				for (String city : LocationsCollection.getCities(selectedCountry)) {
					cboCity.addItem(city);
				}
			}
		});

		btnGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedCountry = cboCountry.getSelectedItem().toString();
				String selectedCity = cboCity.getSelectedItem().toString();

				if (selectedCountry.equals("") || selectedCity.equals("")) {
					return;
				}

				Location location = null;
				try {
					location = new Location(selectedCountry, selectedCity);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				WeatherData weatherData = null;
				try {
					weatherData = service.getWeatherData(location);
				} catch (WeatherDataServiceExeption e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setDataLabels(weatherData);
			}
		});
	}

	public void loadFrame() {

		pnlWeather.setLayout(new FlowLayout());
		pnlWeather.add(lblMinMaxTemperature);
		pnlWeather.add(lblMainTemperature);
		pnlWeather.add(lblMainDescription);
		pnlWeather.add(lblDetailedDescription);
		pnlWeather.add(lblHumidity);
		pnlWeather.add(lblWind);

		frmMain.setLayout(new FlowLayout());
		frmMain.add(lblHeader);
		frmMain.add(lblCountry);
		frmMain.add(cboCountry);
		frmMain.add(lblCity);
		frmMain.add(cboCity);
		frmMain.add(btnGo);
		frmMain.add(pnlWeather);

		cboCountry.addItem("");
		for (String country : LocationsCollection.getCountries()) {
			cboCountry.addItem(country);
		}
		cboCity.addItem("");

		frmMain.setSize(800, 600);
		frmMain.setVisible(true);
	}

	private void loadData() {
		try {
			LocationsCollection.loadCities();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setDataLabels(WeatherData weatherData) {
		WeatherDescription description = weatherData.getDescription();
		Temperature temperature = weatherData.getTemperature();
		Wind wind = weatherData.getWind();

		lblMinMaxTemperature.setText(temperature.getMinMaxStr());
		lblMainTemperature.setText(temperature.getMainStr());
		lblMainDescription.setText(description.getMain());
		lblDetailedDescription.setText(description.getDetailed());
		lblHumidity.setText(temperature.getHumidityStr());
		lblWind.setText(wind.getWindStr());

	}

}
