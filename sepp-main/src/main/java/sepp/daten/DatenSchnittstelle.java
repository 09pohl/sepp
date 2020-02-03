package sepp.daten;

import java.util.ArrayList;

public interface DatenSchnittstelle {

	// Enthält Benutzername und Projektpfad
	Einstellungen getEinstellungen();

	// Gibt eine Liste aller Dateien des Projekts zurück, auch aus Unterordnern
	ArrayList<String> getDateiNamen();

	// Enthält alle Zeilen (mit Benutzername und Text), Icon, Dateiname,
	// Erstellungsdatum, Änderungsdatum
	DateiInformationen getDateiInformationen(String dateiName);
}
