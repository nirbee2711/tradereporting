package com.jpmorgan.techtest.tradereporting.testdata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.techtest.tradereporting.data.Instruction;

public class TestDataGenerator {
	
	private TestDataGenerator() {
		
	}
	
	public static List<Instruction> createTestData() {
		Instruction i1 = new Instruction("bar1","B",0.5,"SGP",LocalDate.parse("2016-01-01"),LocalDate.parse("2016-01-02"),200,100.25);
		Instruction i2 = new Instruction("bar2","S",0.22,"AED",LocalDate.parse("2016-01-05"),LocalDate.parse("2016-01-07"),450,150.5);
		Instruction i3 = new Instruction("bar1","B",0.22,"INR",LocalDate.parse("2016-01-05"),LocalDate.parse("2016-01-02"),250,95.5);
		
		List<Instruction> instructionList = new ArrayList<>();
		instructionList.add(i1);
		instructionList.add(i2);
		instructionList.add(i3);
		
		return instructionList;
	}


}
