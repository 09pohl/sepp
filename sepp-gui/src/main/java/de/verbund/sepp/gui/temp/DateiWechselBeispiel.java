package de.verbund.sepp.gui.temp;

import java.io.IOException;

import de.verbund.sepp.gui.controller.ActiveFileController;
import de.verbund.sepp.gui.dialoge.SEPPMainDlg;

// Zu #48

public class DateiWechselBeispiel {

	public static void main(String[] args) throws IOException {
		String pfadZurDatei = "Ich/Bin/Ein/Pfad";
		ActiveFileController.getInstance().setAktiveDateiPfad(pfadZurDatei); // Pfad zu der angeklickten Datei setzten
		SEPPMainDlg main = new SEPPMainDlg();
		main.refreshMainTables(); // Refresh durchführen
	}
}
