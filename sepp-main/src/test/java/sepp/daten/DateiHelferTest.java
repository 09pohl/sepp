package sepp.daten;

import static org.junit.Assert.assertEquals;
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
		FileTime datumEins = datei.basisInformationen().lastModifiedTime();
		datei.schreibe("a");
		FileTime datumZwei = datei.basisInformationen().lastModifiedTime();
		assertNotEquals(datumEins, datumZwei);
	}

	@Test(expected = Test.None.class)
	public void testPfadOhneEndung() throws IOException {
		Einstellungen test = new Einstellungen();
		assertEquals(test.einstellungenPfad.replace(".properties", ""),
				DateiHelfer.pfadOhneEndung(test.einstellungenPfad));
	}
}