package de.verbund.sepp.gui.todo.comment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.verbund.sepp.gui.SEPPMainDlg;

public class FullTextAndEdit extends JDialog {

	private JTable table;
	private JEditorPane editor;
	private SEPPMainDlg seppMainDlg;
	
	public FullTextAndEdit(int toDifferentTables, JTable table, SEPPMainDlg seppMainDlg) {
		this.table = table;
		this.seppMainDlg = seppMainDlg;
		createJDialog(toDifferentTables, table);

	}

	private void createJDialog(int toDifferentTables, JTable table) {
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(northPanel(), BorderLayout.NORTH);
		add(centralPanel(table), BorderLayout.CENTER);
		add(southPanel(toDifferentTables), BorderLayout.SOUTH);

		if (toDifferentTables == 0) {
			setTitle("Kommentar");
		} else {
			setTitle("ToDo");
		}
		setVisible(true);
	}

	private Component northPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(new JLabel("Bearbeiten:")); //statt Betreff:
//		northPanel.add(new JTextField(10));
		return northPanel;
	}

	private Component centralPanel(JTable table) {
		JPanel centralPanel = new JPanel();
		editor = new JEditorPane();
		editor.setSize(300, 200);
		editor.setPreferredSize(new Dimension(300, 95));
		editor.setText((table.getValueAt(table.getSelectedRow(), 1).toString()));
		JScrollPane scrollPane = new JScrollPane(editor);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centralPanel.add(scrollPane);
		return centralPanel;
	}

	private Component southPanel(int toDifferentTables) {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Abbrechen");
		okButton.addActionListener(e -> edit(toDifferentTables));
		cancelButton.addActionListener(e -> cancel());
		southPanel.add(okButton);
		southPanel.add(cancelButton);
		return southPanel;
	}

	private void cancel() {
		dispose();
	}

	private void edit(int toDifferentTables) {
		PopUpFunction refresh = new PopUpFunction();
		String comment = editor.getText().replaceAll("\\r?\\n", " ");
		try {
			refresh.edit(toDifferentTables, table.getSelectedRow(), comment, seppMainDlg);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Aktualisieren!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
		}
		dispose();
	}
}
