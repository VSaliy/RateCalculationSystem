package com.zopa;

import com.zopa.view.LoanResultImpl;
import com.zopa.controller.RateCalculationSystem;
import static com.zopa.config.QuoteConstant.UPPER_RANGE;
import static com.zopa.config.QuoteConstant.LOWER_RANGE;
import static com.zopa.config.QuoteConstant.INCREMENT_AMOUNT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LoanResultImpl loanResult = new LoanResultImpl();
		try {
			validateParams(args);
		} catch (IllegalStateException e) {
			loanResult.setExceptionMessage(e.getMessage());
		}
		new RateCalculationSystem().process(args[0], args[1], loanResult);
		logger.info(loanResult.toString());
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
