package sepp.daten;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Einstellungen {

	private final String DATEINAME = "/sepp_config.properties";
	String pfad = System.getProperty("user.home") + DATEINAME;

	String projektPfad;
	String username;

	public String getProjektPfad() {
		return projektPfad;
	}

	public String getUsername() {
		return username;
	}

	public void speichern() throws IOException {
		OutputStream output = new FileOutputStream(pfad);
		Properties einstellungen = new Properties();
		einstellungen.setProperty("benutzer", username);
		einstellungen.setProperty("projekt", projektPfad);
		einstellungen.store(output, null);

	}

	public void laden() throws IOException {
		InputStream input = new FileInputStream(pfad);
		Properties einstellungen = new Properties();
		einstellungen.load(input);
		username = einstellungen.getProperty("benutzer");
		projektPfad = einstellungen.getProperty("projekt");
	}

}
