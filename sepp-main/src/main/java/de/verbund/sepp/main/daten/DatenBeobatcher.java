package de.verbund.sepp.main.daten;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

public class DatenBeobatcher {

	private WatchService watcher;
	private List<WatchKey> keys = new ArrayList<>();
	private BeobachterThread thread;

	private static DatenBeobatcher instance;

	private DatenBeobatcher() throws IOException {
		watcher = FileSystems.getDefault().newWatchService();

	}

	public static DatenBeobatcher getInstance() throws IOException {
		if (instance == null) {
			instance = new DatenBeobatcher();
		}
		return instance;
	}

	public static void resetInstance() throws IOException {
		instance = new DatenBeobatcher();
	}

	public void register(Path pfad) throws IOException {

		WatchKey key = pfad.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		keys.add(key);
	}

	public void start() {
		thread = new BeobachterThread(watcher);
		thread.run();
	}

}
