package de.verbund.sepp.main.utils;

import java.io.IOException;
import java.util.ArrayList;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.daten.Einstellungen;

public class DateiInfoHelfer {

	public static String[][] getZeilenArray(String text) {
		String[][] result = null;
		if (text != null) {
			String[] zeilen = text.split("\\r?\\n");
			result = new String[zeilen.length][2];
			for (int i = 0; i < zeilen.length; i++) {
				result[i] = zeilen[i].split(":", 2);
			}
		}
		return result;
	}

	public static ArrayList<DateiInformationen> dateiInformationenListe(ArrayList<String> dateiPfade)
			throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		ArrayList<DateiInformationen> result = new ArrayList<DateiInformationen>();
		for (String pfad : dateiPfade) {
			result.add(schnittstelle.getDateiInformationen(pfad));
		}
		return result;
	}

	public static String nameMitUnterordner(String pfad) throws IOException {
		Einstellungen einstellungen = new DatenSchnittstelleImpl().getEinstellungen();
		String result = pfad.replace(einstellungen.getProjektPfad(), "");
		return result;
	}

}
