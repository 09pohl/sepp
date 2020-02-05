package de.verbund.sepp.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.verbund.sepp.gui.dialoge.DateiGUI;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class FileInfoGUI extends JPanel{
	
	private Dimension dimension = new Dimension(200, 100);
	private DateiInfoHelfer helferlein = new DateiInfoHelfer();
	private String name;
	
	public FileInfoGUI(ArrayList<String> files) {
		setLayout(new GridLayout(0, 1, 30, 100));
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
