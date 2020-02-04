package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
			if (settings.getProjektPfad() == null && settings.getUsername() == null) {
				startDlg = new StartUpDlg();
				startDlg.getRootPane().setDefaultButton(startDlg.getSaveButton());
				startDlg.getChooseButton().addActionListener(e -> chooseDirectory());
				startDlg.getSaveButton().addActionListener(e -> saveSettings());
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
			} else {
				System.out.println("Einstellungen vorhanden");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveSettings() {
		String dir = startDlg.getDirectoryTf().getText();
		String user = startDlg.getUserNameTf().getText();
		if (dir.equals("") || user.equals("")) {
			System.out.println("Speicher nicht möglich!");
		}
		else {
			System.out.println("Projektverzeichnis: " + dir);
			System.out.println("Benutzername: " + user);
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
