package sepp.daten;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sepp.utils.DateiHelfer;

public class DatenSchnittstelleImpl implements DatenSchnittstelle {

	@Override
	public Einstellungen getEinstellungen() throws IOException {
		return Einstellungen.getInstance();
	}

	@Override
	public ArrayList<String> getDateiNamen() {
		// TODO Auto-generated method stub
		return null;
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
		DateiHelfer dateiKommentare = new DateiHelfer(dateiPfad + DateiInformationen.DATEIENDUNG_KOMMENTARE);
		leseSeppDateien(dateiTodo, dateiInfo);
		leseSeppDateien(dateiKommentare, dateiInfo);
		return dateiInfo;
	}

	@Override
	public void speichereDateiInformationen(DateiInformationen dateiInfo) throws IOException {
		DateiHelfer dateiTodo = new DateiHelfer(dateiInfo.getPfad() + DateiInformationen.DATEIENDUNG_TODOS);
		dateiTodo.schreibe(dateiInfo.getToDos());
		DateiHelfer dateiKommentare = new DateiHelfer(dateiInfo.getPfad() + DateiInformationen.DATEIENDUNG_KOMMENTARE);
		dateiKommentare.schreibe(dateiInfo.getKommentare());
	}

	private void leseSeppDateien(DateiHelfer datei, DateiInformationen dateiInfo) throws IOException {
		if (datei.existiert()) {
			dateiInfo.setToDos(datei.lese());
		} else {
			datei.schreibe("");
		}
	}
}
