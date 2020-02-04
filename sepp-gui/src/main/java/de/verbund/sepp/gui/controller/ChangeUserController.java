package de.verbund.sepp.gui.controller;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import de.verbund.sepp.gui.dialoge.ChangeUserDlg;

public class ChangeUserController {
	
	private ChangeUserDlg changeDlg;
	
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
		changeDlg.dispose();
	}

}
