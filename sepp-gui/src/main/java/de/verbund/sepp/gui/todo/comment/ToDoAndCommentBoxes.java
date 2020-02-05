package de.verbund.sepp.gui.todo.comment;

import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class ToDoAndCommentBoxes {

	public static final String[] spaltenKommentare = { "User", "Kommentar" };
	public static final String[] spaltenTodos = { "User", "To-Do's" };

	private DatenSchnittstelleImpl dataSchnittstelle;

	private JTable tableComment;
	private JTable tableToDo;

	private JScrollPane initCommentBox() {
		return new JScrollPane(createCommentTable());
	}

	private JScrollPane initToDoBox() {
		return new JScrollPane(createToDoTable());
	}

	private JTable createCommentTable() {
		dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen dataComments;

		try {
			dataComments = dataSchnittstelle
					.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
			String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
			tableComment = gefuellteTabelle(userAndComments, spaltenKommentare);
			return tableComment;
		} catch (IOException e) {
			// TODO #61 sysout entfernen
			System.out.println("Fehler Comments");
			return null;
		}
	}

	private JTable createToDoTable() {
		dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen dataToDos;

		try {
			dataToDos = dataSchnittstelle
					.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektDateiPfad());
			String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
			tableToDo = gefuellteTabelle(userAndToDos, spaltenTodos);
			return tableToDo;
		} catch (IOException e) {
			// TODO #61 sysout entfernen
			System.out.println("Fehler ToDo's");
			return null;
		}
	}

	private JTable gefuellteTabelle(String[][] inhalte, String[] spaltenTitel) {
		TableModel model = new DefaultTableModel(inhalte, spaltenTitel);
		JTable doneTable = new TableAndPopUpMenu(model).getTable();
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
}
