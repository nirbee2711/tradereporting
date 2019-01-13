package com.jpmorgan.techtest.tradereporting.report;

//Strategy for Selling - Incoming
public class CheckSelling implements CheckStrategy {
	public static final String SELLING = "S"; // constant

	public boolean check(String operation, String entity, double usdAmountOfTrade) {
		if (operation == null)
			return false;
		else
			return operation.equals(SELLING);
	}
}
