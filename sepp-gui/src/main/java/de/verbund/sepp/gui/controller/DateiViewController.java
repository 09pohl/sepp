package de.verbund.sepp.gui.controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;

import de.verbund.sepp.gui.FileInfoGUI;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class DateiViewController extends JScrollPane {

	private FileInfoGUI infoGUI;
	private DatenSchnittstelle data = DatenSchnittstelleImpl.getInstance();
	private List<String> files;

	public DateiViewController() {
	}

	public JScrollPane init() {
		infoGUI = new FileInfoGUI(getFiles());
		return new JScrollPane(infoGUI);
	}

	public List<String> getFiles() {
		try {
			files = data.getDateiPfade(data.getEinstellungen());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return files;
	}

}
