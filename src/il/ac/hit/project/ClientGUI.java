package il.ac.hit.project;

import javax.swing.*;
import java.awt.*;

public class ClientGUI {

	private JFrame frmMain;
	private JLabel lblHeader;
	private JLabel lblCountry;
	private JComboBox<String> cboCountry;
	private JLabel lblCity;
	private JComboBox<String> cboCity;
	private JPanel pnlWeather;

	// private Image imgIcon;
	// Try: JLabel lblIcon; lblIcon.SetIcon(new ImageIcon(imgIcon));
	private JLabel lblMinMaxTemperature;
	private JLabel lblMainTemperature;
	private JLabel lblMainDescription;
	private JLabel lblDetailedDescription;
	private JLabel lblHumidity;
	private JLabel lblWind;

	// https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json

	public static void main(String[] args) {
		ClientGUI client = new ClientGUI();
		client.load();
	}

	public ClientGUI() {
		frmMain = new JFrame("Weather Service - Java Final Project - Ran&Jacob");
		lblHeader = new JLabel("Weather");
		lblCountry = new JLabel("Country");
		cboCountry = new JComboBox<>();
		lblCity = new JLabel("City");
		cboCity = new JComboBox<>();
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

	public void load() {
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
		frmMain.add(pnlWeather);
		
		frmMain.setSize(800, 600);
		frmMain.setVisible(true);
	}

}
