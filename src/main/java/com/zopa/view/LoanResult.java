package com.zopa.view;

import java.math.BigDecimal;

public class LoanResult {

	private BigDecimal requestedAmount;
	private BigDecimal rate;
	private BigDecimal monthlyRepayment;
	private BigDecimal totalRepayment;
	private String exceptionMessage;

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public void setTotalRepayment(BigDecimal totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return exceptionMessage != null && !exceptionMessage.isEmpty() ? exceptionMessage :
				"Requested amount:£" + requestedAmount + "\n" +
						"Rate:" + rate + "%\n" +
						"Monthly repayment:£" + monthlyRepayment + "\n" +
						"Total repayment:£" + totalRepayment;
	}
}
