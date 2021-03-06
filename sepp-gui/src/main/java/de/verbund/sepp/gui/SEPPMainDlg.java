package de.verbund.sepp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import de.verbund.sepp.gui.controller.*;
import de.verbund.sepp.gui.icon.IconLoader;
import de.verbund.sepp.gui.todo.comment.ToDoAndCommentBoxes;
import de.verbund.sepp.main.daten.*;
import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class SEPPMainDlg {

	private JPanel mainPanel;
	private JPanel panel;
	private JFrame seppMainFrame = new JFrame();

	private DateiViewController dc = new DateiViewController();
	private JSplitPane frameSplitPane;
	private ToDoAndCommentBoxes toDoComments = new ToDoAndCommentBoxes(this);

	private static SEPPMainDlg instance;

	public static SEPPMainDlg getInstance() {
		if (instance == null) {
			instance = new SEPPMainDlg();
		}
		return instance;
	}

	private SEPPMainDlg() {
		erzeugeSplitLayout();
		erzeugeMenue();
		seppMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		seppMainFrame.setTitle("SuperEffectiveProjectPlanning (SEPP)");
		seppMainFrame.setContentPane(panel);
		seppMainFrame.setSize(1000, 800);
		seppMainFrame.setResizable(true);
		seppMainFrame.setLocationRelativeTo(null);
		IconLoader iconLoader = new IconLoader();
		seppMainFrame.setIconImages(iconLoader.laden());
		seppMainFrame.setVisible(true);
	}

	private void erzeugeSplitLayout() {
		mainPanel = new JPanel(new BorderLayout());
		panel = new JPanel(new BorderLayout());
		try {
			erzeugeButtonPanel();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(seppMainFrame, "Fehler beim Einlesen bestehender Einstellungen!", "FEHLER!",
					JOptionPane.ERROR_MESSAGE);
		}
		panel.add(mainPanel, BorderLayout.CENTER);
		JScrollPane dateiScroll = dc.init();
		setFilePanelSettings(dateiScroll);
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
		frameSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dateiScroll, infoPanel);
		frameSplitPane.resetToPreferredSizes();
		frameSplitPane.setDividerLocation(485);
		mainPanel.add(frameSplitPane, BorderLayout.CENTER);
	}

	private void erzeugeButtonPanel() throws IOException {
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton refreshButton = new JButton("Aktualisieren");
		refreshButton.addActionListener(e -> {

			try {
				refreshMainTables();
			} catch (IOException e1) {
				SEPPMainDlg.getInstance().getIOException("Fehler: Aktualisierung nicht möglich!");
			}

		});
		JButton bZurHauptdatei = new JButton("Zur Hauptdatei");
		bZurHauptdatei.addActionListener(e -> {
			try {
				ActiveFileController.getInstance()
						.setAktiveDateiPfad(schnittstelle.getEinstellungen().getProjektDateiPfad());
				this.refreshMainTables();
			} catch (IOException e1) {
				SEPPMainDlg.getInstance().getIOException("Fehler: Laden nicht möglich");
			}
		});
		JLabel lAktiveDatei = new JLabel("");
		ActiveFileController.getInstance().setLAktiveDatei(lAktiveDatei);
		buttonPanel.add(lAktiveDatei);
		ActiveFileController.getInstance().refreshLabel();
		ActiveFileController.getInstance().setBZurHauptdatei(bZurHauptdatei);
		buttonPanel.add(bZurHauptdatei);
		bZurHauptdatei.setVisible(false);
		buttonPanel.add(refreshButton);
		panel.add(buttonPanel, BorderLayout.NORTH);
	}

	public void refreshMainTables() throws IOException {
		frameSplitPane.remove(frameSplitPane.getLeftComponent());
		frameSplitPane.setLeftComponent(dc.init());
		frameSplitPane.setDividerLocation(485);
		setFilePanelSettings((JScrollPane) frameSplitPane.getLeftComponent());
		DatenSchnittstelle dataSchnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen daten;
		String dateiPfad = ActiveFileController.getInstance().getAktiveDateiPfad();
		daten = dataSchnittstelle.getDateiInformationen(dateiPfad);
		String[][] userKommentare = DateiInfoHelfer.getZeilenArray(daten.getKommentare());
		refreshTableModel(userKommentare, ToDoAndCommentBoxes.spaltenKommentare, toDoComments.getTableComment());
		String[][] userToDos = DateiInfoHelfer.getZeilenArray(daten.getToDos());
		refreshTableModel(userToDos, ToDoAndCommentBoxes.spaltenTodos, toDoComments.getTableToDo());
	}

	private void setFilePanelSettings(JScrollPane component) {
		component.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		component.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		component.getVerticalScrollBar().setUnitIncrement(25);
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

	private void getIOException(String message) {
		JOptionPane.showMessageDialog(seppMainFrame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	private void showUserDlg() {
		new ChangeUserController(seppMainFrame);
	}

	private void showSourceDlg() {
		new ChangeSourceController(seppMainFrame, this);
	}

	public static void main(String[] args) {
		new StartUpController();
		SEPPMainDlg.getInstance();
	}
}