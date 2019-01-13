package com.jpmorgan.techtest.tradereporting.data;

import java.time.LocalDate;

public class Instruction {
	
	private String entity;
	private String operation; //Buy/Sell
	private double agreedFx;
	private String currency;
	private LocalDate instructionDate;
	private LocalDate requestedSettlementDate;
	private int units;
	private double pricePerUnit;
	
	private LocalDate realSettlementDate;
	private double usdAmountOfTrade;
	
	/**
	 * @param entity
	 * @param operation
	 * @param agreedFx
	 * @param currency
	 * @param instructionDate
	 * @param requestedSettlementDate
	 * @param units
	 * @param pricePerUnit
	 */
	public Instruction(String entity, String operation, double agreedFx, String currency, LocalDate instructionDate,
			LocalDate requestedSettlementDate, int units, double pricePerUnit) {
		this.entity = entity;
		this.operation = operation;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.requestedSettlementDate = requestedSettlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}
	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @return the agreedFx
	 */
	public double getAgreedFx() {
		return agreedFx;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @return the instructionDate
	 */
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @return the requestedSettlementDate
	 */
	public LocalDate getRequestedSettlementDate() {
		return requestedSettlementDate;
	}
	/**
	 * @return the realSettlementDate
	 */
	public LocalDate getRealSettlementDate() {
		return realSettlementDate;
	}
	/**
	 * @param realSettlementDate the realSettlementDate to set
	 */
	public void setRealSettlementDate(LocalDate realSettlementDate) {
		this.realSettlementDate = realSettlementDate;
	}
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}
	/**
	 * @return the pricePerUnit
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @return the usdAmountOfTrade
	 */
	public double getUsdAmountOfTrade() {
		return usdAmountOfTrade;
	}
	/**
	 * @param usdAmountOfTrade the usdAmountOfTrade to set
	 */
	public void setUsdAmountOfTrade(double usdAmountOfTrade) {
		this.usdAmountOfTrade = usdAmountOfTrade;
	}
}
