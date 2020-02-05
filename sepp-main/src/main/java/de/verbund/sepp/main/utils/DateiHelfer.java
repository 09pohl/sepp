package de.verbund.sepp.main.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;

public class DateiHelfer {
	private String dateiName;

	public DateiHelfer(String dateiName) {
		this.dateiName = dateiName;
	}

	public static boolean dateiExistiert(String pfad) {
		File datei = new File(pfad);
		return datei.exists() && !datei.isDirectory();
	}

	public static String pfadOhneEndung(String pfad) {
		String result = pfad.substring(0, pfad.lastIndexOf('.'));
		return result;
	}

	public static String dateiEndung(String pfad) {
		String endung = pfad.substring(pfad.lastIndexOf('.') + 1);
		return endung;
	}

	public static String dateiEndungMitPunkt(String pfad) {
		String endung = pfad.substring(pfad.lastIndexOf('.'));
		return endung;
	}

	public static void dateienInOrdner(File ordner, ArrayList<String> pfadListe) throws IOException {
		for (File datei : ordner.listFiles()) {
			if (datei.isDirectory()) {
				dateienInOrdner(datei, pfadListe);
			} else {
				pfadListe.add(datei.getCanonicalPath());
			}
		}
	}

	public static void dateienInOrdnerSepp(File ordner, ArrayList<String> pfadListe) throws IOException {
		for (File datei : ordner.listFiles()) {
			if (datei.isDirectory()) {
				dateienInOrdner(datei, pfadListe);
			} else if (!DateiHelfer.dateiEndungMitPunkt(datei.getCanonicalPath())
					.equals(DateiInformationen.DATEIENDUNG_KOMMENTARE)
					&& !DateiHelfer.dateiEndungMitPunkt(datei.getCanonicalPath())
							.equals(DateiInformationen.DATEIENDUNG_TODOS)
					&& !DateiHelfer.dateiEndungMitPunkt(datei.getCanonicalPath())
							.equals(DatenSchnittstelle.PRIMAER_DATEIENDUNG)) {
				pfadListe.add(datei.getCanonicalPath());
			}
		}
	}

	public BasicFileAttributes basisInformationen() throws IOException {
		Path pfad = Paths.get(dateiName);
		BasicFileAttributes basic = Files.readAttributes(pfad, BasicFileAttributes.class);
		return basic;
	}

	public Icon icon() {
		Icon icon = FileSystemView.getFileSystemView().getSystemIcon(new File(dateiName));
		return icon;
	}

	public String nameMitEndung() {
		String name = Paths.get(dateiName).getFileName().toString();
		return name;
	}

	public boolean existiert() {
		File datei = new File(dateiName);
		return datei.exists() && !datei.isDirectory();
	}

	public void loesche() {
		File datei = new File(dateiName);
		datei.delete();
	}

	public void erstelleNeueDatei() throws IOException {
		File datei = new File(dateiName);
		datei.createNewFile();
	}

	public void schreibe(String text) throws IOException {
		schreibe(text, false);
	}

	public void schreibe(String txt, boolean append) throws IOException {
		File datei = null;
		datei = new File(dateiName);
		try (FileWriter outStream = new FileWriter(datei, append)) {

			outStream.write(txt);

		}
	}

	public String lese() throws IOException {
		StringBuffer inhalt = new StringBuffer();
		File datei = null;
		BufferedReader reader = null;
		// einlesen der Datei
		datei = new File(dateiName); // Erzeuge ein Datei-Objekt
		try (FileReader inStream = new FileReader(datei)) {
			reader = new BufferedReader(inStream);
			String zeile = "";
			while ((zeile = reader.readLine()) != null) // bis alles drin ist
			{
				if (inhalt.length() > 0) {
					inhalt.append("\n");
				}
				inhalt.append(zeile);
			}
		}
		return inhalt.toString();
	}
}
