package todo_comment;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class ToDoBox extends JFrame{
	
	private JLabel header;
	private JScrollPane commentBox;
	private JTextArea comment;
	
	public ToDoBox() {
		createToDoBox();
	}

	private void createToDoBox() {
	
		setLayout(new GridLayout(0, 1));
		setSize(500,300);
		setTitle("To Do's");
		setLocationRelativeTo(null);
	
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ToDoBox();
	}
	
}


