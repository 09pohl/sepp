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
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;

public class FullTextAndEdit extends JDialog {

	private JTable table;
	private JEditorPane editor;
	private SEPPMainDlg seppMainDlg;
	private DatenSchnittstelle datenSchnittstelle = DatenSchnittstelleImpl.getInstance();
	private static int row;

	public FullTextAndEdit(int toDifferentTables, JTable table, SEPPMainDlg seppMainDlg, int call) {
		this.table = table;
		checkCallSituation(call);

		System.out.println(row);
		this.seppMainDlg = seppMainDlg;
		try {
			createJDialog(toDifferentTables, table, call);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Fehler: Editieren nicht möglich", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void createJDialog(int toDifferentTables, JTable table, int call) throws IOException {
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(northPanel(toDifferentTables), BorderLayout.NORTH);
		add(centralPanel(table, call), BorderLayout.CENTER);
		add(southPanel(toDifferentTables), BorderLayout.SOUTH);

		String user = table.getValueAt(table.getSelectedRow(), 0).toString();

		if (toDifferentTables == 0) {
			setTitle("Kommentar [Autor: " + user + "]");
		} else {
			setTitle("To-do [Autor: " + user + "]");
		}

		setVisible(true);

	}

	private Component northPanel(int toDifferentTables) {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton nextComment = new JButton("->");
		JButton previousComment = new JButton("<-");
		nextComment.addActionListener(e -> next(toDifferentTables, 1));
		previousComment.addActionListener(e -> next(toDifferentTables, -1));
		northPanel.add(new JLabel("Bearbeiten:")); // statt Betreff:
		northPanel.add(previousComment);
		northPanel.add(nextComment);
//		northPanel.add(new JTextField(10));
		return northPanel;
	}

	private void next(int toDifferentTables, int call) {
		dispose();
		new FullTextAndEdit(toDifferentTables, table, seppMainDlg, call);

	}

	private Component centralPanel(JTable table, int call) {
		JPanel centralPanel = new JPanel();
		editor = new JEditorPane();
		editor.setSize(300, 200);
		editor.setPreferredSize(new Dimension(300, 95));
		editor.setText((table.getValueAt(row, 1).toString()));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();
	}

	private void checkCallSituation(int call) {
		if (call == 0) {
			row = table.getSelectedRow();
			System.out.println("0");
		} else {
			if (row == table.getRowCount() - 1 && call == 1) {
				row = 0;
			} else if (row == 0 && call == -1) {
				row = table.getRowCount() - 1;
			} else if (row != 0 && call == 1) {
				row += 1;
			} else if (row != 0 && call == -1) {
				row -= 1;
			} else if (row == 0 && call == 1) {
				row += 1;
			}

		}

	}
}
