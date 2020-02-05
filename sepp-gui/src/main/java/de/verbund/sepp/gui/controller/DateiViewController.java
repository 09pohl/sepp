package de.verbund.sepp.gui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import de.verbund.sepp.gui.DummyFileInfoGUI;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class DateiViewController extends JScrollPane{
	
	private DummyFileInfoGUI infoGUI;
	private DatenSchnittstelle data = new DatenSchnittstelleImpl();
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
		infoGUI = new DummyFileInfoGUI(files);
		return new JScrollPane(infoGUI);
	}

}
