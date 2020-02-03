package sepp.daten;

import java.io.IOException;
import java.util.ArrayList;

public interface DatenSchnittstelle {

	// Enthält Benutzername und Projektpfad
	Einstellungen getEinstellungen();

	// Gibt eine Liste aller Dateien des Projekts zurück, auch aus Unterordnern
	ArrayList<String> getDateiNamen();

	// Enthält Icon, Dateiname, Erstellungsdatum, Änderungsdatum, To-Dos und
	// Kommentare
	DateiInformationen getDateiInformationen(String dateiPfad) throws IOException;

	// speichert die DateiInformationen
	void speichereDateiInformationen(DateiInformationen dateiInfo);

}
