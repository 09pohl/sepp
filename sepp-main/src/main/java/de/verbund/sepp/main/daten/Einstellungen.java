package de.verbund.sepp.main.daten;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Einstellungen {

	private final String EINSTELLUNGEN_DATEINAME = "/sepp_config.properties";
	private final String BENUTZER_PROPERTY = "benutzer";
	private final String PROJEKT_PROPERTY = "projekt";

	private static Einstellungen instanz;
	String einstellungenPfad = System.getProperty("user.home") + EINSTELLUNGEN_DATEINAME;

	String projektPfad;
	String username; // keine Doppelpunkte zulassen!

	private Einstellungen() {
	}

	public static Einstellungen getInstance() throws IOException {
		if (instanz == null) {
			instanz = new Einstellungen();
		}
		return instanz;
	}

	static void resetInstance() throws IOException {
		instanz = new Einstellungen();
	}

	public void speichern() throws IOException {
		OutputStream output = new FileOutputStream(einstellungenPfad);
		Properties einstellungen = new Properties();
		einstellungen.setProperty(BENUTZER_PROPERTY, username);
		einstellungen.setProperty(PROJEKT_PROPERTY, projektPfad);
		einstellungen.store(output, null);
		output.close();
	}

	public void laden() throws IOException {
		InputStream input = new FileInputStream(einstellungenPfad);
		Properties einstellungen = new Properties();
		einstellungen.load(input);
		username = einstellungen.getProperty(BENUTZER_PROPERTY);
		projektPfad = einstellungen.getProperty(PROJEKT_PROPERTY);
		input.close();
	}

	public String getProjektPfad() {
		return projektPfad;
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
