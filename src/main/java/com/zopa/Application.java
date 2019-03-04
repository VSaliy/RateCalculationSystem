package com.zopa;

import com.zopa.view.LoanResult;
import com.zopa.controller.RateCalculationSystem;
import static com.zopa.config.QuoteConstant.UPPER_RANGE;
import static com.zopa.config.QuoteConstant.LOWER_RANGE;
import static com.zopa.config.QuoteConstant.INCREMENT_AMOUNT;

public class Application {

	public static void main(String[] args) {
		LoanResult loanResult = new LoanResult();
		try {
			validateParams(args);
		} catch (IllegalStateException e) {
			loanResult.setExceptionMessage(e.getMessage());
		}
		new RateCalculationSystem().process(args[0], args[1], loanResult);
		System.out.println(loanResult);
	}

	private static void validateParams(String[] args) {

		if (args.length < 1)
			throw new IllegalStateException(
					"Error occured while getting parameters from args, imcomplete information. Parameters are null.");

		if (args.length < 2)
			throw new IllegalStateException("Please provide the correct parameters");

		int loanAmount = Integer.parseInt(args[1]);
		if(loanAmount%INCREMENT_AMOUNT==0) {
			if (loanAmount < LOWER_RANGE || loanAmount > UPPER_RANGE) {
				throw new IllegalStateException("Sorry, only able to request a loan betwen £1000 and £15000");
			}
		} else {
			throw new IllegalStateException("Sorry, only able to request a loan of any £100");
		}
	}
}
