package todo.comment;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sepp.daten.DateiInformationen;
import sepp.utils.DateiHelfer;
import sepp.utils.DateiInfoHelfer;

public class ToDoAndCommentBoxes extends JPanel{
	
	private JTable doneTable;
	private DateiInformationen dataComments;
	private DateiInformationen dataToDos;
	private JScrollPane scrollPaneComments;
	private JScrollPane scrollPaneToDos;
	
	private JScrollPane initCommentBox() {
		setSize(500, 300);
		scrollPaneComments = new JScrollPane(createCommentTable());
		return scrollPaneComments;
		}
	
	private JScrollPane initToDoBox() {
		setSize(500, 300);
		scrollPaneComments = new JScrollPane(createToDoTable());
		return scrollPaneToDos;
		}
			
	private JTable createCommentTable() {
		dataComments = new DateiInformationen();
		dataComments.setKommentare("Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le");
		String[][] userAndComments = DateiInfoHelfer.getZeilenArray(dataComments.getKommentare());
		String[] columns = {"User", "Kommentar"};
		TableModel model = new DefaultTableModel(userAndComments, columns);
		doneTable = new JTable(model);
		return doneTable;
	}
	
	private JTable createToDoTable() {
		dataToDos = new DateiInformationen();
		dataToDos.setToDos("Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le\nDaniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le\nDuong:Le");
		String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(dataToDos.getToDos());
		String[] columns = {"User", "To Do's"};
		TableModel model = new DefaultTableModel(userAndToDos, columns);
		doneTable = new JTable(model);
		return doneTable;
	}
	
	public JScrollPane getCommentBox(){
		return initCommentBox();
	}
	public JScrollPane getToDoBox(){
		return initToDoBox();
	}
}
