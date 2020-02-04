package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.verbund.sepp.gui.dialoge.ChangeUserDlg;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class ChangeUserController {
	
	private ChangeUserDlg changeDlg;
	private DatenSchnittstelleImpl schnittstelle = new DatenSchnittstelleImpl();
	
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
		changeDlg.getAcceptButton().addActionListener(e -> changeUserName());
		changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		changeDlg.setSize(330, 110);
		changeDlg.setResizable(false);
		changeDlg.setModal(true);
		changeDlg.setLocationRelativeTo(frame);
		changeDlg.setVisible(true);
	}

	protected void changeUserName() {
		String newName = changeDlg.getNewUserNameTf().getText();
		System.out.println("Neuer Username: " + newName);
		try {
			schnittstelle.getEinstellungen().setUsername(newName);
			schnittstelle.getEinstellungen().speichern();
		} catch (IOException e) {
			System.err.println("Fehler beim Festlegen des Benutzernamens");
		}
		changeDlg.dispose();
	}

}
