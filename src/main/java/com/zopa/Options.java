package com.zopa;

import static com.zopa.config.QuoteConstant.*;

public class Options {

	private static Options sInstance;

	private Options() {
	}

	public static Options getInstance() {
		if (sInstance == null)
			sInstance = new Options();

		return sInstance;
	}

	public boolean validateParams(String[] args) {
		if (args.length < 1)
			throw new IllegalStateException(
					"Error occured while getting parameters from args, imcomplete information. Parameters are null.");

		if (args.length < 2)
			throw new IllegalStateException("Please provide the correct parameters");
		try {
			int loanAmount = Integer.parseInt(args[1]);
			if (loanAmount % INCREMENT_AMOUNT == 0) {
				if (loanAmount < LOWER_RANGE || loanAmount > UPPER_RANGE) {
					throw new IllegalStateException("Sorry, only able to request a loan betwen £1000 and £15000");
				}
			} else {
				throw new IllegalStateException("Sorry, only able to request a loan of any £100");
			}
		} catch (NumberFormatException e) {
			throw new IllegalStateException("Sorry, only able to process numbers as loan parameter");
		}
		return true;
	}
}
