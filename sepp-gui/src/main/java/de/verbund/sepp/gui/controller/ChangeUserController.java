package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.verbund.sepp.gui.dialoge.ChangeUserDlg;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class ChangeUserController {

	private ChangeUserDlg changeDlg;
	private DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();

	public ChangeUserController(JFrame frame) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					initDialog(frame);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(changeDlg, "Wechseln des Benutzernamens nicht möglich!", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	protected void initDialog(JFrame frame) throws IOException {
		changeDlg = new ChangeUserDlg(frame);
		changeDlg.getRootPane().setDefaultButton(changeDlg.getAcceptButton());
		changeDlg.getAcceptButton().addActionListener(e -> saveUserName());
		changeDlg.getOldUserNameLabel().setText(schnittstelle.getEinstellungen().getUsername());
		changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		changeDlg.setSize(330, 110);
		changeDlg.setResizable(false);
		changeDlg.setModal(true);
		changeDlg.setLocationRelativeTo(frame);
		changeDlg.setVisible(true);
	}

	protected void saveUserName() {
		String change = changeDlg.getNewUserNameTf().getText();
		if (!(change.contains(":"))) {
			try {
				schnittstelle.getEinstellungen().setUsername(change);
				schnittstelle.getEinstellungen().speichern();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(changeDlg, "Benutzername konnte nicht festgelegt werden!", "FEHLER!",
						JOptionPane.ERROR_MESSAGE);
			}
			changeDlg.dispose();
		} else {
			JOptionPane.showMessageDialog(changeDlg, "Der Benutzername darf keinen Doppelpunkt enthalten!", "FEHLER!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
