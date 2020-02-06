package de.verbund.sepp.gui.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.verbund.sepp.gui.dialoge.StartUpDlg;
import de.verbund.sepp.main.daten.*;

public class StartUpController {

	private StartUpDlg startDlg;
	private DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();

	public StartUpController() {
		initStartUp();
	}

	protected void initStartUp() {
		try {
			Einstellungen settings = schnittstelle.getEinstellungen();
			try {
				settings.laden();
			} catch (FileNotFoundException e) {
				startDlg = new StartUpDlg();
				startDlg.getRootPane().setDefaultButton(startDlg.getSaveButton());
				startDlg.getChooseButton().addActionListener(e2 -> chooseDirectory());
				startDlg.getSaveButton().addActionListener(e2 -> {
					try {
						saveSettings();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(startDlg, "Fehler beim Speichern der Einstellungen!", "FEHLER!",
								JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(startDlg, "Fehler beim Einlesen bestehender Einstellungen!", "FEHLER!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveSettings() throws IOException {
		String dir = startDlg.getDirectoryTf().getText();
		String user = startDlg.getUserNameTf().getText();
		if (!("".equals(dir) || "".equals(user))) {
			if (!(user.contains(":"))) {
				// TODO #61 sysout entfernen
				System.out.println("Projektverzeichnis: " + dir);
				// TODO #61 sysout entfernen
				System.out.println("Benutzername: " + user);
				schnittstelle.getEinstellungen().setProjektPfad(dir);
				schnittstelle.getEinstellungen().setUsername(user);
				schnittstelle.getEinstellungen().speichern();
				startDlg.dispose();
			} else {
				JOptionPane.showMessageDialog(startDlg, "Der Benutzername darf keinen Doppelpunkt enthalten!",
						"FEHLER!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void chooseDirectory() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(startDlg);
		startDlg.getDirectoryTf().setText(fc.getSelectedFile().getPath());
	}

}
