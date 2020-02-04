package de.verbund.sepp.main.daten;

import java.io.IOException;
import java.util.ArrayList;

public interface DatenSchnittstelle {

	final static String PRIMAER_DATEIENDUNG = ".sepp";
	final static String PRIMAER_DATEINAME = "projekt" + PRIMAER_DATEIENDUNG;

	// Enthält Benutzername und Projektpfad
	Einstellungen getEinstellungen() throws IOException;

	// Gibt eine Liste aller Dateien des Projekts zurück, auch aus Unterordnern
	ArrayList<String> getDateiPfade(Einstellungen einstellungen) throws IOException;

	// Enthält Icon, Dateiname, Erstellungsdatum, Änderungsdatum, To-Dos und
	// Kommentare
	DateiInformationen getDateiInformationen(String dateiPfad) throws IOException;

	// speichert die DateiInformationen
	void speichereDateiInformationen(DateiInformationen dateiInfo) throws IOException;

}
