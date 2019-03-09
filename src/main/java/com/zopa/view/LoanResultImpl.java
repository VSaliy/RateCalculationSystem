package com.zopa.view;

import java.math.BigDecimal;

public class LoanResultImpl implements LoanResult {

	private BigDecimal requestedAmount;
	private BigDecimal rate;
	private BigDecimal monthlyRepayment;
	private BigDecimal totalRepayment;
	private String exceptionMessage;

	@Override
	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	@Override
	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	@Override
	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	@Override
	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	@Override
	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	@Override
	public void setTotalRepayment(BigDecimal totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	@Override
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	@Override
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return getExceptionMessage() != null && !getExceptionMessage().isEmpty() ?
				String.format("Error: %1$s", getExceptionMessage()) :
				String.format("Requested amount: £%1$s%n" +
								"Rate: %2$s%%%n" +
								"Monthly repayment: £%3$s%n" +
								"Total repayment: £%4$s",
						getRequestedAmount(),
						getRate(),
						getMonthlyRepayment(),
						getTotalRepayment());
	}
}
