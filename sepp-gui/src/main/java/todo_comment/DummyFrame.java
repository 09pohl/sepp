package todo_comment;

import javax.swing.JFrame;

public class DummyFrame extends JFrame {

	public DummyFrame() {
		
		setSize(800,700);
		CommentBox comment = new CommentBox();
		add(comment);
		System.out.println("hi");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DummyFrame();
	}
}
