package de.verbund.sepp.main.daten;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.verbund.sepp.main.utils.DateiHelfer;

public class DatenSchnittstelleImpl implements DatenSchnittstelle {

	private static DatenSchnittstelleImpl instance;

	private DatenSchnittstelleImpl() {
	}

	public static DatenSchnittstelleImpl getInstance() {
		if (instance == null) {
			instance = new DatenSchnittstelleImpl();
		}
		return instance;
	}

	@Override
	public Einstellungen getEinstellungen() throws IOException {
		return Einstellungen.getInstance();
	}

	@Override
	public ArrayList<String> getDateiPfade(Einstellungen einstellungen) throws IOException {
		ArrayList<String> pfadListe = new ArrayList<String>();
		File projektOrdner = new File(einstellungen.projektPfad);
		DateiHelfer.dateienInOrdnerSepp(projektOrdner, pfadListe);
		return pfadListe;
	}

	@Override
	public DateiInformationen getDateiInformationen(String dateiPfad) throws IOException {
		DateiInformationen dateiInfo = new DateiInformationen();
		DateiHelfer datei = new DateiHelfer(dateiPfad);
		Path pfad = Paths.get(dateiPfad);
		dateiInfo.setIcon(datei.icon());
		dateiInfo.setPfad(pfad);
		dateiInfo.setName(pfad.getFileName().toString());
		dateiInfo.setErstellungsDatum(datei.basisInformationen().creationTime());
		dateiInfo.setAenderungsDatum(datei.basisInformationen().lastModifiedTime());

		DateiHelfer dateiTodo = new DateiHelfer(dateiPfad + DateiInformationen.DATEIENDUNG_TODOS);
		if (dateiTodo.existiert()) {
			dateiInfo.setToDos(dateiTodo.lese());
		} else {
			dateiTodo.schreibe("");
			try {
				dateiTodo.setHidden(true);
			} catch (Exception e) {
				getError();
			}
		}

		DateiHelfer dateiKommentare = new DateiHelfer(dateiPfad + DateiInformationen.DATEIENDUNG_KOMMENTARE);
		if (dateiKommentare.existiert()) {
			dateiInfo.setKommentare(dateiKommentare.lese());
		} else {
			dateiKommentare.schreibe("");
			try {
				dateiKommentare.setHidden(true);
			} catch (Exception e) {
				getError();
			}
		}
		return dateiInfo;
	}

	@Override
	public void speichereDateiInformationen(DateiInformationen dateiInfo) throws IOException {
		DateiHelfer dateiTodo = new DateiHelfer(dateiInfo.getPfad() + DateiInformationen.DATEIENDUNG_TODOS);
		dateiTodo.setHidden(false);
		dateiTodo.schreibe(dateiInfo.getToDos());
		dateiTodo.setHidden(true);
		DateiHelfer dateiKommentare = new DateiHelfer(dateiInfo.getPfad() + DateiInformationen.DATEIENDUNG_KOMMENTARE);
		dateiKommentare.setHidden(false);
		dateiKommentare.schreibe(dateiInfo.getKommentare());
		dateiKommentare.setHidden(true);
	}
	
	private void getError() {
		JOptionPane.showMessageDialog(null, "Datei konnte nicht erstellt werden!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
	}
}
