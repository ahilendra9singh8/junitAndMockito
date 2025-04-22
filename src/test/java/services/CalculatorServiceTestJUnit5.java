package services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorServiceTestJUnit5 {
	
	@Test
	public void addTwoNumbersTest() {
		
		int actualResult = CalculatorService.addTwoNumbers(12, 12);
		int expectedResult = 24;
		Assertions.assertEquals(expectedResult, actualResult);
		
	}
	
//	@Test
//	public void addAnyNumbersTest() {
//		
//	}

}
