package de.verbund.sepp.gui.todo.comment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableAndPopUpMenu {

	private JTable table;

	public TableAndPopUpMenu(TableModel model) {
		table = new JTable(model);
	}

	private JTable createTable() {

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = createPopUpMenu(new JPopupMenu());
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			private JPopupMenu createPopUpMenu(JPopupMenu popup) {
				popup.add("Hinzufügen").addActionListener(e -> addOrEditJOP(AddOrEdit.ADD));
				popup.add("Editieren").addActionListener(e -> addOrEditJOP(AddOrEdit.EDIT));
				popup.add("Löschen").addActionListener(e -> deleteJOP());
				return popup;
			}
		});
		table.setEnabled(false);
		return table;
	}

	public JTable getTable() {
		return createTable();

	}

	private void addOrEditJOP(AddOrEdit text) {
		
		String comment = "";
		boolean goOn = true;

		while (goOn) {
			comment = JOptionPane.showInputDialog(null, text.getInfoText() + ":", text.getInfoText(), JOptionPane.OK_CANCEL_OPTION);
			try {
				if (!comment.replaceAll("\\s+", "").isEmpty()) {
					System.out.println(comment);
					goOn = false;
					//-->Funktionen
				}
			} catch (NullPointerException npe) {
				goOn = false;
			}
		}
	}
	
	enum AddOrEdit {
		ADD("Hinzufügen"), EDIT("Bearbeiten");
		private String infoText;
		
		AddOrEdit(String infoText){
			this.infoText = infoText;
		}
		
		private String getInfoText() {
			return infoText;
		}


	}

	private void deleteJOP() {
		int doDelete = JOptionPane.showConfirmDialog(null, "Wollen Sie diese Zeile wirklich löschen?", "Löschen",
				JOptionPane.YES_NO_OPTION);

		if (doDelete == JOptionPane.YES_OPTION) {
			System.out.println("DoDelete");
			//Funktion
		}
	}

}
