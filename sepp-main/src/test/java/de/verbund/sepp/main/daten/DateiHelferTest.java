package de.verbund.sepp.main.daten;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;

import org.junit.Test;

import de.verbund.sepp.main.utils.DateiHelfer;

public class DateiHelferTest {

	// 001
	@Test(expected = Test.None.class)
	public void testBasisInfo() throws IOException {
		Einstellungen test = Einstellungen.getInstance();
		DateiHelfer datei = new DateiHelfer(test.einstellungenPfad);
		test.username = "user";
		test.projektPfad = "pfad";
		test.speichern();
		FileTime datumEins = datei.basisInformationen().lastModifiedTime();
		datei.schreibe("a", true);
		datei.schreibe("a", true);
		datei.schreibe("a", true);
		datei.schreibe("a", true);
		FileTime datumZwei = datei.basisInformationen().lastModifiedTime();
		assertNotEquals(datumEins, datumZwei);
	}

	@Test(expected = Test.None.class)
	public void testPfadOhneEndung() throws IOException {
		Einstellungen test = Einstellungen.getInstance();
		assertEquals(test.einstellungenPfad.replace(".properties", ""),
				DateiHelfer.pfadOhneEndung(test.einstellungenPfad));
	}

	@Test(expected = Test.None.class)
	public void testEndung() throws IOException {
		String rtfPfad = "C:/Java/0-sepp/Dokument.rtf";
		File rtfFile = new File(rtfPfad);
		rtfFile.createNewFile();
		assertEquals("rtf", DateiHelfer.dateiEndung(rtfPfad));
	}
}