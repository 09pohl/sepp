package sepp.daten;

import java.util.ArrayList;

import javax.swing.Icon;

public class DateiInformationen {

	private Icon icon;
	private String name;
	private String erstellungsDatum;
	private String aenderungsDatum;

	public ArrayList<ToDo> getToDos(String dateiName) {
		return null;
	}

	public ArrayList<Kommentar> getKommentare(String dateiName) {
		return null;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public String getErstellungsDatum() {
		return erstellungsDatum;
	}

	public String getAenderungsDatum() {
		return aenderungsDatum;
	}
}
