package sepp.gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class ChangeUserDlg extends JDialog{
	private JPanel panel;
	private JButton acceptButton;
	private JPanel panel_1;
	private JLabel oldUserLabel;
	private JLabel oldUserNameLabel;
	private JLabel newUserNameLabel;
	private JTextField newUserNameTf;
	
	public ChangeUserDlg(JFrame frame) {
		setTitle("Benutzernamen ändern...");
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getAcceptButton());
		}
		return panel;
	}
	JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton("Bestätigen");
		}
		return acceptButton;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			GridBagConstraints gbc_oldUserLabel = new GridBagConstraints();
			gbc_oldUserLabel.anchor = GridBagConstraints.EAST;
			gbc_oldUserLabel.insets = new Insets(0, 0, 5, 5);
			gbc_oldUserLabel.gridx = 4;
			gbc_oldUserLabel.gridy = 2;
			panel_1.add(getOldUserLabel(), gbc_oldUserLabel);
			GridBagConstraints gbc_oldUserNameLabel = new GridBagConstraints();
			gbc_oldUserNameLabel.anchor = GridBagConstraints.WEST;
			gbc_oldUserNameLabel.insets = new Insets(0, 0, 5, 0);
			gbc_oldUserNameLabel.gridx = 6;
			gbc_oldUserNameLabel.gridy = 2;
			panel_1.add(getOldUserNameLabel(), gbc_oldUserNameLabel);
			GridBagConstraints gbc_newUserNameLabel = new GridBagConstraints();
			gbc_newUserNameLabel.anchor = GridBagConstraints.EAST;
			gbc_newUserNameLabel.insets = new Insets(0, 0, 0, 5);
			gbc_newUserNameLabel.gridx = 4;
			gbc_newUserNameLabel.gridy = 4;
			panel_1.add(getNewUserNameLabel(), gbc_newUserNameLabel);
			GridBagConstraints gbc_newUserNameTf = new GridBagConstraints();
			gbc_newUserNameTf.anchor = GridBagConstraints.WEST;
			gbc_newUserNameTf.gridx = 6;
			gbc_newUserNameTf.gridy = 4;
			panel_1.add(getNewUserNameTf(), gbc_newUserNameTf);
		}
		return panel_1;
	}
	private JLabel getOldUserLabel() {
		if (oldUserLabel == null) {
			oldUserLabel = new JLabel("Bisheriger Benutzername:");
		}
		return oldUserLabel;
	}
	JLabel getOldUserNameLabel() {
		if (oldUserNameLabel == null) {
			oldUserNameLabel = new JLabel("*/*old_username*/*");
		}
		return oldUserNameLabel;
	}
	private JLabel getNewUserNameLabel() {
		if (newUserNameLabel == null) {
			newUserNameLabel = new JLabel("Neuer Benutzername:");
		}
		return newUserNameLabel;
	}
	JTextField getNewUserNameTf() {
		if (newUserNameTf == null) {
			newUserNameTf = new JTextField();
			newUserNameTf.setColumns(15);
		}
		return newUserNameTf;
	}
}
