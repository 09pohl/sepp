package de.verbund.sepp.main.daten;

import java.io.IOException;
import java.util.ArrayList;

public interface DatenSchnittstelle {

	final static String PRIMAER_DATEINAME = "projekt.sepp";

	// Enthält Benutzername und Projektpfad
	Einstellungen getEinstellungen() throws IOException;

	// Gibt eine Liste aller Dateien des Projekts zurück, auch aus Unterordnern
	ArrayList<String> getDateiNamen();

	// Enthält Icon, Dateiname, Erstellungsdatum, Änderungsdatum, To-Dos und
	// Kommentare
	DateiInformationen getDateiInformationen(String dateiPfad) throws IOException;

	// speichert die DateiInformationen
	void speichereDateiInformationen(DateiInformationen dateiInfo) throws IOException;

}
