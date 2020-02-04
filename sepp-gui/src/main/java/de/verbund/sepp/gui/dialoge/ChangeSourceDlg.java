package de.verbund.sepp.gui.dialoge;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChangeSourceDlg extends JDialog{
	private JPanel panel;
	private JLabel lblNameDesNeuen;
	private JTextField textField;
	private JButton btnBesttigen;
	private JButton btnDurchsuchen;
	
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
			panel.add(getLblNameDesNeuen());
			panel.add(getTextField());
			panel.add(getEnterButton());
			panel.add(getChooseButton());
		}
		return panel;
	}
	private JLabel getLblNameDesNeuen() {
		if (lblNameDesNeuen == null) {
			lblNameDesNeuen = new JLabel("Name des neuen Verzeichnisses: ");
		}
		return lblNameDesNeuen;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(30);
		}
		return textField;
	}
	public JButton getEnterButton() {
		if (btnBesttigen == null) {
			btnBesttigen = new JButton("Bestätigen");
		}
		return btnBesttigen;
	}
	public JButton getChooseButton() {
		if (btnDurchsuchen == null) {
			btnDurchsuchen = new JButton("Durchsuchen");
		}
		return btnDurchsuchen;
	}
}
