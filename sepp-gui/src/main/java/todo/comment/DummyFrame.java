package todo.comment;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class DummyFrame extends JFrame {

	ToDoAndCommentBoxes boxes;
	
	public DummyFrame() {
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0, 1));
	
		boxes = new ToDoAndCommentBoxes();
		add(boxes.getCommentBox());
		add(boxes.getToDoBox());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DummyFrame();
	}
}
