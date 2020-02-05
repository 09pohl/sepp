package de.verbund.sepp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.verbund.sepp.gui.dialoge.DateiGUI;

public class DummyFileInfoGUI extends JPanel{
	
	private Dimension dimension = new Dimension(200, 100);
	
	public DummyFileInfoGUI(int filecount) {
		setLayout(new GridLayout(0, 1));
		for (int i=1; i<=filecount; i++) {
			try {
				DateiGUI datei = new DateiGUI();
				datei.setPreferredSize(dimension);
				datei.setMinimumSize(dimension);
				datei.setMaximumSize(dimension);
				add(datei);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			JPanel p = new JPanel(new BorderLayout());
//			p.add(new JLabel("Panel " + i), BorderLayout.NORTH);
//			p.add(new JButton(""+i), BorderLayout.SOUTH);
//			p.setPreferredSize(dimension);
//			p.setMinimumSize(dimension);
//			p.setMaximumSize(dimension);
//			add(p);
//			setVisible(true);
		}
	}
}
