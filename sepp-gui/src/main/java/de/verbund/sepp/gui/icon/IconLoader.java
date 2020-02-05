package de.verbund.sepp.gui.icon;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class IconLoader {

	private static final String[] AUFLOESUNGEN = { "16", "32", "64", "128" };
	private static final String ICON_NAME = "tempicon_";
	private static final String ICON_ENDUNG = ".png";

	public List<Image> laden() {
		List<Image> result = new ArrayList<Image>();
		for (String aufl : AUFLOESUNGEN) {
			ImageIcon icon = new ImageIcon(this.getClass().getResource(ICON_NAME + aufl + ICON_ENDUNG));
			result.add(icon.getImage());
		}
		return result;
	}

}
