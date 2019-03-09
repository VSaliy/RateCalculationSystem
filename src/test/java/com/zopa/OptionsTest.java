package com.zopa;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.internal.matchers.StringContains.containsString;

public class OptionsTest {

	private static final String OFFERS_FILE = "tets/filePath";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void processUpperRangeThrowException() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("Sorry, only able to request a loan betwen £1000 and £15000"));
		String[] args = new String[] {OFFERS_FILE, "100000"};
		Options.getInstance().validateParams(args);
	}

	@Test
	public void processLowerRangeThrowException() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("Sorry, only able to request a loan betwen £1000 and £15000"));
		String[] args = new String[] {OFFERS_FILE, "500"};
		Options.getInstance().validateParams(args);
	}

	@Test
	public void processIncrementAmountThrowException() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("Sorry, only able to request a loan of any £100"));
		String[] args = new String[] {OFFERS_FILE, "1325"};
		Options.getInstance().validateParams(args);
	}

	@Test
	public void processWrongAmountValueThrowException() {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("Sorry, only able to process numbers as loan parameter"));
		String[] args = new String[] {OFFERS_FILE, "abcd"};
		Options.getInstance().validateParams(args);
	}

}
