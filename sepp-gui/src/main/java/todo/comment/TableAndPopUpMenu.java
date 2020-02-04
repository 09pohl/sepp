package todo.comment;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableAndPopUpMenu {

	private JTable table;
	
	public TableAndPopUpMenu(TableModel model) {
		table = new JTable(model);
	}
	
	private JTable createTable(){
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        int r = table.rowAtPoint(e.getPoint());
		        if (r >= 0 && r < table.getRowCount()) {
		        	table.setRowSelectionInterval(r, r);
		        } else {
		        	table.clearSelection();
		        }

		        int rowindex = table.getSelectedRow();
		        if (rowindex < 0)
		            return;
		        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		            JPopupMenu popup = createPopUpMenu(new JPopupMenu());
		            popup.show(e.getComponent(), e.getX(), e.getY());
		        }
		    }

			private JPopupMenu createPopUpMenu(JPopupMenu popup) {
				popup.add("Hinzufügen");
				popup.add("Editieren");
				popup.add("Löschen");
				return popup;
			}
		});
		table.setEnabled(false);
		return table;
		}

	public JTable getTable() {
		return createTable();
		
	}
}
