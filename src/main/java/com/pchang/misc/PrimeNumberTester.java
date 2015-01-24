package com.pchang.misc;

public class PrimeNumberTester {

	public static void main(String[] args) {
		// http://stackoverflow.com/questions/295579/fastest-way-to-determine-if-an-integers-square-root-is-an-integer
		// All perfect squares end in 1, 4, 5, 6, 9 or 00 (i.e. Even number of zeros). Therefore, a number that ends in 2, 3, 7 or 8 is not a perfect square.

		Long testNumber = 812L;
		double primeD = sqrt(testNumber); // 6.08276253029822
		double primeD2 = Math.sqrt(testNumber.doubleValue()); // 6.082762530298219
		System.out.println("primeD2: " + primeD2);
		System.out.println("abs: " + Math.abs(primeD2));
		System.out.println("floor: " + Math.floor(primeD2));
		System.out.println("ceil: " + Math.ceil(primeD2));

		// check if a number is a perfect squqre
		// isPerfectSquare(testNumber);
		isPrime(testNumber);

	}

	public static double sqrt(long number) {
		double t;
		double squareRoot = number / 2;
		do {
			t = squareRoot;
			squareRoot = (t + (number / t)) / 2;
		} while ((t - squareRoot) != 0);
		return squareRoot;
	}

	public static boolean isPerfectSquare(Long inputNumber) {
		// All perfect squares end in 1, 4, 5, 6, 9 or 00 (i.e. Even number of zeros). Therefore, a number that ends in 2, 3, 7 or 8 (0 -single 0) is not a perfect square.
		String input = Long.toString(inputNumber);
		// look at last char
		char last = input.charAt(input.length() - 1);
		char last2 = input.charAt(input.length() - 2);
		boolean isPerfect = false;

		if (last == '2' || last == '3' || last == '7' || last == '8' || (last == '0' && last2 != '0')) {
			System.out.printf("%s does not have a perfect square", input);
		} else {
			System.out.printf("more work is needed %s ----------- %n", input);
			long tst = (long) (Math.sqrt(inputNumber) + 0.5);
			isPerfect = tst * tst == inputNumber;
			// System.out.println(" (long) (Math.sqrt(prime) + 0.5 ");
			// System.out.printf("Method 1: a perfect square? %b ", tst * tst == inputNumber);
			System.out.printf("Method 1: %d is a perfect square? %s %n", inputNumber, isPerfect);

			// another way???
			double floor = Math.floor(Math.sqrt(inputNumber));
			double sqrt = Math.sqrt(inputNumber);
			System.out.println("       Math.floor(sqrt) vs sqrt = " + floor + " - " + sqrt);
			if (Math.floor(sqrt) - sqrt == 0) {
				System.out.printf("Method 2: %d is a perfect square%n", inputNumber);
			} else {
				System.out.printf("Method 2: %d is NOT a perfect square%n", inputNumber);
			}
		}
		return isPerfect;
	}

	public static void isPrime(long n) {

		if (Math.abs(n) == 2) {
			System.out.printf("%d is prime", n);
			return;
		}
		if (n % 2 == 0) {
			System.out.printf("%d is not prime - divisible by 2", n);
			return;
		}
		// then see if the number has an divisor other than it self
		// check from 3 to sqrt(n)
		double limit = Math.sqrt(n);
		boolean found = true;
		for (int i = 3; i < limit; i += 2) {
			System.out.printf("%d < %.2f%n", i, limit);
			if (n % i == 0) {
				System.out.printf("%d is not prime", n);
				found = false;
				break;
			}
		}
		if (found) {
			System.out.printf("%d is prime", n);
		}

	}

	public static boolean isPrimeNumberZZZ(Long inputNumber) {
		// double d = 1234.56;
		// long x = Math.round(d);
		//
		double numberD = Math.sqrt(inputNumber.doubleValue());
		long nextPrime = (long) Math.ceil(numberD);

		long mod = inputNumber % 2;
		boolean isEven = mod == 0 ? true : false;
		boolean isPrime = false;
		long testNumber = 2;
		while (true) {
			if (isEven) {
				testNumber = testNumber + 1;
			} else {
				if (testNumber > nextPrime) {
					break;
				} else {

				}
			}
		}

		return isPrime;
	}
}
