package de.verbund.sepp.gui.todo.comment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.verbund.sepp.gui.SEPPMainDlg;

public class TableAndPopUpMenu {

	private JTable table;
	private PopUpFunction popUpFunction;
	private SEPPMainDlg seppMainDlg;

	public TableAndPopUpMenu(TableModel model, SEPPMainDlg seppMainDlg) {
		this.seppMainDlg = seppMainDlg;
		table = new JTable(model);
	}

	private JTable createTable(int toDifferentTables) {
		mouseListener(toDifferentTables, table);
		table.setEnabled(false);
		return table;
	}

	public JTable getTable(int toDifferentTables) {
		return createTable(toDifferentTables);

	}

	private void addOrEditJOP(AddOrEdit text, int rowindex, int toDifferentTables) {
		String comment = "";
		boolean goOn = true;

		while (goOn) {
			comment = JOptionPane.showInputDialog(null, text.getInfoText() + ":", text.getInfoText(),
					JOptionPane.OK_CANCEL_OPTION);
			try {
				if (!comment.replaceAll("\\s+", "").isEmpty()) {
					if (text.getInfoText().equals("Hinzufügen")) {
						try {
							popUpFunction.add(toDifferentTables, rowindex, comment, seppMainDlg);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Fehler beim Hinzufügen!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						try {
							popUpFunction.edit(toDifferentTables, rowindex, comment, seppMainDlg);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Fehler beim Bearbeiten!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
						}
					}
					goOn = false;
				}
			} catch (NullPointerException npe) {
				goOn = false;
			}
		}
	}

	enum AddOrEdit {
		ADD("Hinzufügen"), EDIT("Bearbeiten");

		private String infoText;

		AddOrEdit(String infoText) {
			this.infoText = infoText;
		}

		private String getInfoText() {
			return infoText;
		}

	}

	private void deleteJOP(int rowindex, int toDifferentTables) {

		int doDelete = JOptionPane.showConfirmDialog(null, "Wollen Sie diese Zeile wirklich löschen?", "Löschen",
				JOptionPane.YES_NO_OPTION);

		if (doDelete == JOptionPane.YES_OPTION) {
			popUpFunction.delete(toDifferentTables, rowindex, seppMainDlg);
		}
	}

	private void mouseListener(int toDifferentTables, JTable table) {
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
					JPopupMenu popup = createPopUpMenu(new JPopupMenu(), rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					new FullTextAndEdit(toDifferentTables, table, seppMainDlg, 0);
					e.consume();
				}
			}

			private JPopupMenu createPopUpMenu(JPopupMenu popup, int rowindex) {
				popup.add("Hinzufügen")
						.addActionListener(e -> addOrEditJOP(AddOrEdit.ADD, rowindex, toDifferentTables));
				popup.add("Editieren")
						.addActionListener(e -> addOrEditJOP(AddOrEdit.EDIT, rowindex, toDifferentTables));
				popup.add("Löschen").addActionListener(e -> deleteJOP(rowindex, toDifferentTables));
				popUpFunction = new PopUpFunction();
				return popup;
			}

		});
	}
}
