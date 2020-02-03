package sepp.daten;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class EinstellungenTest {

	// 001
	@Test(expected = Test.None.class)
	public void testUsernameNull() throws IOException {
		Einstellungen test = new Einstellungen();
		assertEquals(null, test.getUsername());
	}

	// 002
	@Test(expected = Test.None.class)
	public void testUsernameSet() throws IOException {
		Einstellungen test = new Einstellungen();
		test.username = "testName";
		assertEquals("testName", test.getUsername());
	}

	// 003
	@Test(expected = Test.None.class)
	public void testProjektPfadNull() throws IOException {
		Einstellungen test = new Einstellungen();
		assertEquals(null, test.getProjektPfad());
	}

	// 004
	@Test(expected = Test.None.class)
	public void testProjektPfadSet() throws IOException {
		Einstellungen test = new Einstellungen();
		test.projektPfad = "testPfad";
		assertEquals("testPfad", test.getProjektPfad());
	}

	// 005
	@Test(expected = Test.None.class)
	public void testSpeichern() throws IOException {
		Einstellungen test = new Einstellungen();
		test.projektPfad = "testPfad";
		test.username = "testName";
		test.speichern();
	}

	// 006
	@Test(expected = Test.None.class)
	public void testLaden() throws IOException {
		Einstellungen test = new Einstellungen();
		test.projektPfad = "eins";
		test.username = "zwei";
		test.speichern();
		test.laden();
		assertEquals("eins", test.getProjektPfad());
		assertEquals("zwei", test.getUsername());
	}

	// 007
	@Test(expected = Test.None.class)
	public void testLadenError() throws IOException {
		String pfad = System.getProperty("user.home") + "\\sepp_config.properties";
		File f = new File(pfad);
		if (f.delete()) {
			System.out.println(f.getName() + " deleted");
		} else {
			System.out.println("failed");
		}
		Einstellungen test = new Einstellungen();
		test.laden();
	}
}