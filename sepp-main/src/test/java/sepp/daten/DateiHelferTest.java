package sepp.daten;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.nio.file.attribute.FileTime;

import org.junit.Test;

import sepp.utils.DateiHelfer;

public class DateiHelferTest {

	// 001
	@Test(expected = Test.None.class)
	public void testBasisInfo() throws IOException {
		Einstellungen test = new Einstellungen();
		DateiHelfer datei = new DateiHelfer(test.einstellungenPfad);
		test.setProjektPfad("a");
		test.setUsername("b");
		test.speichern();
		FileTime datumEins = datei.basisInformationen().lastModifiedTime();
		test.speichern();
		FileTime datumZwei = datei.basisInformationen().lastModifiedTime();
		assertNotEquals(datumEins, datumZwei);
	}
}