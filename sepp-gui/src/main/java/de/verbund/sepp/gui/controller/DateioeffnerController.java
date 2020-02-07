package de.verbund.sepp.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DateioeffnerController {

    public static boolean open(File file) {

        if (openSystemSpecific(file.toString())) return true;

        if (openDESKTOP(file)) return true;

        return false;
    }

    private static boolean openSystemSpecific(String what) {

        EnumOS os = getOs();

        if (os.isLinux()) {
            if (runCommand("kde-open", "%s", what)) return true;
            if (runCommand("gnome-open", "%s", what)) return true;
            if (runCommand("xdg-open", "%s", what)) return true;
        }

        if (os.isMac()) {
            if (runCommand("open", "%s", what)) return true;
        }

        if (os.isWindows()) {
            if (runCommand("explorer", "%s", what)) return true;
        }

        return false;
    }

    private static boolean openDESKTOP(File file) {

        try {
            if (!Desktop.isDesktopSupported()) {
            	JOptionPane.showMessageDialog(null, "Plattform wird nicht unterstützt!",
            			"FEHLER!", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            	JOptionPane.showMessageDialog(null, "Öffen - Funktion wird nicht unterstützt!",
            			"FEHLER!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Desktop.getDesktop().open(file);

            return true;
        } catch (Throwable t) {
        	JOptionPane.showMessageDialog(null, "Desktop kann nicht geöffnet werden!",
        			"FEHLER!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private static boolean runCommand(String command, String args, String file) {

        String[] parts = prepareCommand(command, args, file);

        try {
            Process p = Runtime.getRuntime().exec(parts);
            if (p == null) return false;

            try {
                int retval = p.exitValue();
                if (retval == 0) {
                    return false;
                } else {
                	JOptionPane.showMessageDialog(null, "Fehler beim Prozess!");
                    return false;
                }
            } catch (IllegalThreadStateException itse) {
                return true;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler in der Funktion!", "FEHLER!", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
    }


    private static String[] prepareCommand(String command, String args, String file) {

        List<String> parts = new ArrayList<String>();
        parts.add(command);
        
        if (args != null) {
            for (String s : args.split(" ")) {
                s = String.format(s, file);
                parts.add(s.trim());
            }
        }

        return parts.toArray(new String[parts.size()]);
    }

    public static enum EnumOS {
        linux, macos, solaris, unknown, windows;

        public boolean isLinux() {
            return this == linux || this == solaris;
        }

        public boolean isMac() {
            return this == macos;
        }

        public boolean isWindows() {
            return this == windows;
        }
    }


    public static EnumOS getOs() {

        String s = System.getProperty("os.name").toLowerCase();

        if (s.contains("win")) {
            return EnumOS.windows;
        }

        if (s.contains("mac")) {
            return EnumOS.macos;
        }

        if (s.contains("solaris")) {
            return EnumOS.solaris;
        }

        if (s.contains("sunos")) {
            return EnumOS.solaris;
        }

        if (s.contains("linux")) {
            return EnumOS.linux;
        }

        if (s.contains("unix")) {
            return EnumOS.linux;
        } else {
            return EnumOS.unknown;
        }
    }
}
