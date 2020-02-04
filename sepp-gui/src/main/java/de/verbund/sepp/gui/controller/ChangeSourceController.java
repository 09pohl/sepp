package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.verbund.sepp.gui.dialoge.ChangeSourceDlg;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class ChangeSourceController {

	private ChangeSourceDlg changeDlg;
	private DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();

	public ChangeSourceController(JFrame frame) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				initDialog(frame);
			}
		});

	}

	protected void initDialog(JFrame frame) {
		changeDlg = new ChangeSourceDlg(frame);
		changeDlg.getRootPane().setDefaultButton(changeDlg.getChooseButton());
		changeDlg.getChooseButton().addActionListener(e -> chooseFolder());
		changeDlg.getEnterButton().addActionListener(e -> saveSelection());
		changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		changeDlg.setSize(600, 100);
		changeDlg.setResizable(false);
		changeDlg.setModal(true);
		changeDlg.setLocationRelativeTo(frame);
		changeDlg.setVisible(true);
	}

	protected void saveSelection() {
		String change = changeDlg.getTextField().getText();
		try {
			schnittstelle.getEinstellungen().setProjektPfad(change);
			schnittstelle.getEinstellungen().speichern();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(changeDlg, "Pfad konnte nicht festgelegt werden!", "FEHLER!",
					JOptionPane.ERROR_MESSAGE);
		}
		changeDlg.dispose();
	}

	private void chooseFolder() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(changeDlg);
		changeDlg.getTextField().setText(fc.getSelectedFile().getPath());
	}

}
