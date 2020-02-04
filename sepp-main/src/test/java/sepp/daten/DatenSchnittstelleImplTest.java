package sepp.daten;

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
		System.out.println(data.getToDos());
	}
}