package de.verbund.sepp.main.daten;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class BeobachterThread extends Thread {
	WatchService watcher;

	public BeobachterThread(WatchService watcher) {
		this.watcher = watcher;
	}

	/**
	 */
	@Override
	public void run() {
		System.out.println("start");
		while (true) {
			try {
				WatchKey key = watcher.take();
				List<WatchEvent<?>> eventList = key.pollEvents();
				System.out.println("size = " + eventList.size());
				for (WatchEvent<?> e : eventList) {
					System.out.print(e.kind() + " -> ");
					Path name = (Path) e.context();
					System.out.println(name);
				}
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}

}
