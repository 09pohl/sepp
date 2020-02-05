package de.verbund.sepp.gui.controller;

import java.io.IOException;

import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class ActiveFileController {

	private DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
	private String aktiveDateiPfad;

	private static ActiveFileController instanz;

	private ActiveFileController() throws IOException {
		aktiveDateiPfad = schnittstelle.getEinstellungen().getProjektDateiPfad();
	}

	public static ActiveFileController getInstanze() throws IOException {
		if (ActiveFileController.instanz == null) {
			ActiveFileController.instanz = new ActiveFileController();
		}
		return ActiveFileController.instanz;
	}

	public String getAktiveDateiPfad() {
		return aktiveDateiPfad;
	}

	public void setAktiveDateiPfad(String aktiveDateiPfad) {
		this.aktiveDateiPfad = aktiveDateiPfad;
	}
}
