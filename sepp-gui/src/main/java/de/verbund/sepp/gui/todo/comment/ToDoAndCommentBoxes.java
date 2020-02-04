package de.verbund.sepp.gui.todo.comment;

import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class ToDoAndCommentBoxes {

	private DatenSchnittstelleImpl dataSchnittstelle;

	private JScrollPane initCommentBox() {
		return new JScrollPane(createCommentTable());
	}

	private JScrollPane initToDoBox() {
		return new JScrollPane(createToDoTable());
	}

	private JTable createCommentTable() {
		dataSchnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen dataComments;

		try {
			dataComments = dataSchnittstelle.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad()
					+ "\\" + DatenSchnittstelle.PRIMAER_DATEINAME);
			String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
			String[] columns = { "User", "Kommentar" };
			return gefuellteTabelle(userAndComments, columns);
		} catch (IOException e) {
			System.out.println("Fehler Comments");
			return null;
		}

	}

	private JTable createToDoTable() {
		dataSchnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen dataToDos;

		try {
			dataToDos = dataSchnittstelle.getDateiInformationen(dataSchnittstelle.getEinstellungen().getProjektPfad()
					+ "\\" + DatenSchnittstelle.PRIMAER_DATEINAME);
			String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
			String[] columns = { "User", "To Do's" };
			return gefuellteTabelle(userAndToDos, columns);
		} catch (IOException e) {
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
}
