package de.verbund.sepp.gui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import de.verbund.sepp.main.daten.*;

public class DateiGUI extends JFrame implements ActionListener{

	private JButton bInfo;
	private static final String VERZEICHNIS = "C:\\java\\git\\sepp\\TestOrdner\\Aufgaben.rtf";
	
	DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
	DateiInformationen data = schnittstelle.getDateiInformationen(VERZEICHNIS);
	
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
		String datum_1 = data.getErstellungsDatum().toString();
		String datum_2 = data.getAenderungsDatum().toString();
		JLabel label_1 = new JLabel("Erstellungsdatum: " + datum_1);
		JLabel label_2 = new JLabel("Aenderungsdatum: " + datum_2);
		p.add(label_1);
		p.add(label_2);
		return p;
	}
	
	//Laden von Button mit Verweis auf ToDo - und Kommentarliste
	private Component getSueden(){
		JPanel p = new JPanel();
		bInfo = new JButton("ToDo - & Kommenatarliste");
		bInfo.addActionListener(this);
		p.add(bInfo);
		return p;
	}

	public static void main(String[] args)
	  {
	    SwingUtilities.invokeLater(new Runnable(){
	      public void run(){
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
		if (e.getSource() == bInfo) {
			//TODO: Load TODO-List & Kommenaterliste;
		}
	}
}
