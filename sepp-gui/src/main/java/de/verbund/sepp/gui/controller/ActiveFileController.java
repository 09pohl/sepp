package de.verbund.sepp.gui.controller;

import java.io.IOException;

import javax.swing.JLabel;

import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiHelfer;

public class ActiveFileController {

	private DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
	private String aktiveDateiPfad;
	private JLabel ativeDateiLabel;

	private static ActiveFileController instanz;

	private ActiveFileController() throws IOException {
		aktiveDateiPfad = schnittstelle.getEinstellungen().getProjektDateiPfad();
	}

	public static ActiveFileController getInstanz() throws IOException {
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
		if (ativeDateiLabel != null) {
			DateiHelfer datei = new DateiHelfer(aktiveDateiPfad);
			ativeDateiLabel.setText(datei.nameMitEndung());
		}
	}

	public JLabel getAktiveDateiLabel() {
		return ativeDateiLabel;
	}

	public void setAktiveDateiLabel(JLabel ativeDateiLabel) {
		this.ativeDateiLabel = ativeDateiLabel;
	}

	public void refreshLabel() {
		if (ativeDateiLabel != null) {
			DateiHelfer datei = new DateiHelfer(aktiveDateiPfad);
			ativeDateiLabel.setText(datei.nameMitEndung());
		}
	}
}
