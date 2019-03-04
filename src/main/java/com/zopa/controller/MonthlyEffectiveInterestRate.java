package com.zopa.controller;

import java.math.BigDecimal;

@FunctionalInterface
public interface MonthlyEffectiveInterestRate {
	BigDecimal calculate(Double loanAmount, Double rate, Integer term);
}
