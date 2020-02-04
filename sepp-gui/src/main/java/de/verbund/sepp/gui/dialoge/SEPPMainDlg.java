package de.verbund.sepp.gui.dialoge;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import de.verbund.sepp.gui.controller.ChangeSourceController;
import de.verbund.sepp.gui.controller.ChangeUserController;
import de.verbund.sepp.gui.controller.StartUpController;
import de.verbund.sepp.gui.todo.comment.ToDoAndCommentBoxes;
import de.verbund.sepp.main.daten.DateiInformationen;
import de.verbund.sepp.main.daten.DatenSchnittstelle;
import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class SEPPMainDlg {

	private JPanel mainPanel;
	private JPanel panel;
	private JFrame seppMainFrame = new JFrame();
	private ToDoAndCommentBoxes toDoComments = new ToDoAndCommentBoxes();

	public SEPPMainDlg() {
		erzeugeSplitLayout();
		erzeugeMenue();
		seppMainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		seppMainFrame.setTitle("SuperEffectiveProjectPlanning (SEPP)");
		seppMainFrame.setContentPane(panel);
		seppMainFrame.setSize(1000, 800);
		seppMainFrame.setResizable(false);
		seppMainFrame.setLocationRelativeTo(null);
		seppMainFrame.setVisible(true);
	}

	private void erzeugeSplitLayout() {
		mainPanel = new JPanel(new BorderLayout());
		panel = new JPanel(new BorderLayout());
		erzeugeButtonPanel();
		panel.add(mainPanel, BorderLayout.CENTER);
		JPanel dateiPanel = new JPanel();
		JPanel infoPanel = new JPanel(new BorderLayout());
		JPanel toDoPanel = new JPanel(new BorderLayout());
		JScrollPane toDoScroll = toDoComments.getToDoBox();
		toDoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		toDoPanel.add(toDoScroll);
		JPanel commentsPanel = new JPanel(new BorderLayout());
		JScrollPane commentScroll = toDoComments.getCommentBox();
		commentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		commentsPanel.add(commentScroll);
		JSplitPane infoSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toDoPanel, commentsPanel);
		infoSplitPane.setDividerLocation(360);
		infoPanel.add(infoSplitPane, BorderLayout.CENTER);
		JSplitPane frameSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dateiPanel, infoPanel);
		frameSplitPane.setDividerLocation(485);
		mainPanel.add(frameSplitPane, BorderLayout.CENTER);
	}

	private void erzeugeButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton refreshButton = new JButton("Aktualisieren...");
		refreshButton.addActionListener(e -> {

			try {
				refreshMainTables();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		buttonPanel.add(refreshButton);
		panel.add(buttonPanel, BorderLayout.NORTH);
	}

	public void refreshMainTables() throws IOException {
		DatenSchnittstelle dataSchnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen daten;
		daten = dataSchnittstelle.getDateiInformationen(
				dataSchnittstelle.getEinstellungen().getProjektPfad() + "\\" + DatenSchnittstelle.PRIMAER_DATEINAME);
		String[][] userKommentare = DateiInfoHelfer.getZeilenArray(daten.getKommentare());
		refreshTableModel(userKommentare, ToDoAndCommentBoxes.spaltenKommentare, toDoComments.getTableComment());
		String[][] userToDos = DateiInfoHelfer.getZeilenArray(daten.getToDos());
		refreshTableModel(userToDos, ToDoAndCommentBoxes.spaltenTodos, toDoComments.getTableToDo());
	}

	private void refreshTableModel(String[][] inhalte, String[] spaltenTitel, JTable table) {
		if (table != null) {
			table.setModel(new DefaultTableModel(inhalte, spaltenTitel));
			AbstractTableModel absz = (AbstractTableModel) toDoComments.getTableComment().getModel();
			absz.fireTableDataChanged();
		}
	}

	private void erzeugeMenue() {
		JMenuBar menubar = new JMenuBar();
		seppMainFrame.setJMenuBar(menubar);

		// MENUEPUNKT 1: Einstellungen
		JMenu settings = new JMenu("Einstellungen");
		settings.setMnemonic('e');
		menubar.add(settings);

		JMenuItem source = new JMenuItem("Quellverzeichnis wechseln...");
		source.setMnemonic('q');
		source.addActionListener(e -> showSourceDlg());
		settings.add(source);

		JMenuItem user = new JMenuItem("Benutzernamen ändern...");
		user.setMnemonic('b');
		user.addActionListener(e -> showUserDlg());
		settings.add(user);
	}

	private void showUserDlg() {
		new ChangeUserController(seppMainFrame);
	}

	private void showSourceDlg() {
		new ChangeSourceController(seppMainFrame);
	}

	public static void main(String[] args) {
		new StartUpController();
		new SEPPMainDlg();
	}
}