package sepp.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DateiGUI extends JFrame implements ActionListener{

	private JButton bTODO;
	private JButton bKommentare;
	private JButton bLoeschen;
	private JFrame fenster;
	
	public DateiGUI() {
		Container panel = getContentPane();
		getUserIconImage();
		setSize(350, 200);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(getTitel()); 
		panel.setLayout(new BorderLayout());
		JFrame.setDefaultLookAndFeelDecorated(true);
	    removeMinMaxClose(fenster);
	    setResizable(false);

	    getContentPane().add(getMitte(), BorderLayout.CENTER);
	    getContentPane().add(getSueden(), BorderLayout.SOUTH);
	    
	    setVisible(true);
	    
	  }
	
	private void getUserIconImage() {
		ImageIcon img = new ImageIcon();
		//TODO Path to UserImage
		setIconImage(img.getImage());
	}

	private String getTitel() {
		//TODO - Get Username, Topic & Erstellungsdatum
		return null;
	}

	private Component getMitte() {
		JPanel p = new JPanel();
		//TODO - Get Kommentare & Änderungsdatum
		p.setLayout(new FlowLayout());  
		return p;
	}

	private Component getSueden() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		bTODO = new JButton("TODO-Liste");
		bTODO.addActionListener(this);
		bKommentare = new JButton("Kommentare");
		bKommentare.addActionListener(this);
		bLoeschen = new JButton("Loeschen");
		bLoeschen.addActionListener(this);
		p.add(bTODO); 
		p.add(bKommentare);
		p.add(bLoeschen);
		return p;
	}

	public void removeMinMaxClose(Component comp)
	  {
		 if(comp instanceof JButton)
		  {
		    String accName = ((JButton) comp).getAccessibleContext().getAccessibleName();
		    System.out.println(accName);
		    if(accName.equals("Maximize")|| accName.equals("Iconify")||
		       accName.equals("Close")) comp.getParent().remove(comp);
		  }
		  if (comp instanceof Container)
		  {
		    Component[] comps = ((Container)comp).getComponents();
		    for(int x = 0, y = comps.length; x < y; x++)
		    {
		      removeMinMaxClose(comps[x]);
		    }
	    }
	  }
	  public static void main(String[] args)
	  {
	    SwingUtilities.invokeLater(new Runnable(){
	      public void run(){
	        new DateiGUI();
	      }
	    });
	  }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bTODO) {
			//TODO: Get TODO-List
		}
		if (e.getSource() == bKommentare) {
			//TODO: Get Kommentare
		}
		if (e.getSource() == bLoeschen) {
			//TODO: Remove TODO-List & Kommentare
		}
	}
}
