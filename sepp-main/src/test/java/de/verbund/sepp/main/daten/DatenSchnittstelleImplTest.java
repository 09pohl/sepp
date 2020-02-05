package de.verbund.sepp.main.daten;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

public class DatenSchnittstelleImplTest {

	// 001
	@Test(expected = Test.None.class)
	public void testGetDateiInformationen() throws IOException {
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument.rtf");
	}

	// 002
	@Test(expected = NoSuchFileException.class)
	public void testKeineDatei() throws IOException {
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument2.rtf");
	}

	// 003
	@Test(expected = Test.None.class)
	public void testSchreiben() throws IOException {
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument.rtf");
		data.setKommentare("ich:test");
		data.setToDos("ich:test");
		schnittstelle.speichereDateiInformationen(data);
		DateiInformationen data2 = schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument.rtf");
		assertEquals("ich:test", data2.getToDos());
	}

	// 004
	@Test(expected = Test.None.class)
	public void testKommentarPfad() throws IOException {
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument.rtf");
		data.setKommentare("ich:test");
		data.setToDos("ich:test");
		schnittstelle.speichereDateiInformationen(data);
		DateiInformationen data2 = schnittstelle.getDateiInformationen("C:/Java/0-sepp/Dokument.rtf");
		assertEquals("C:/Java/0-sepp/Dokument.rtf.sepptodo", data2.getKommentareDatei());
	}

	// 005
	@Test(expected = Test.None.class)
	public void testPfade() throws IOException {
		Einstellungen test = Einstellungen.getInstance();
		Einstellungen.resetInstance();
		test.projektPfad = "C:/Java/0-sepp/0-sepp/";
		DatenSchnittstelle schnittstelle = DatenSchnittstelleImpl.getInstance();
		for (String string : schnittstelle.getDateiPfade(test)) {
			System.out.println(string);
		}
	}
}