package de.verbund.sepp.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class DateiGUI extends JFrame implements ActionListener {

	private JButton bTODO;
	private JButton bKommentare;
	private JButton bLoeschen;
	private static final String VERZEICHNIS = "C:\\java\\git\\sepp\\TestOrdner\\Aufgaben.rtf";

	public DateiGUI() throws IOException {
		Container panel = getContentPane();
		setSize(350, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(getTitel());
		panel.setLayout(new BorderLayout());
		JFrame.setDefaultLookAndFeelDecorated(true);
		setResizable(true);

		panel.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				setSize(new Dimension(350, getHeight()));
				super.componentResized(e);
			}

		});

		getContentPane().add(getMitte(), BorderLayout.CENTER);
		getContentPane().add(getSueden(), BorderLayout.SOUTH);
		removeMinMaxClose(panel);
		setVisible(true);

	}

	// Laden von Titel mit Name und Datum
	private String getTitel() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen data = schnittstelle.getDateiInformationen(VERZEICHNIS);
		String name = data.getName();
		String datum = data.getErstellungsDatum().toString();
		return name + datum;
	}

	// Laden von ToDOs und Kommentaren
	private Component getMitte() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen data = schnittstelle.getDateiInformationen(VERZEICHNIS);
		Icon icon = data.getIcon();
		JPanel p = new JPanel();
		JLabel label = new JLabel(icon);
		p.add(data.getAenderungsDatum().toString(), label);
		p.add(label);
		// TODO - User und Kommentare
		p.setLayout(new FlowLayout());
		return p;
	}

	// BorderLayout mit Buttons
	private Component getSueden() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		bTODO = new JButton("TODO-Liste");
		bTODO.addActionListener(this);
		bKommentare = new JButton("Kommentare");
		bKommentare.addActionListener(this);
		bLoeschen = new JButton("Loeschen"); // Loeschen von ToDo - Liste (WIP)
		bLoeschen.addActionListener(this);
		p.add(bTODO);
		p.add(bKommentare);
		p.add(bLoeschen);
		return p;
	}

	// Deaktivierung von Min-Max-Icon
	public void removeMinMaxClose(Component comp) {
		if (comp instanceof JButton) {
			String accName = ((JButton) comp).getAccessibleContext().getAccessibleName();
			// TODO #61 sysout entfernen
			System.out.println(accName);
			if (accName.equals("Maximize") || accName.equals("Iconify") || accName.equals("Close"))
				comp.getParent().remove(comp);
		}
		if (comp instanceof Container) {
			Component[] comps = ((Container) comp).getComponents();
			for (int x = 0, y = comps.length; x < y; x++) {
				removeMinMaxClose(comps[x]);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new DateiGUI();
				} catch (IOException e) {
					JOptionPane.showInputDialog("Failed to load!");
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bTODO) {
			// TODO: Load TODO-List
		}
		if (e.getSource() == bKommentare) {
			// TODO: Load Kommentare
		}
		if (e.getSource() == bLoeschen) {
			// TODO: Remove TODO-List & Kommentare
		}
	}
}
