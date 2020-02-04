package de.verbund.sepp.gui.dialoge;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StartUpDlg extends JDialog {
	private JPanel panel;
	private JLabel lblProjektverzeichnis;
	private JTextField directoryTf;
	private JButton chooseButton;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField userNameTf;
	private JButton saveButton;

	public StartUpDlg() {
		setTitle("Einstellungen");
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.NORTH);
		getContentPane().add(getPanel_1(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_2(), BorderLayout.CENTER);
		System.out.println("keine Einstellungen vorhanden");

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panel.add(getLblProjektverzeichnis());
			panel.add(getDirectoryTf());
			panel.add(getChooseButton());
		}
		return panel;
	}

	private JLabel getLblProjektverzeichnis() {
		if (lblProjektverzeichnis == null) {
			lblProjektverzeichnis = new JLabel("Projektverzeichnis:");
		}
		return lblProjektverzeichnis;
	}

	public JTextField getDirectoryTf() {
		if (directoryTf == null) {
			directoryTf = new JTextField();
			directoryTf.setColumns(40);
		}
		return directoryTf;
	}

	public JButton getChooseButton() {
		if (chooseButton == null) {
			chooseButton = new JButton("Durchsuchen...");
		}
		return chooseButton;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getSaveButton());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_2.add(getLblNewLabel());
			panel_2.add(getUserNameTf());
		}
		return panel_2;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Benutzername (Identität in Kommentaren und to Do´s):");
		}
		return lblNewLabel;
	}

	public JTextField getUserNameTf() {
		if (userNameTf == null) {
			userNameTf = new JTextField();
			userNameTf.setColumns(18);
		}
		return userNameTf;
	}

	public JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Einstellungen speichern");
		}
		return saveButton;
	}
}
