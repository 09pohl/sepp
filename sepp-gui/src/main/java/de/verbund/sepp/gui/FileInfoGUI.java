package de.verbund.sepp.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import de.verbund.sepp.gui.controller.DateiViewController;
import de.verbund.sepp.gui.dialoge.DateiGUI;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class FileInfoGUI extends JPanel {

	private Dimension dimension = new Dimension(200, 135);
	private DateiInfoHelfer helferlein = new DateiInfoHelfer();
	private String name;
	
	private static FileInfoGUI instance;
	
	public static FileInfoGUI getInstance() {
		DateiViewController dc = new DateiViewController();
		if (instance == null) {
			instance = new FileInfoGUI(dc.getFiles());
		}
		return instance;
	}

	public FileInfoGUI(List<String> files) {
		setLayout(new GridLayout(0, 1, 30, 0));
		for (String f : files) {
			try {
				name = helferlein.nameMitUnterordner(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				DateiGUI datei = new DateiGUI(f, name);
				datei.setPreferredSize(dimension);
				datei.setMinimumSize(dimension);
				datei.setMaximumSize(dimension);
				add(datei);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
