package sepp.daten;

public class Einstellungen {

	private String projektPfad;
	private String username;

<<<<<<< HEAD
=======
	String einstellungenPfad = System.getProperty("user.home") + EINSTELLUNGEN_DATEINAME;

	String projektPfad;
	String username; // keine Doppelpunkte zulassen!

	public void speichern() throws IOException {
		OutputStream output = new FileOutputStream(einstellungenPfad);
		Properties einstellungen = new Properties();
		einstellungen.setProperty(BENUTZER_PROPERTY, username);
		einstellungen.setProperty("projekt", projektPfad);
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
>>>>>>> refs/remotes/origin/sepp-15-todo_kommentar_gui
}
