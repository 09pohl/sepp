package de.verbund.sepp.gui.dialoge;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.attribute.FileTime;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.daten.Einstellungen;
import de.verbund.sepp.main.utils.DatumHelfer;

public class DateiGUI extends JFrame implements ActionListener {

	Einstellungen einstellungen;
	private JButton bInfo;
	private String verzeichnis = einstellungen.getProjektDateiPfad(); // TODO - Dateiaufruf
	DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
	DateiInformationen data = schnittstelle.getDateiInformationen(verzeichnis);

	public DateiGUI() throws IOException {
		Container panel = getContentPane();
		setSize(350, 150);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		panel.setLayout(new BorderLayout());
		setDefaultLookAndFeelDecorated(true);
		setResizable(false);

		getContentPane().add(getNorden(), BorderLayout.NORTH);
		getContentPane().add(getMitte(), BorderLayout.CENTER);
		getContentPane().add(getSueden(), BorderLayout.SOUTH);
		setVisible(true);
	}

	// Laden von Icon und Namen
	private Component getNorden() {
		Icon icon = data.getIcon();
		String name = data.getName();
		JPanel p = new JPanel();
		JLabel i = new JLabel(icon);
		JLabel n = new JLabel(name);
		p.add(i);
		p.add(n);
		return p;
	}

	// Laden von Datum
	private Component getMitte() throws IOException {
		JPanel p = new JPanel();
		FileTime datum_1 = data.getErstellungsDatum();
		FileTime datum_2 = data.getAenderungsDatum();
		String s = DatumHelfer.fileTimeToString(datum_1);
		JLabel label_1 = new JLabel("Erstellungsdatum: " + s);
		String s1 = DatumHelfer.fileTimeToString(datum_2);
		JLabel label_2 = new JLabel("Aenderungsdatum: " + s1);
		p.add(label_1);
		p.add(label_2);
		return p;
	}

	// Laden von Button mit Verweis auf ToDo - und Kommentarliste
	private Component getSueden() {
		JPanel p = new JPanel();
		bInfo = new JButton("ToDo - & Kommentarliste");
		bInfo.addActionListener(this);
		p.add(bInfo);
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bInfo) {
			// TODO: Load TODO-List & Kommentarliste;
		}
	}

}
