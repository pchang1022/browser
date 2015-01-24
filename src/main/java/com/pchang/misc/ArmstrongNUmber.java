package com.pchang.misc;

import java.util.Scanner;

public class ArmstrongNUmber {

	// Example of some armstrong numbers: 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		while (true) {

			System.out.println("Enter a number (enter -1 to exit): ");
			int n = s.nextInt();
			if (n == -1) break;

			boolean isArm = isArmstrongNumber(n);
			System.out.println("is arm? " + isArm);
			// how many digits? cast it to string to char[]

			/*
			 * String str = Integer.toString(n);
			 * char[] charAry = str.toCharArray();
			 * double sum = 0;
			 * int size = charAry.length;
			 * for (int i = 0; i < size; i++) {
			 * char c = charAry[i];
			 * char zero = '0';
			 * int x = c - '0';
			 * String.valueOf(c);
			 * char cc = Character.forDigit(10, 16);
			 * int ccc = Character.getNumericValue(c);
			 * double tempD = Double.valueOf(charAry[i]);
			 * double temp = Math.pow(x, size);
			 * sum += temp;
			 * System.out.printf("digit # = %d, temp = %f ==> sum: %f%n", i, temp, sum);
			 * }
			 * long sumL = (long) sum;
			 * if (sumL == n) {
			 * System.out.printf("%d is an Armstrong number%n", n);
			 * } else {
			 * System.out.printf("%d is not an Armstrong number%n", n);
			 * }
			 */

		}
		s.close();
	}

	public static boolean isArmstrongNumber(int n) {
		boolean isArm = false;

		// need to know the # of digits
		// so get in to a string, then to a char array so we can get the individual number
		String s = Integer.toString(n);
		char[] cc = s.toCharArray();
		int d = cc.length; // number of digits
		double sum = 0;
		for (int i = 0; i < d; i++) {
			// cc[i] is a char, need to gets numeric value
			// ==> nth digit raised to the dth power, Java Math.pow uses double
			int num = Character.getNumericValue(cc[i]);
			double tempSum = Math.pow(num, d);
			sum = sum + tempSum;
		}

		// compare sum to original number - one is double and one is int
		// change double to long...
		long sumL = (short) sum;
		if (sumL == n) {
			isArm = true;
		}

		return isArm;
	}
}
