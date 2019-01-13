package com.jpmorgan.techtest.tradereporting.report;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmorgan.techtest.tradereporting.data.Instruction;
import com.jpmorgan.techtest.tradereporting.util.ReportUtils;

public class ReportExecuter {

	private List<Instruction> adjustData(List<Instruction> list) {

		for (Instruction instruction : list) {
			// 1. adjust the settlement Date of each Instruction
			if (instruction.getCurrency().equals("AED") || instruction.getCurrency().equals("SAR")) {
				switch (instruction.getRequestedSettlementDate().getDayOfWeek()) {
				case FRIDAY:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate().plusDays(2));
					break;
				case SATURDAY:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate().plusDays(1));
					break;
				default:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate());
					break;
				}
			} else {
				switch (instruction.getRequestedSettlementDate().getDayOfWeek()) {
				case SATURDAY:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate().plusDays(2));
					break;
				case SUNDAY:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate().plusDays(1));
					break;
				default:
					instruction.setRealSettlementDate(instruction.getRequestedSettlementDate());
					break;
				}
			}

			// 2. calculate the USD amount of Trade of each Instruction
			instruction.setUsdAmountOfTrade(
					instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFx());
		}
		return list;
	}

	private Map<LocalDate, List<Instruction>> groupByDate(List<Instruction> instructionList) {
		return instructionList.stream().collect(Collectors.groupingBy(
				instruction -> instruction.getRealSettlementDate().with(TemporalAdjusters.ofDateAdjuster(d -> d))));
	}

	public void createReport(List<Instruction> instructionList) {

		// 1. Adjust realSettlementDates + Calculate the usdAmount
		instructionList = adjustData(instructionList);

		// 2. groupData by Date
		Map<LocalDate, List<Instruction>> dayBasedInstructionMap = groupByDate(instructionList);

		System.out
				.println("============================== JP MORGAN TRADING REPORT ==============================\n\n");
		// 3. Calculate the total and max using buying and selling Strategy and
		// TotalSettlement Decorator
		System.out.println("=============================== DAILY SETTLEMENT REPORT ==============================");
		final CheckStrategy sellingStrategyForTotal = new TotalSettlementDecorater(new CheckSelling());
		final CheckStrategy buyingStrategyForTotal = new TotalSettlementDecorater(new CheckBuying());

		dayBasedInstructionMap.forEach((k, v) -> {
			for (Instruction instruction : v) {
				sellingStrategyForTotal.check(instruction.getOperation(), instruction.getEntity(),
						instruction.getUsdAmountOfTrade());
				buyingStrategyForTotal.check(instruction.getOperation(), instruction.getEntity(),
						instruction.getUsdAmountOfTrade());
			}
			System.out.println("\nReal Settlement Date:" + k + "==> Incoming (Selling) settled = USD "
					+ ((TotalSettlementDecorater) sellingStrategyForTotal).getTotalSettlement());
			System.out.println("                                   Outgoing (Buying ) settled = USD "
					+ ((TotalSettlementDecorater) buyingStrategyForTotal).getTotalSettlement());

			((TotalSettlementDecorater) sellingStrategyForTotal).reset();
			((TotalSettlementDecorater) buyingStrategyForTotal).reset();
		});

		// 4. Ranking of entities (not required daily rank, but overall rank)
		System.out
				.println("\n\n=========================== OVERALL ENTITY RANKING REPORT  ===========================");
		final CheckStrategy sellingStrategyForRanking = new RankingEntitiesDecorater(new CheckSelling());
		final CheckStrategy buyingStrategyForRanking = new RankingEntitiesDecorater(new CheckBuying());

		for (Instruction instruction : instructionList) {
			sellingStrategyForRanking.check(instruction.getOperation(), instruction.getEntity(),
					instruction.getUsdAmountOfTrade());
			buyingStrategyForRanking.check(instruction.getOperation(), instruction.getEntity(),
					instruction.getUsdAmountOfTrade());
		}
		System.out.println("Overall Ranking of (Selling) entities :" + ReportUtils
				.sortDescendingByValue(((RankingEntitiesDecorater) sellingStrategyForRanking).getRakingMap()).keySet());
		System.out.println("Overall Ranking of (Buying ) entities :" + ReportUtils
				.sortDescendingByValue(((RankingEntitiesDecorater) buyingStrategyForRanking).getRakingMap()).keySet());
	}
}
