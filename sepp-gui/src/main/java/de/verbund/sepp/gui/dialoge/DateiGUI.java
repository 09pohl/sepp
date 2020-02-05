package de.verbund.sepp.gui.dialoge;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.verbund.sepp.main.daten.*;

import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateiGUI extends JPanel implements ActionListener{
	
	DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
	Einstellungen einstellungen = schnittstelle.getEinstellungen();
	private JButton bInfo;
	private static final DateTimeFormatter DATE_FORMATTER_WITH_TIME = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss");
	private String verzeichnis = einstellungen.getProjektDateiPfad(); //TODO - Dateiaufruf
	DateiInformationen data = schnittstelle.getDateiInformationen(verzeichnis);
	
	public DateiGUI() throws IOException {
		//Container panel = getContentPane();
		setSize(350, 150);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		//setDefaultLookAndFeelDecorated(true);
	    //setResizable(false);
	    
	    add(getNorden(), BorderLayout.NORTH);
	    add(getMitte(), BorderLayout.CENTER);
	    add(getSueden(), BorderLayout.SOUTH);
	    setVisible(true); 
	  }


	//Laden von Icon und Namen
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

	//Laden von Datum
	private Component getMitte() throws IOException {
		JPanel p = new JPanel();
		FileTime datum_1 = data.getErstellungsDatum();
		FileTime datum_2 = data.getAenderungsDatum();
		String s = fileTimeToString(datum_1);
		JLabel label_1 = new JLabel("Erstellungsdatum: " + s);
		String s1 = fileTimeToString(datum_2);
		JLabel label_2 = new JLabel("Aenderungsdatum: " + s1);
		p.add(label_1);
		p.add(label_2);
		return p;
	}
	
	//Laden von Button mit Verweis auf ToDo - und Kommentarliste
	private Component getSueden(){
		JPanel p = new JPanel();
		bInfo = new JButton("ToDo - & Kommentarliste");
		bInfo.addActionListener(this);
		p.add(bInfo);
		return p;
	}
	
	//DateTimeFormatter
	public static String fileTimeToString(FileTime fileTime) {
	      String s = parseToString(
	              fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	      return s;
	}

	public static FileTime fileTimeFromString(String dateTimeString) {
	      LocalDateTime localDateTime = parseFromString(dateTimeString);
	      return FileTime.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static String parseToString(LocalDateTime localDateTime) {
	      return localDateTime.format(DATE_FORMATTER_WITH_TIME);
	}

	public static LocalDateTime parseFromString(String date) {
	      return LocalDateTime.parse(date, DATE_FORMATTER_WITH_TIME);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bInfo) {
			//TODO: Load TODO-List & Kommentarliste;
		}
	}
	
	
}
