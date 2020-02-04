package todo.comment;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class CommentBox extends JPanel {

	private JTable doneTable;
	private DateiInformationen data;
	private JScrollPane scrollPane;

	public CommentBox() {
		init();
	}

	private void init() {
		setSize(500, 300);
		scrollPane = new JScrollPane(createTable());
		add(scrollPane);
		setVisible(true);
	}

	private JTable createTable() {
		data = new DateiInformationen();
		data.setKommentare("Daniel:Schmidt\nLukas:Lüthke\nJonathan:Pohl\nDuong:Le");
		String[][] userAndComments = DateiInfoHelfer.getZeilenArray(data.getKommentare());
		String[] columns = { "User", "Kommentar" };
		TableModel model = new DefaultTableModel(userAndComments, columns);
		doneTable = new JTable(model);
		return doneTable;
	}

}
