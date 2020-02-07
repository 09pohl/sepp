package de.verbund.sepp.gui.todo.comment;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FullTextAndEdit extends JDialog {

	
	public FullTextAndEdit(int toDifferentTables, JTable table) {
		createJDialog(toDifferentTables, table);

	}

	private void createJDialog(int toDifferentTables, JTable table) {
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		add(northPanel(), BorderLayout.NORTH);
		add(centralPanel(table), BorderLayout.CENTER);
		add(southPanel(), BorderLayout.SOUTH);

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
		northPanel.add(new JLabel("Betreff:"));
		northPanel.add(new JTextField(10));
		return northPanel;
	}

	private Component centralPanel(JTable table) {
		JPanel centralPanel = new JPanel();
		JEditorPane editor = new JEditorPane();
		editor.setSize(300, 200);
		editor.setPreferredSize(new Dimension(300,95));
		editor.setText((table.getValueAt(table.getSelectedRow(), 1).toString()));
		JScrollPane scrollPane = new JScrollPane(editor);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		centralPanel.add(scrollPane);
		return centralPanel;
	}

	private Component southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(new JButton("OK"));
		southPanel.add(new JButton("Abbrechen"));
		return southPanel;
	}
}
