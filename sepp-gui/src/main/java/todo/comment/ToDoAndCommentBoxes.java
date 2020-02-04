package todo.comment;

import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.verbund.sepp.main.daten.DateiInformationen;
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
			dataComments = dataSchnittstelle.getDateiInformationen("C:\\Users\\Administrator\\git\\sepp\\TestOrdner\\projekt.sepp");
			String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
			String[] columns = { "User", "Kommentar" };
			TableModel model = new DefaultTableModel(userAndComments, columns);
			JTable doneTable = new JTable(model);
			return doneTable;
		} catch (IOException e) {
			System.out.println("Fehler Comments");
			return null;
		}
		
	}

	private JTable createToDoTable() {
		dataSchnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen dataToDos;
		
		try {
			dataToDos = dataSchnittstelle.getDateiInformationen("C:\\Users\\Administrator\\git\\sepp\\TestOrdner\\projekt.sepp");
			String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
			String[] columns = { "User", "To Do's" };
			TableModel model = new DefaultTableModel(userAndToDos, columns);
			JTable doneTable = new JTable(model);
			return doneTable;
		} catch (IOException e) {
			System.out.println("Fehler ToDo's");
			return null;
		}
	}

	public JScrollPane getCommentBox() {
		return initCommentBox();
	}

	public JScrollPane getToDoBox() {
		return initToDoBox();
	}
}
