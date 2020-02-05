package de.verbund.sepp.gui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import de.verbund.sepp.gui.FileInfoGUI;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class DateiViewController extends JScrollPane{
	
	private FileInfoGUI infoGUI;
	private DatenSchnittstelle data = DatenSchnittstelleImpl.getInstance();
	private ArrayList<String> files;
	
	public DateiViewController() {
	}

	public JScrollPane init(){
		try {
			files = data.getDateiPfade(data.getEinstellungen());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoGUI = new FileInfoGUI(files);
		return new JScrollPane(infoGUI);
	}

}
