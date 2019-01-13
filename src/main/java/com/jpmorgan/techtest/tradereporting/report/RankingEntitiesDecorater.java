package com.jpmorgan.techtest.tradereporting.report;

import java.util.HashMap;
import java.util.Map;

//Decorator class for Ranking Report
public class RankingEntitiesDecorater implements CheckStrategy {
	private CheckStrategy cs = null;
	private HashMap<String, Double> rakingMap = new HashMap<>();

	public RankingEntitiesDecorater(CheckStrategy cs) {
		this.cs = cs;
	}

	public boolean check(String operation, String entity, double usdAmountOfTrade) {
		boolean isFound = cs.check(operation, entity, usdAmountOfTrade);
		if (isFound)
			if (rakingMap.containsKey(entity)) {
				rakingMap.put(entity, rakingMap.get(entity) + usdAmountOfTrade);
			} else {
				rakingMap.put(entity, usdAmountOfTrade);
			}
		return isFound;
	}

	public Map<String, Double> getRakingMap() {
		return this.rakingMap;
	}

	public void reset() {
		this.rakingMap = new HashMap<>();
	}
}