package todo.comment;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sepp.daten.DateiInformationen;
import sepp.utils.DateiInfoHelfer;

public class ToDoBox extends JPanel{
	
	private JTable doneTable;
	private DateiInformationen data;
	private JScrollPane scrollPane;
	
	public ToDoBox() {
		createToDoBox();
	}

	private void createToDoBox() {
		
		setSize(500, 300);
		scrollPane = new JScrollPane(createTable());
		add(scrollPane);
		setVisible(true);

	}
	
	private JTable createTable() {
		data = new DateiInformationen();
		data.setToDos("Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le");
		String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(data.getToDos());
		String[] columns = {"User", "To Do's"};
		TableModel model = new DefaultTableModel(userAndToDos, columns);
		doneTable = new JTable(model);
		return doneTable;
	}
}


