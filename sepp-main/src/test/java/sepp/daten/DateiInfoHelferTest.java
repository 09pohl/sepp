package sepp.daten;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import sepp.utils.DateinInfoHelfer;

public class DateiInfoHelferTest {

	// 001
	@Test(expected = Test.None.class)
	public void testGetZeilenArray() throws IOException {
		String test = "user:einszwei:dreivier:fünf\nnocheiner:\nZWEI";
		String[][] ausgabe = DateinInfoHelfer.getZeilenArray(test);
		assertEquals("einszwei:dreivier:fünf", ausgabe[0][1]);
	}

	// 002
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetZeilenArrayBounds() throws IOException {
		String test = "user:einszwei:dreivier:fünf\nnocheiner:\nZWEI";
		String[][] ausgabe = DateinInfoHelfer.getZeilenArray(test);
		System.out.println(ausgabe[2][1]);
	}
}