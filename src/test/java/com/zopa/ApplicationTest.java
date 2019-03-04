package com.zopa;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {
	private static final String OFFERS_FILE = "src/test/resources/Market Data for Exercise - .csv.csv";
	private static final String DROKEN_OFFERS_FILE = "src/test/resources/Market_with_empty_cells.csv";
	private static final String DROKEN_FILE_NAME = "market.csv";
	private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut(new PrintStream(consoleOutput));
	}

	@Test
	public void calculateLoan() {
		String[] args = new String[] {OFFERS_FILE, "1000"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("Requested amount:£1000.000"));
	}

	@Test
	public void processOffersWithEmptyCells() {
		String[] args = new String[] {DROKEN_OFFERS_FILE, "1000"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("Requested amount:£1000.000"));
	}

	@Test
	public void processNonValidFilenameThrowException() {
		String[] args = new String[] {DROKEN_FILE_NAME, "10000"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("market.csv (The system cannot find the file specified)"));
	}

	@Test
	public void processUpperRangeThrowException() {
		String[] args = new String[] {OFFERS_FILE, "100000"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("Sorry, only able to request a loan betwen £1000 and £15000"));
	}

	@Test
	public void processLowerRangeThrowException() {
		String[] args = new String[] {OFFERS_FILE, "500"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("Sorry, only able to request a loan betwen £1000 and £15000"));
	}

	@Test
	public void processIncrementAmountThrowException() {
		String[] args = new String[] {OFFERS_FILE, "1325"};
		Application.main(args);
		assertTrue(consoleOutput.toString().contains("Sorry, only able to request a loan of any £100"));
	}


}