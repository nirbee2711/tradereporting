package com.jpmorgan.techtest.tradereporting.report;

//Decorator class for Total Settlement Report
public class TotalSettlementDecorater implements CheckStrategy {
	private CheckStrategy cs = null;
	private double totalSettlement = 0.0;

	public TotalSettlementDecorater(CheckStrategy cs) {
		this.cs = cs;
	}

	public boolean check(String operation, String entity, double usdAmountOfTrade) {
		boolean isFound = cs.check(operation, entity, usdAmountOfTrade);
		if (isFound)
			this.totalSettlement+=usdAmountOfTrade;
		return isFound;
	}

	public double getTotalSettlement() {
		return this.totalSettlement;
	}

	public void reset() {
		this.totalSettlement = 0;
	}
}