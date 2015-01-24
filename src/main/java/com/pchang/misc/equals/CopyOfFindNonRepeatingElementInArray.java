package com.pchang.misc.equals;

public class CopyOfFindNonRepeatingElementInArray {

	public static void main(String[] args) {

		int m[] = { 1, 2, 3, 4, 4, 2, 1, 8, 8 };

		int result = m[0];
		for (int i = 1; i < m.length; i++) {
			result ^= m[i];
			System.out.println(i + " current element: " + m[i] + " -- result : " + result);
		}
		System.out.println(result);
	}

}
