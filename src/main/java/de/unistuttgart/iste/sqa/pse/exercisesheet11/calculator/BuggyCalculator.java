package de.unistuttgart.iste.sqa.pse.exercisesheet11.calculator;

/**
 * 
 * A Calculator. It has Bugs.
 *
 */
final class BuggyCalculator implements Calculator {

	@Override
	public int add(final int summand1, final int summand2) {
		return summand1 + summand2;
	}

	@Override
	public int divide(final int dividend, final int divisor) {	
		if (dividend == Integer.MIN_VALUE || divisor == Integer.MIN_VALUE) {
			throw new IllegalArgumentException();
		}
		if (divisor == 0) {
			return 0;
		}
		int posDividend = Math.abs(dividend);
		int posDivisor = Math.abs(divisor);
		int posQuotient =  posDividend / posDivisor;
		int posResidual = posDividend % posDivisor;
		
		if (2 * posResidual > posDivisor || 2 * posResidual < 0) { 
			posQuotient++; 
		}
		
		return posQuotient * getResultSign(dividend, divisor);
	}
	
	/*
	 * @ensures dividend < 0 & divisor < 0 ==> \result = 1
	 * 
	 * @ensures dividend > 0 & divisor > 0 ==> \result = 1
	 * 
	 * @ensures dividend < 0 & divisor > 0 ==> \result = -1
	 * 
	 * @ensures dividend > 0 & divisor < 0 ==> \result = -1
	 */
	/**
	 * calculates the sign of the result when dividing or multiplying to integer
	 * numbers
	 * 
	 * @param n the first number
	 * @param m the second number
	 * @return -1 if the result is smaller than 0, 1 otherwise
	 */
	private int getResultSign(final int dividend, final int divisor) {
		if ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * 
	 * @ensures summand1 + summand2 < Integer.MAX_Value 
	 * 
	 * calculates the sum of two integer values 
	 * 
	 * if summand1 + summand2 < Integer.MAX_VALUE || summand1 + summand2 > Integer.MIN_VALUE  else throws an 
	 * Arithmetic Exception 
	 * 
	 * @param summand1 first summand
	 * @param summand2 second summand
	 * 
	 * @return sum of summand1 and summand2 
	 * 
	 */
	public int addExact (int summand1, int summand2)  {
		return 0;
	}
	
	/**
	 * calculates absolute value of a given integer or throws an Arithmetic Exception
	 * 
	 * @param integer value
	 * @return absolute value 
	 */
	public int abs (int value)  {
		return 0;
	}
	
	
}
