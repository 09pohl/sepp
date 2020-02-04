package de.verbund.sepp.main.daten;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import javax.swing.Icon;

public class DateiInformationen {

	public static final String DATEIENDUNG_TODOS = ".sepptodo";
	public static final String DATEIENDUNG_KOMMENTARE = ".seppkomm";

	private Icon icon;
	private String name;
	private Path pfad;
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

	public Path getPfad() {
		return pfad;
	}

	public void setPfad(Path pfad) {
		this.pfad = pfad;
	}

	public String getKommentareDatei() {
		return pfad + DATEIENDUNG_TODOS;
	}

	public String getToDosDatei() {
		return pfad + DATEIENDUNG_KOMMENTARE;
	}

}
