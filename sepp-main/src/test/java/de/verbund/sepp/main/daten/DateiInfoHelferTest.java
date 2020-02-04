package de.verbund.sepp.main.daten;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import de.verbund.sepp.main.utils.DateiInfoHelfer;

public class DateiInfoHelferTest {

	// 001
	@Test(expected = Test.None.class)
	public void testGetZeilenArray() throws IOException {
		String test = "user:einszwei:dreivier:fünf\nnocheiner:\nZWEI";
		String[][] ausgabe = DateiInfoHelfer.getZeilenArray(test);
		assertEquals("einszwei:dreivier:fünf", ausgabe[0][1]);
	}

	// 002
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetZeilenArrayBounds() throws IOException {
		String test = "user:einszwei:dreivier:fünf\nnocheiner:\nZWEI";
		String[][] ausgabe = DateiInfoHelfer.getZeilenArray(test);
		System.out.println(ausgabe[2][1]);
	}
}