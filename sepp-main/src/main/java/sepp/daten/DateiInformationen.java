package sepp.daten;

import java.nio.file.attribute.FileTime;

import javax.swing.Icon;

public class DateiInformationen {

	private final String DATEIENDUNG_TODOS = ".sepptodo";
	private final String DATEIENDUNG_KOMMENTARE = ".seppkomm";

	private Icon icon;
	private String name;
	private FileTime erstellungsDatum;
	private FileTime aenderungsDatum;
	private String toDos;
	private String kommentare;

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileTime getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(FileTime erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public FileTime getAenderungsDatum() {
		return aenderungsDatum;
	}

	public void setAenderungsDatum(FileTime aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}

	public String getToDos() {
		return toDos;
	}

	public void setToDos(String toDos) {
		this.toDos = toDos;
	}

	public String getKommentare() {
		return kommentare;
	}

	public void setKommentare(String kommentare) {
		this.kommentare = kommentare;
	}

}
