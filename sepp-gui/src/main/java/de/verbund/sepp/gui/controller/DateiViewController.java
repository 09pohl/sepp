package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import de.verbund.sepp.gui.DummyFileInfoGUI;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class DateiViewController {
	
	private DummyFileInfoGUI infoGUI;
	private DatenSchnittstelle data = new DatenSchnittstelleImpl();
	private File directory;
	
	public DateiViewController() {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
					init();

			}
		});
	}

	public JPanel init(){
		try {
			directory = new File(data.getEinstellungen().getProjektPfad());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int filecount = directory.list().length;
		infoGUI = new DummyFileInfoGUI(filecount);
		infoGUI.setVisible(true);
		return infoGUI;
	}

}
