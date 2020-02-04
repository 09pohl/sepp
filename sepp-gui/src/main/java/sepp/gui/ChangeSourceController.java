package sepp.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ChangeSourceController {
	
	private ChangeSourceDlg changeDlg;
	
	public ChangeSourceController(JFrame frame) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				changeDlg = new ChangeSourceDlg(frame);
				changeDlg.getRootPane().setDefaultButton(changeDlg.getChooseButton());
				changeDlg.getChooseButton().addActionListener(e -> chooseFolder());
				changeDlg.getEnterButton().addActionListener(e -> saveSelection());
				changeDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				changeDlg.setSize(600, 100);
				changeDlg.setResizable(false);
				changeDlg.setModal(true);
				changeDlg.setLocationRelativeTo(frame);
				changeDlg.setVisible(true);
				
			}
		});

	}

	protected void saveSelection() {
		String newDirectory = changeDlg.getTextField().getText();
		System.out.println("Neuer Ordner: " + newDirectory);
		changeDlg.dispose();
	}

	private void chooseFolder() {
		JFileChooser fc = new JFileChooser(System.getProperty("user.home"));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(changeDlg);
		changeDlg.getTextField().setText(fc.getSelectedFile().getPath());
	}
	
}
