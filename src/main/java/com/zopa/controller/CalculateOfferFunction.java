package com.zopa.controller;

import com.zopa.model.Lender;
import com.zopa.model.LenderImpl;

import java.util.function.Function;
import java.util.stream.Stream;

class CalculateOfferFunction implements Function<Lender, Stream<Lender>> {

	private double loan;

	CalculateOfferFunction(double loan) {
		this.loan = loan;
	}

	@Override
	public Stream<Lender> apply(Lender pocket) {
		if (Double.compare(loan, pocket.getAviable()) >= 0) {
			loan -= pocket.getAviable();
			return Stream.of(pocket);
		} else if (Double.compare(loan, 0.0) > 0) {
			Lender lastPocket = new LenderImpl(pocket.getLenderName(), pocket.getRate(), loan);
			loan = 0;

			return Stream.of(lastPocket);
		}
		return Stream.empty();
	}
}