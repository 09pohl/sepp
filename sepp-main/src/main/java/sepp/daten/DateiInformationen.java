package sepp.daten;

import java.time.LocalDateTime;

import javax.swing.Icon;

public class DateiInformationen {

	private final String DATEIENDUNG_TODOS = ".sepptodo";
	private final String DATEIENDUNG_KOMMENTARE = ".seppkomm";

	private Icon icon;
	private String name;
	private LocalDateTime erstellungsDatum;
	private LocalDateTime aenderungsDatum;
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

	public LocalDateTime getErstellungsDatum() {
		return erstellungsDatum;
	}

	public void setErstellungsDatum(LocalDateTime erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public LocalDateTime getAenderungsDatum() {
		return aenderungsDatum;
	}

	public void setAenderungsDatum(LocalDateTime aenderungsDatum) {
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
