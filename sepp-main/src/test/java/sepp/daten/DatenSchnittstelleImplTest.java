package sepp.daten;

import java.io.IOException;

import org.junit.Test;

public class DatenSchnittstelleImplTest {

	// 001
	@Test(expected = Test.None.class)
	public void testGetZeilenArray() throws IOException {
		DatenSchnittstelle schnittstelle = new DatenSchnittstelleImpl();
		schnittstelle.getDateiInformationen("C:\\Java\\0-sepp\\Dokument.rtf");
	}
}