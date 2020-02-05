package de.verbund.sepp.gui.controller;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiHelfer;

public class ActiveFileController {

	private DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
	private String aktiveDateiPfad;
	private JLabel lAktiveDatei;
	private JButton bZurHauptdatei;

	private static ActiveFileController instance;

	private ActiveFileController() throws IOException {
		aktiveDateiPfad = schnittstelle.getEinstellungen().getProjektDateiPfad();
	}

	public static ActiveFileController getInstance() throws IOException {
		if (instance == null) {
			instance = new ActiveFileController();
		}
		return instance;
	}

	public String getAktiveDateiPfad() {
		return aktiveDateiPfad;
	}

	public void setAktiveDateiPfad(String aktiveDateiPfad) throws IOException {
		this.aktiveDateiPfad = aktiveDateiPfad;
		if (lAktiveDatei != null) {
			DateiHelfer datei = new DateiHelfer(aktiveDateiPfad);
			lAktiveDatei.setText(datei.nameMitEndung());
			if (!schnittstelle.getEinstellungen().getProjektDateiPfad().equals(aktiveDateiPfad)
					&& bZurHauptdatei != null) {
				bZurHauptdatei.setVisible(true);
			} else {
				bZurHauptdatei.setVisible(false);
			}
		}
	}

	public JLabel getLAktiveDatei() {
		return lAktiveDatei;
	}

	public void setLAktiveDatei(JLabel ativeDateiLabel) {
		this.lAktiveDatei = ativeDateiLabel;
	}

	public void refreshLabel() {
		if (lAktiveDatei != null) {
			DateiHelfer datei = new DateiHelfer(aktiveDateiPfad);
			lAktiveDatei.setText(datei.nameMitEndung());
		}
	}

	public JButton getBZurHauptdatei() {
		return bZurHauptdatei;
	}

	public void setBZurHauptdatei(JButton bZurHauptdatei) {
		this.bZurHauptdatei = bZurHauptdatei;
	}
}
