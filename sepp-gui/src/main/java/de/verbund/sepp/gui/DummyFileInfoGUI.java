package de.verbund.sepp.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DummyFileInfoGUI extends JPanel{
	
	public DummyFileInfoGUI(int filecount) {
		setLayout(new GridLayout(0, 1));
		for (int i=1; i<=filecount; i++) {
			JPanel p = new JPanel(new BorderLayout());
			p.add(new JLabel("Panel " + i), BorderLayout.NORTH);
			p.add(new JButton(""+i), BorderLayout.SOUTH);
			add(p);
		}
	}
}
