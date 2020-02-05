package de.verbund.sepp.gui.todo.comment;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

import de.verbund.sepp.main.daten.DatenSchnittstelleImpl;
import de.verbund.sepp.main.daten.Einstellungen;

public class DummyFrame extends JFrame {

	ToDoAndCommentBoxes boxes;

	public DummyFrame() throws IOException {
		Einstellungen einst = new DatenSchnittstelleImpl().getEinstellungen();
		einst.setUsername("test");
		einst.setProjektPfad("C:/Users/Administrator/it/sepp/TestOrdner");
		System.out.println(einst.getProjektPfad());
		einst.speichern();
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(0, 1));
		boxes = new ToDoAndCommentBoxes();
		add(boxes.getCommentBox());
		add(boxes.getToDoBox());
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			new DummyFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
