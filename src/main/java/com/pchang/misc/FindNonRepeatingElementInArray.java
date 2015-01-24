package com.pchang.misc;

import java.lang.reflect.Field;

public class FindNonRepeatingElementInArray {

	public static void main(String[] args)throws Exception{ {

		
		Integer a = 2;
        Field valField = a.getClass().getDeclaredField("value");
        valField.setAccessible(true);
			valField.setInt(a, 40);
		Integer b = 2;
		Integer c = 1;

		System.out.println("b+c : " + (b + c));

		int m[] = { 1, 2, 3, 4, 4, 2, 1, 8, 8 };

		int result = m[0];
		for (int i = 1; i < m.length; i++) {
			result ^= m[i];
			System.out.println(i + " current element: " + m[i] + " -- result : " + result);
		}
		System.out.println(result);
	}

}
}