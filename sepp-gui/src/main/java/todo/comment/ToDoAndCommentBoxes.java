package todo.comment;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sepp.daten.DateiInformationen;
import sepp.utils.DateiInfoHelfer;

public class ToDoAndCommentBoxes {

	private DateiInformationen dataComments;
	private DateiInformationen dataToDos;

	private JScrollPane initCommentBox() {

		return new JScrollPane(createCommentTable());
	}

	private JScrollPane initToDoBox() {

		return new JScrollPane(createToDoTable());
	}

	private JTable createCommentTable() {
		dataComments = new DateiInformationen();
		dataComments.setKommentare("Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le");
		String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
		String[] columns = { "User", "Kommentar" };
		TableModel model = new DefaultTableModel(userAndComments, columns);
		JTable doneTable = new JTable(model);
		return doneTable;
	}

	private JTable createToDoTable() {
		dataToDos = new DateiInformationen();
		dataToDos.setToDos(
				"Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le\nDaniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le");
		String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
		String[] columns = { "User", "To Do's" };
		TableModel model = new DefaultTableModel(userAndToDos, columns);
		JTable doneTable = new JTable(model);
		return doneTable;
	}

	public JScrollPane getCommentBox() {
		return initCommentBox();
	}

	public JScrollPane getToDoBox() {
		return initToDoBox();
	}
}
