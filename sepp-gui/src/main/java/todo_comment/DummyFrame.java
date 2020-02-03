package todo_comment;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class DummyFrame extends JFrame {

	public DummyFrame() {
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0,1));
		
		
		CommentBox comment = new CommentBox();
		ToDoBox toDo = new ToDoBox();

		add(toDo);
		add(comment);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DummyFrame();
	}
}
