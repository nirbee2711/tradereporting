package com.jpmorgan.techtest.tradereporting.main;

import java.util.List;

import com.jpmorgan.techtest.tradereporting.data.Instruction;
import com.jpmorgan.techtest.tradereporting.report.ReportExecuter;
import com.jpmorgan.techtest.tradereporting.testdata.TestDataGenerator;

public class Main {
	
	public static void main(String[] args) {
		//get the Data
		List<Instruction> listData = TestDataGenerator.createTestData();
		//can be later changed to read from db, file, etc.
		
		//Display the report by passing the list of data
		ReportExecuter reportExecuter = new ReportExecuter();
		reportExecuter.createReport(listData);
	}
}
