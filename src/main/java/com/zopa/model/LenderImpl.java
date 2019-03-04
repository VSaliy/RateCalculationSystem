package com.zopa.model;

public class LenderImpl implements Lender {

	private String lenderName;
	private Double rate;
	private Double aviable;

	public LenderImpl(String lenderName, Double rate, Double aviable) {
		this.lenderName = lenderName;
		this.rate = rate;
		this.aviable = aviable;
	}

	@Override
	public String getLenderName() {
		return lenderName;
	}

	@Override
	public Double getRate() {
		return rate;
	}

	@Override
	public Double getAviable() {
		return aviable;
	}

}
