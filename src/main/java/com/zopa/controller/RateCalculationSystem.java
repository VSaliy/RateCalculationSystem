package com.zopa.controller;

import com.zopa.model.LenderImpl;
import com.zopa.view.LoanResult;
import com.zopa.model.Offer;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.zopa.config.QuoteConstant.TERM_MONTHS;

public class RateCalculationSystem {

	private static final String SEPARATOR = ",";

	public void process(String filePath, String requestedAmount, LoanResult loanResult) {

		Predicate<List<String>> filterRowWithEmptyCells = line -> line.parallelStream().noneMatch(String::isEmpty);
		Function<Function<String, Double>, Function<List<String>, LenderImpl>> rowToLender =
				stringToDouble -> row -> new LenderImpl(row.get(0), stringToDouble.apply(row.get(1)), stringToDouble.apply(row.get(2)));
		Function<String, BigDecimal> parse = str -> BigDecimal.valueOf(Float.parseFloat(str)).setScale(3, RoundingMode.CEILING);

		MonthlyEffectiveInterestRate monthlyEffectiveInterestRate = (investment, interestRate, term) -> {
			double r = Math.pow((1.0D + interestRate),1.0D/12D) - 1.0D;
			double v = (r * investment) / (1 - Math.pow((1 + r), -term));
			return BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_UP);
		};

		File file = new File(filePath);
		try (InputStream inputStream = new FileInputStream(file);
			BufferedReader r = new BufferedReader(new InputStreamReader(inputStream))) {

			List<Offer> collect = r.lines()
					.skip(1)
					.map(line -> Arrays.asList(line.split(SEPARATOR)))
					.filter(line -> line.size() == 3)
					.filter(filterRowWithEmptyCells)
					.map(rowToLender.apply(Double::parseDouble))
					.sorted(Comparator.comparing(Offer::getRate))
					.flatMap(new CalculateOfferFunction(Double.valueOf(requestedAmount)))
					.collect(Collectors.toList());
			BigDecimal rate = BigDecimal.valueOf(collect
						.stream()
						.mapToDouble(Offer::getRate)
						.average()
						.orElseThrow(IllegalStateException::new))
					.multiply(new BigDecimal(100))
					.setScale(1, RoundingMode.CEILING);
			BigDecimal monthlyInterest = collect.stream()
					.map(offer -> monthlyEffectiveInterestRate.calculate(offer.getAviable(), offer.getRate(), TERM_MONTHS))
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			loanResult.setRequestedAmount(parse.apply(requestedAmount));
			loanResult.setTotalRepayment(monthlyInterest.multiply(new BigDecimal(TERM_MONTHS)));
			loanResult.setRate(rate);
			loanResult.setMonthlyRepayment(monthlyInterest);

		} catch (IOException e) {
			loanResult.setExceptionMessage(e.getMessage());
		}

	}

}
