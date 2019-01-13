package com.jpmorgan.techtest.tradereporting.report;

//Strategy Interface
public interface CheckStrategy {
	public boolean check(String operation, String entity, double usdAmountOfTrade);
}
