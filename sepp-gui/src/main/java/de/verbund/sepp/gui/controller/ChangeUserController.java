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
				initDialog(frame);
			}
		});
	}

	protected void initDialog(JFrame frame) {
		changeDlg = new ChangeUserDlg(frame);
		changeDlg.getRootPane().setDefaultButton(changeDlg.getAcceptButton());
		changeDlg.getAcceptButton().addActionListener(e -> saveUserName());
		changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		changeDlg.setSize(330, 110);
		changeDlg.setResizable(false);
		changeDlg.setModal(true);
		changeDlg.setLocationRelativeTo(frame);
		changeDlg.setVisible(true);
	}

	protected void saveUserName() {
		String change = changeDlg.getNewUserNameTf().getText();
		try {
			schnittstelle.getEinstellungen().setUsername(change);
			schnittstelle.getEinstellungen().speichern();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(changeDlg, "Benutzername konnte nicht festgelegt werden!", "FEHLER!",
					JOptionPane.ERROR_MESSAGE);
		}
		changeDlg.dispose();
	}

}
