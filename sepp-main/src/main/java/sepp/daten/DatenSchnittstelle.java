package sepp.daten;

import java.io.IOException;
import java.util.ArrayList;

public interface DatenSchnittstelle {

<<<<<<< HEAD
	String getProjektPfad();

=======
	final String DATEINAME = "sepp";

	// Enthält Benutzername und Projektpfad
>>>>>>> refs/remotes/origin/sepp-15-todo_kommentar_gui
	Einstellungen getEinstellungen();

	ArrayList<String> getDateiNamen();

<<<<<<< HEAD
	DateiInformationen getDateiInformationen(String dateiName);
=======
	// Enthält Icon, Dateiname, Erstellungsdatum, Änderungsdatum, To-Dos und
	// Kommentare
	DateiInformationen getDateiInformationen(String dateiPfad) throws IOException;

	// speichert die DateiInformationen
	void speichereDateiInformationen(DateiInformationen dateiInfo);

>>>>>>> refs/remotes/origin/sepp-15-todo_kommentar_gui
}
