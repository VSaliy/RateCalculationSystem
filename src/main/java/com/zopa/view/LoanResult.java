package com.zopa.view;

import java.math.BigDecimal;

public interface LoanResult {
	BigDecimal getRequestedAmount();

	void setRequestedAmount(BigDecimal requestedAmount);

	BigDecimal getRate();

	void setRate(BigDecimal rate);

	BigDecimal getMonthlyRepayment();

	void setMonthlyRepayment(BigDecimal monthlyRepayment);

	BigDecimal getTotalRepayment();

	void setTotalRepayment(BigDecimal totalRepayment);

	String getExceptionMessage();

	void setExceptionMessage(String exceptionMessage);

}
