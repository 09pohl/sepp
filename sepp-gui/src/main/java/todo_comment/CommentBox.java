package todo_comment;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CommentBox extends JPanel{
	
	private JTable commentsAndUsernames;
	private JScrollPane layoutTable;
	private DateiInformationen data;
	
	
	public CommentBox() {
		fillTable();
	}
	
	
	
	private void fillTable() {
	
		data.
		
		String[] columns = {"User", "Kommentar"};
		
		String[][] testTable = new String [2][2];
		testTable[0][0] = "User1";
		testTable[0][1] = "User2";
		testTable[1][0] = "Kommentar1";
		testTable[1][1] = "Kommentar2";
		
		TableModel model = new DefaultTableModel(testTable, columns);
		
		commentsAndUsernames = new JTable(model);
		add(commentsAndUsernames);
		
		
		
		setVisible(true);
		
		}
			
	public static void main(String[] args) {
		new CommentBox();
	}
	
}
