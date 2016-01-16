package il.ac.hit.project;

import javax.imageio.ImageIO;
import javax.swing.*;

import il.ac.hit.project.exceptions.*;
import il.ac.hit.project.weather.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientGUI {

	private IWeatherDataService service;

	private JFrame frmMain;
	private JLabel lblHeader;
	private JPanel pnlInputFields;
	private JPanel pnlWeather;

	private JLabel lblCountry;
	private JComboBox<String> cboCountry;
	private JLabel lblCity;
	private JComboBox<String> cboCity;
	private JButton btnGo;

	private JLabel lblIcon;
	private JLabel lblMinMaxTemperature;
	private JLabel lblMainTemperature;
	private JLabel lblMainDescription;
	private JLabel lblDetailedDescription;
	private JLabel lblHumidity;
	private JLabel lblWind;

	private static final int HEADER_FONT_SIZE = 50;
	private static final int DEFAULT_FONT_SIZE = 16;
	private static final int MAIN_FONT_SIZE = 20;
	private static final int WEATHER_FONT_SIZE = 14;

	public ClientGUI() {
		service = null;
		try {
			service = WeatherDataServiceFactory.getWeatherDataService(WeatherDataServiceFactory.OPEN_WEATHER_MAP);
		} catch (WeatherDataServiceExeption e) {
			showMessageBox(e.getMessage());
		}

		frmMain = new JFrame("Weather Service - Java Final Project - Ran&Jacob");
		pnlInputFields = new JPanel();
		pnlWeather = new JPanel();

		lblHeader = new JLabel("Weather");
		lblCountry = new JLabel("Country");
		cboCountry = new JComboBox<>();
		lblCity = new JLabel("City");
		cboCity = new JComboBox<>();
		btnGo = new JButton("Go!");

		lblIcon = new JLabel();
		lblMinMaxTemperature = new JLabel("", JLabel.CENTER);
		lblMainTemperature = new JLabel("", JLabel.CENTER);
		lblMainDescription = new JLabel();
		lblDetailedDescription = new JLabel();
		lblHumidity = new JLabel();
		lblWind = new JLabel();
	}

	public void load() {
		loadData();
		loadFrame();
		addListeners();
	}

	private void loadData() {
		try {
			LocationsCollection.loadCities();
		} catch (FileNotFoundException e) {
			showMessageBox(e.getMessage());
		}
	}

	private void loadFrame() {

		lblHeader.setFont(new Font(lblHeader.getName(), lblHeader.getFont().getStyle(), HEADER_FONT_SIZE));
		lblMainTemperature.setFont(
				new Font(lblMainTemperature.getName(), lblMainTemperature.getFont().getStyle(), MAIN_FONT_SIZE));
		lblMainDescription.setFont(
				new Font(lblMainDescription.getName(), lblMainDescription.getFont().getStyle(), MAIN_FONT_SIZE));
		lblCity.setFont(new Font(lblCity.getName(), lblCity.getFont().getStyle(), DEFAULT_FONT_SIZE));
		lblCountry.setFont(new Font(lblCountry.getName(), lblCountry.getFont().getStyle(), DEFAULT_FONT_SIZE));
		lblMinMaxTemperature.setFont(
				new Font(lblMinMaxTemperature.getName(), lblMinMaxTemperature.getFont().getStyle(), WEATHER_FONT_SIZE));
		lblDetailedDescription.setFont(new Font(lblDetailedDescription.getName(),
				lblDetailedDescription.getFont().getStyle(), WEATHER_FONT_SIZE));
		lblHumidity.setFont(new Font(lblHumidity.getName(), lblHumidity.getFont().getStyle(), WEATHER_FONT_SIZE));
		lblWind.setFont(new Font(lblWind.getName(), lblWind.getFont().getStyle(), WEATHER_FONT_SIZE));

		pnlInputFields.setLayout(new FlowLayout());
		pnlInputFields.add(lblCountry);
		pnlInputFields.add(cboCountry);
		pnlInputFields.add(lblCity);
		pnlInputFields.add(cboCity);
		pnlInputFields.add(btnGo);

		GridBagLayout gbLayout = new GridBagLayout();
		pnlWeather.setLayout(gbLayout);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 10;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
		pnlWeather.add(lblIcon, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		pnlWeather.add(lblMinMaxTemperature, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		pnlWeather.add(lblMainTemperature, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 20;
		gbc.gridx = 2;
		gbc.gridy = 0;
		pnlWeather.add(lblMainDescription, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		pnlWeather.add(lblDetailedDescription, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 2;
		pnlWeather.add(lblHumidity, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		pnlWeather.add(lblWind, gbc);

		frmMain.setLayout(new BoxLayout(frmMain.getContentPane(), BoxLayout.Y_AXIS));
		frmMain.add(lblHeader);
		frmMain.add(pnlInputFields);
		frmMain.add(pnlWeather);

		for (String country : LocationsCollection.getCountries()) {
			cboCountry.addItem(country);
		}
		cboCity.addItem("");

		Color backColor = new Color(200, 230, 250);
		frmMain.getContentPane().setBackground(backColor);
		pnlInputFields.setOpaque(false);
		pnlWeather.setOpaque(false);
		frmMain.setSize(700, 450);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setVisible(true);
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
					emptyDataLabels();
					return;
				}

				Location location = null;
				try {
					location = new Location(selectedCountry.replaceAll(" ", "_"), selectedCity.replaceAll(" ", "_"));
				} catch (Exception e1) {
					emptyDataLabels();
					showMessageBox(e1.getMessage());
					return;
				}

				WeatherData weatherData = null;
				try {
					weatherData = service.getWeatherData(location);
				} catch (WeatherDataServiceExeption e1) {
					emptyDataLabels();
					showMessageBox(e1.getMessage());
					return;
				}

				setDataLabels(weatherData);
			}
		});
	}

	private void setDataLabels(WeatherData weatherData) {
		WeatherDescription description = weatherData.getDescription();
		Temperature temperature = weatherData.getTemperature();
		Wind wind = weatherData.getWind();

		URL url = null;
		try {
			url = new URL("http://openweathermap.org/img/w/" + description.getIcon() + ".png");
		} catch (MalformedURLException e) {
			showMessageBox("Couldn't load weather icon from URL!");
		}

		if (url != null) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
				showMessageBox("Couldn't load weather icon as buffered image!");
			}
			if (image != null) {
				int newSize = 150;
				BufferedImage resized = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = resized.createGraphics();
				g.drawImage(image, 0, 0, newSize, newSize, null);
				g.dispose();

				ImageIcon iconResized = new ImageIcon(resized);
				lblIcon.setIcon(iconResized);
			}
		}

		lblMinMaxTemperature.setText(temperature.getMinMaxStr());
		lblMainTemperature.setText(temperature.getMainStr());
		lblMainDescription.setText(description.getMain());
		lblDetailedDescription.setText(description.getDetailed());
		lblHumidity.setText(temperature.getHumidityStr());

		if (wind != null) {
			lblWind.setText(wind.getWindStr());
		}
	}

	private void emptyDataLabels() {
		lblMinMaxTemperature.setText("");
		lblMainTemperature.setText("");
		lblMainDescription.setText("");
		lblDetailedDescription.setText("");
		lblHumidity.setText("");
		lblWind.setText("");
	}

	private void showMessageBox(String message) {
		JOptionPane.showMessageDialog(null, message, "Weather", JOptionPane.INFORMATION_MESSAGE);
	}

}
