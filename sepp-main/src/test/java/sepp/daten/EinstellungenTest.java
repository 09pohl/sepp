package sepp.daten;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EinstellungenTest {

	@Test
	public void testUsernameNull() {
		Einstellungen test = new Einstellungen();
		assertEquals(null, test.getUsername());
	}

	public void testUsernameSet() {
		Einstellungen test = new Einstellungen();
		test.username = "testName";
		assertEquals("testName", test.getUsername());
	}
}