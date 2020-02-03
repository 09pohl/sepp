package sepp;

import java.util.ArrayList;

public interface DatenSchnittstelle {

	String getProjektPfad();
	Einstellungen getEinstellungen();
	ArrayList<String> getDateiNamen();
	DateiInformationen getDateiInformationen(String dateiName);	
}
