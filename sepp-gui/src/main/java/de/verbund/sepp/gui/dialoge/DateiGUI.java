package de.verbund.sepp.gui.dialoge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import de.verbund.sepp.gui.SEPPMainDlg;
import de.verbund.sepp.gui.controller.ActiveFileController;
import de.verbund.sepp.gui.controller.DateioeffnerController;
import de.verbund.sepp.main.utils.DatumHelfer;
import de.verbund.sepp.main.utils.HTMLHelfer;
import de.verbund.sepp.main.daten.*;

public class DateiGUI extends JPanel implements ActionListener {

	Einstellungen einstellungen;
	private JButton bInfo;
	private String name;
	private JFileChooser fc;
	DateioeffnerController controller;
	DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
	DateiInformationen data;

	public DateiGUI(String verzeichnis, String name) throws IOException {
		this.name = name;
		data = schnittstelle.getDateiInformationen(verzeichnis);
		setLayout(new BorderLayout());
		add(getNorden(), BorderLayout.NORTH);
		add(getMitte(), BorderLayout.CENTER);
		add(getSueden(), BorderLayout.SOUTH);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), name)));

		setVisible(true);
	}

	// Laden von Icon und Endung
	private Component getNorden() throws IOException {
		Icon fileimg = data.getIcon();
		fc = new JFileChooser();
		String file = schnittstelle.getEinstellungen().getProjektPfad();
		File fileconv = new File(file);
		Icon folderimg = fc.getUI().getFileView(fc).getIcon(fileconv);
		JPanel p = new JPanel();
		JLabel lOrdner = new JLabel(folderimg);
		JLabel lDatei = new JLabel(fileimg);
		
		lOrdner.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				String file;
				try {
					file = schnittstelle.getEinstellungen().getProjektPfad() + name;
					File file_conv = new File(file);
					DateioeffnerController.open(file_conv.getParentFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		lDatei.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String file;
					file = schnittstelle.getEinstellungen().getProjektPfad() + "/" + name;
					File file_conv = new File(file);
					DateioeffnerController.open(file_conv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		p.add(lOrdner);
		p.add(lDatei);
		return p;
	}

	// Laden von Datum
	private Component getMitte() throws IOException {
		JPanel p = new JPanel();
		FileTime datum_1 = data.getErstellungsDatum();
		FileTime datum_2 = data.getAenderungsDatum();
		String s = DatumHelfer.fileTimeToString(datum_1);
		String s1 = DatumHelfer.fileTimeToString(datum_2);
		JLabel label_1 = new JLabel(
				HTMLHelfer.OPEN + "Erstellungsdatum: " + s + "<br/>Änderungsdatum: " + s1 + HTMLHelfer.CLOSE);
		p.add(label_1);
		return p;
	}

	// Laden von Button mit Verweis auf ToDo - und Kommentarliste
	private Component getSueden() {
		JPanel p = new JPanel();
		bInfo = new JButton("To-Do & Kommentarliste");
		bInfo.addActionListener(this);
		p.add(bInfo);
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bInfo) {

			try {
				ActiveFileController.getInstance().setAktiveDateiPfad(data.getPfad().toString());
				SEPPMainDlg.getInstance().refreshMainTables();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// TODO #61
			System.out.println(data.getPfad());
		}	
	}
}
