package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import de.verbund.sepp.gui.dialoge.StartUpDlg;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.daten.Einstellungen;

public class StartUpController {
	
	private StartUpDlg startDlg;
	private DatenSchnittstelleImpl schnittstelle = new DatenSchnittstelleImpl();
	
	public StartUpController() {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				initStartUp();
			}
		});
	}

	protected void initStartUp() {
		try {
			Einstellungen settings = schnittstelle.getEinstellungen();
			try {
				settings.laden();
				System.out.println("Einstellungen vorhanden");
			} catch (FileNotFoundException e) {
				startDlg = new StartUpDlg();
				startDlg.getRootPane().setDefaultButton(startDlg.getSaveButton());
				startDlg.getChooseButton().addActionListener(e2 -> chooseDirectory());
				startDlg.getSaveButton().addActionListener(e2 -> {
					try {
						saveSettings();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.err.println("Fehler beim Speichern der Einstellungen!");
					}
				});
				startDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				startDlg.setSize(800, 150);
				startDlg.setResizable(false);
				startDlg.setModal(true);
				startDlg.setLocationRelativeTo(null);
				startDlg.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
					
				});
				startDlg.setVisible(true);
			}

		} catch (IOException e) {
			System.err.println("Fehler beim Einlesen bestehender Einstellungen!");
		}
	}

	private void saveSettings() throws IOException {
		String dir = startDlg.getDirectoryTf().getText();
		String user = startDlg.getUserNameTf().getText();
		if (dir.equals("") || user.equals("")) {
			System.out.println("Speicher nicht möglich!");
		}
		else {
			System.out.println("Projektverzeichnis: " + dir);
			System.out.println("Benutzername: " + user);
			schnittstelle.getEinstellungen().setProjektPfad(dir);
			schnittstelle.getEinstellungen().setUsername(user);
			schnittstelle.getEinstellungen().speichern();
			startDlg.dispose();
		}
	}

	private void chooseDirectory() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(startDlg);
		startDlg.getDirectoryTf().setText(fc.getSelectedFile().getPath());
	}

}
