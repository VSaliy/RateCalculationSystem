package com.zopa;

import com.zopa.view.LoanResultImpl;
import com.zopa.controller.RateCalculationSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LoanResultImpl loanResult = new LoanResultImpl();
		try {
			if (Options.getInstance().validateParams(args)) {
				new RateCalculationSystem().process(args[0], args[1], loanResult);
			}
		} catch (IllegalStateException e) {
			loanResult.setExceptionMessage(e.getMessage());
		}
		logger.debug(loanResult.toString());
		System.out.println(loanResult);
	}

}
