package sepp.daten;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class DatenSchnittstelleImplTest {

	// 001
	@Test(expected = Test.None.class)
	public void testGetDateiInformationen() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
	}

	// 002
	@Test(expected = Test.None.class)
	public void testKommentar() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
		assertEquals(null, data.getKommentare());
	}

	// 003
	@Test(expected = Test.None.class)
	public void testSchreiben() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
		data.setKommentare("ich:test");
		data.setToDos("ich:test");
		schnittstelle.speichereDateiInformationen(data);
		DateiInformationen data2 = schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
		assertEquals("ich:test", data2.getToDos());
	}

	// 004
	@Test(expected = Test.None.class)
	public void testKommentarPfad() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		DateiInformationen data = schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
		data.setKommentare("ich:test");
		data.setToDos("ich:test");
		schnittstelle.speichereDateiInformationen(data);
		DateiInformationen data2 = schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
		assertEquals("C:\\Java\\0-sepp\\Dokument.rtf.sepptodo", data2.getKommentareDatei());
	}
}