package de.verbund.sepp.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.verbund.sepp.main.daten.*;

public class DateioeffnerController {
	DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
	Einstellungen einstellungen = schnittstelle.getEinstellungen();	
	private String pfad;
	static DateiInformationen data;
	
	public DateioeffnerController(String pfad) throws IOException {
		this.pfad = pfad;
	}
	
	public void getOrdner() throws IOException {
		
//		Desktop desktop = Desktop.getDesktop();
//		File dirToOpen = null;
//		try {
//			dirToOpen = new File(data.toString());
//			desktop.open(dirToOpen);
//		} catch (IllegalArgumentException e) {
//			System.out.println("File Not Found");
//		}
	}

	public static boolean open(File pfad) {

        if (openSystemSpecific(pfad.toString())) return true;

        if (openDESKTOP(pfad)) return true;

        return false;
    }

	public static boolean openSystemSpecific(String what) {
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
	

	private static boolean runCommand(String command, String args, String pfad) {
		logOut("Trying to exec:\n   cmd = " + command + "\n   args = " + args + "\n   %s = " + pfad);

        String[] parts = prepareCommand(command, args, pfad);

        try {
            Process p = Runtime.getRuntime().exec(parts);
            if (p == null) return false;

            try {
                int retval = p.exitValue();
                if (retval == 0) {
                    logErr("Process ended immediately.");
                    return false;
                } else {
                    logErr("Process crashed.");
                    return false;
                }
            } catch (IllegalThreadStateException itse) {
                logErr("Process is running.");
                return true;
            }
        } catch (IOException e) {
            logErr("Error running command.", e);
            return false;
        }
	}
	
	private static boolean openDESKTOP(File pfad) {
		 logOut("Trying to use Desktop.getDesktop().open() with " + pfad.toString());
	        try {
	            if (!Desktop.isDesktopSupported()) {
	                logErr("Platform is not supported.");
	                return false;
	            }

	            if (!Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
	                logErr("OPEN is not supported.");
	                return false;
	            }

	            Desktop.getDesktop().open(pfad);

	            return true;
	        } catch (Throwable t) {
	            logErr("Error using desktop open.", t);
	            return false;
	        }
	    }

	private static String[] prepareCommand(String command, String args, String pfad) {
        List<String> parts = new ArrayList<String>();
        parts.add(command);

        if (args != null) {
            for (String s : args.split(" ")) {
                s = String.format(s, pfad); // put in the filename thing
                parts.add(s.trim());
            }
        }
        return parts.toArray(new String[parts.size()]);
    }

	
    private static void logErr(String msg, Throwable t) {
        System.err.println(msg);
        t.printStackTrace();
    }

    private static void logErr(String msg) {
        System.err.println(msg);
    }

    private static void logOut(String msg) {
        System.out.println(msg);
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
