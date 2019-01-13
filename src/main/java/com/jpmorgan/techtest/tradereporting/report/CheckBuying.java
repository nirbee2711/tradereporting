package com.jpmorgan.techtest.tradereporting.report;

//Strategy for Buying - Outgoing
public class CheckBuying implements CheckStrategy {
	public static final String BUYING = "B"; // constant

	public boolean check(String operation, String entity, double usdAmountOfTrade) {
		if (operation == null)
			return false;
		else
			return operation.equals(BUYING);
	}
}
