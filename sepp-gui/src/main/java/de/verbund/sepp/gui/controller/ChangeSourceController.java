package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.verbund.sepp.gui.SEPPMainDlg;
import de.verbund.sepp.gui.dialoge.ChangeSourceDlg;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.StringHelfer;

public class ChangeSourceController {

	private ChangeSourceDlg changeDlg;
	private DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
	private SEPPMainDlg seppMainDlg;
	private StringHelfer helferlein;

	public ChangeSourceController(JFrame frame, SEPPMainDlg seppMainDlg) {

		this.seppMainDlg = seppMainDlg;
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					initDialog(frame);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(changeDlg, "Wechseln des Projektverzeichnisses nicht möglich",
							"FEHLER", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	protected void initDialog(JFrame frame) throws IOException {
		changeDlg = new ChangeSourceDlg(frame);
		changeDlg.getRootPane().setDefaultButton(changeDlg.getChooseButton());
		changeDlg.getChooseButton().addActionListener(e -> chooseFolder());
		changeDlg.getAcceptButton().addActionListener(e -> saveSelection());
		changeDlg.getOldDirectoryNameLabel().setText(schnittstelle.getEinstellungen().getProjektPfad());
		changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		changeDlg.setSize(600, 130);
		changeDlg.setResizable(false);
		changeDlg.setModal(true);
		changeDlg.setLocationRelativeTo(frame);
		changeDlg.setVisible(true);
	}

	protected void saveSelection() {
		String change = changeDlg.getNewDirectoryTf().getText();
		if (!(helferlein.isNullEmptyOrWhitespace(change) || helferlein.isNullOrEmpty(change))) {
			try {
				schnittstelle.getEinstellungen().setProjektPfad(change);
				schnittstelle.getEinstellungen().speichern();
				ActiveFileController.getInstance()
					.setAktiveDateiPfad(schnittstelle.getEinstellungen().getProjektDateiPfad());
				ActiveFileController.getInstance().refreshLabel();
				seppMainDlg.refreshMainTables();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(changeDlg, "Pfad konnte nicht festgelegt werden!", "FEHLER!",
						JOptionPane.ERROR_MESSAGE);
			}
			changeDlg.dispose();
		} else {
			changeDlg.getNewDirectoryTf().setText("");
		}
	}

	private void chooseFolder() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(changeDlg);
		changeDlg.getNewDirectoryTf().setText(fc.getSelectedFile().getPath());
	}

}
