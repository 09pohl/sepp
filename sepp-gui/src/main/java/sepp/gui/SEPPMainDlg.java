package sepp.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SEPPMainDlg{
	
	private JPanel mainPanel;
	private JFrame seppMainFrame = new JFrame();

	public SEPPMainDlg() {
		  erzeugeSplitLayout();
		  erzeugeMenue();
		  
		  seppMainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  seppMainFrame.setTitle("SuperEffectiveProjectPlanning (SEPP)");
		  seppMainFrame.setContentPane(mainPanel);
		  seppMainFrame.setSize(1000, 800);
		  seppMainFrame.setResizable(false);
		  seppMainFrame.setLocationRelativeTo(null);
		  seppMainFrame.setVisible(true);
	}
	
	
	private void erzeugeSplitLayout() {
		  mainPanel = new JPanel(new BorderLayout());
		  JPanel dateiPanel = new JPanel();
		  JPanel infoPanel = new JPanel(new BorderLayout());
		  JPanel toDoPanel = new JPanel();
		  JPanel commentsPanel = new JPanel();
		  JSplitPane toDo_comments_sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toDoPanel, commentsPanel);
		  toDo_comments_sp.setDividerLocation(360);
		  infoPanel.add(toDo_comments_sp, BorderLayout.CENTER);
		  JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dateiPanel, infoPanel);
		  sp.setDividerLocation(485);
		  mainPanel.add(sp, BorderLayout.CENTER);
	}


	private void erzeugeMenue() {
		JMenuBar menubar = new JMenuBar();
		seppMainFrame.setJMenuBar(menubar);
		
		// MENUEPUNKT 1: Einstellungen
		JMenu settings = new JMenu("Einstellungen");
		settings.setMnemonic('e');
		menubar.add(settings);
	}


	public static void main(String[] args) {
		new SEPPMainDlg();
	}
}