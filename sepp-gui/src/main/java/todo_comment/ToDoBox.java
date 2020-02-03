package todo_comment;

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
	
	public ToDoBox() {
		createToDoBox();
	}

	private void createToDoBox() {
		
		data = new DateiInformationen();
		data.setToDos("Daniel:hallo\nLukas:hello\n");
		String[][] userAndToDos = DateiInfoHelfer.getZeilenArray(data.getToDos());
		String[] columns = {"User", "Kommentar"};
		
		TableModel model = new DefaultTableModel(userAndToDos, columns);
		doneTable = new JTable(model);
		add(doneTable);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ToDoBox();
	}
	
}


