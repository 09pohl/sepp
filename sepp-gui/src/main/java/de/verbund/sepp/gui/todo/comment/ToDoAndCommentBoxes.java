package de.verbund.sepp.gui.todo.comment;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.verbund.sepp.gui.SEPPMainDlg;
import de.verbund.sepp.gui.controller.ActiveFileController;
import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class ToDoAndCommentBoxes {

	public static final String[] spaltenKommentare = { "User", "Kommentar" };
	public static final String[] spaltenTodos = { "User", "To Do's" };

	private DatenSchnittstelleImpl dataSchnittstelle;

	private JTable tableComment;
	private JTable tableToDo;
	static final int COMMENT = 0;
	private static final int TODO = 1;
	private SEPPMainDlg seppMainDlg;

	public ToDoAndCommentBoxes(SEPPMainDlg seppMainDlg) {
		this.seppMainDlg = seppMainDlg;
	}

	private JScrollPane initCommentBox() {
		return new JScrollPane(createCommentTable());
	}

	private JScrollPane initToDoBox() {
		return new JScrollPane(createToDoTable());
	}

	private JTable createCommentTable() {
		dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen dataInformation;

		try {
			dataInformation = dataSchnittstelle
					.getDateiInformationen(ActiveFileController.getInstance().getAktiveDateiPfad());
			String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataInformation.getKommentare());
			tableComment = gefuellteTabelle(userAndComments, spaltenKommentare, COMMENT);
			return tableComment;
		} catch (IOException e) {
			getError();
			return null;
		}
	}

	private JTable createToDoTable() {
		dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen dataInformation;

		try {
			dataInformation = dataSchnittstelle
					.getDateiInformationen(ActiveFileController.getInstance().getAktiveDateiPfad());
			String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataInformation.getToDos());
			tableToDo = gefuellteTabelle(userAndToDos, spaltenTodos, TODO);
			return tableToDo;
		} catch (IOException e) {
			getError();
			return null;
		}
	}

	JTable gefuellteTabelle(String[][] inhalte, String[] spaltenTitel, int toDifferentTables) {
		TableModel model = new DefaultTableModel(inhalte, spaltenTitel);
		JTable doneTable = new TableAndPopUpMenu(model, seppMainDlg).getTable(toDifferentTables);
		return doneTable;
	}

	public JScrollPane getCommentBox() {
		return initCommentBox();
	}

	public JScrollPane getToDoBox() {
		return initToDoBox();
	}

	public JTable getTableComment() {
		return tableComment;
	}

	public JTable getTableToDo() {
		return tableToDo;
	}

	private void getError() {
		JOptionPane.showMessageDialog(null, "Tabelle konnte nicht geladen werden!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
	}
}
