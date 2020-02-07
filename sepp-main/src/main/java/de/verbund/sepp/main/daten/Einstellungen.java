package de.verbund.sepp.main.daten;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import de.verbund.sepp.main.utils.DateiHelfer;

public class Einstellungen {

	private final String EINSTELLUNGEN_ORDNER = "/SEPP";
	private final String EINSTELLUNGEN_DATEINAME = "/sepp_config.properties";
	private final String BENUTZER_PROPERTY = "benutzer";
	private final String PROJEKT_PROPERTY = "projekt";

	private static Einstellungen instance;
	String einstellungenPfad = System.getProperty("user.home") + EINSTELLUNGEN_ORDNER + EINSTELLUNGEN_DATEINAME;
	String einstellungenOrdnerPfad = System.getProperty("user.home") + EINSTELLUNGEN_ORDNER;

	String projektPfad;
	String username; // keine Doppelpunkte zulassen!

	private Einstellungen() {
	}

	public static Einstellungen getInstance() throws IOException {
		if (instance == null) {
			instance = new Einstellungen();
		}
		return instance;
	}

	static void resetInstance() throws IOException {
		instance = new Einstellungen();
	}

	public void speichern() throws IOException {
		File ordner = new File(einstellungenOrdnerPfad);
		if (!ordner.exists()) {
			ordner.mkdir();
		}
		OutputStream output = new FileOutputStream(einstellungenPfad);
		Properties einstellungen = new Properties();
		einstellungen.setProperty(BENUTZER_PROPERTY, username);
		einstellungen.setProperty(PROJEKT_PROPERTY, projektPfad);

		einstellungen.store(output, null);
		DateiHelfer projektDatei = new DateiHelfer(projektPfad + "/" + DatenSchnittstelle.PRIMAER_DATEINAME);

		System.out.println(projektPfad + "/" + DatenSchnittstelle.PRIMAER_DATEINAME);

		if (!projektDatei.existiert()) {
			projektDatei.schreibe("");
		}
		output.close();
	}

	public void laden() throws IOException {
		InputStream input = new FileInputStream(einstellungenPfad);
		Properties einstellungen = new Properties();
		einstellungen.load(input);
		username = einstellungen.getProperty(BENUTZER_PROPERTY);
		projektPfad = einstellungen.getProperty(PROJEKT_PROPERTY);
		input.close();
		this.speichern(); // stellt sicher, dass die Projektdateien angelegt werden
	}

	public boolean isPropertyEmpty() {
		return username == null || projektPfad == null;
	}

	public String getProjektPfad() {
		return projektPfad;
	}

	public String getProjektDateiPfad() {
		return projektPfad + "/" + DatenSchnittstelle.PRIMAER_DATEINAME;
	}

	public void setProjektPfad(String projektPfad) {
		this.projektPfad = projektPfad;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEinstellungenPfad() {
		return einstellungenPfad;
	}
}
