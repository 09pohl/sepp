package de.verbund.sepp.gui.dialoge;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChangeSourceDlg extends JDialog {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel oldDirectoryLabel;
	private JLabel oldDirectoryNameLabel;
	private JPanel panel_2;
	private JButton acceptButton;
	private JPanel panel_3;
	private JLabel newDirectoryLabel;
	private JTextField newDirectoryTf;
	private JButton chooseButton;

	public ChangeSourceDlg(JFrame frame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Quellverzeichnis wechseln...");
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.SOUTH);
			panel.add(getPanel_3(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getOldDirectoryLabel());
			panel_1.add(getOldDirectoryNameLabel());
		}
		return panel_1;
	}
	private JLabel getOldDirectoryLabel() {
		if (oldDirectoryLabel == null) {
			oldDirectoryLabel = new JLabel("Bisheriges Verzeichnis:");
		}
		return oldDirectoryLabel;
	}
	public JLabel getOldDirectoryNameLabel() {
		if (oldDirectoryNameLabel == null) {
			oldDirectoryNameLabel = new JLabel("*/*old_directory*/*");
		}
		return oldDirectoryNameLabel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getAcceptButton());
		}
		return panel_2;
	}
	public JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton("Bestätigen");
		}
		return acceptButton;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getNewDirectoryLabel());
			panel_3.add(getNewDirectoryTf());
			panel_3.add(getChooseButton());
		}
		return panel_3;
	}
	private JLabel getNewDirectoryLabel() {
		if (newDirectoryLabel == null) {
			newDirectoryLabel = new JLabel("Neues Verzeichnis:");
		}
		return newDirectoryLabel;
	}
	public JTextField getNewDirectoryTf() {
		if (newDirectoryTf == null) {
			newDirectoryTf = new JTextField();
			newDirectoryTf.setColumns(30);
		}
		return newDirectoryTf;
	}
	public JButton getChooseButton() {
		if (chooseButton == null) {
			chooseButton = new JButton("Durchsuchen...");
		}
		return chooseButton;
	}
}
