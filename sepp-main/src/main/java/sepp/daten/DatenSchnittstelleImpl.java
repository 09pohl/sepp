package sepp.daten;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sepp.utils.DateiHelfer;

public class DatenSchnittstelleImpl implements DatenSchnittstelle {

	@Override
	public Einstellungen getEinstellungen() {
		// TODO Auto-generated method stub
		return null;
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
		dateiInfo.setName(pfad.getFileName().toString());
		dateiInfo.setErstellungsDatum(datei.basisInformationen().creationTime());
		dateiInfo.setAenderungsDatum(datei.basisInformationen().lastModifiedTime());
		DateiHelfer dateiTodo = new DateiHelfer(dateiPfad);
		DateiHelfer dateiKommentare = new DateiHelfer(dateiPfad);
		return null;
	}

	@Override
	public void speichereDateiInformationen(DateiInformationen dateiInfo) {
		// TODO Auto-generated method stub

	}
}
