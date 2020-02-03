package sepp.daten;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Einstellungen {

	private final String DATEINAME = "/sepp_config.properties";

	String projektPfad;
	String username;

	public String getProjektPfad() {
		return projektPfad;
	}

	public String getUsername() {
		return username;
	}

	public void speichern() {
		String pfad = System.getProperty("user.home");
		try (OutputStream output = new FileOutputStream(pfad + DATEINAME)) {

			Properties einstellungen = new Properties();
			einstellungen.setProperty("benutzer", username);
			einstellungen.setProperty("projekt", projektPfad);

			// save properties to project root folder
			einstellungen.store(output, null);

			System.out.println(einstellungen);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
