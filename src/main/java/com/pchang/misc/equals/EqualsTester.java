package com.pchang.misc.equals;

public class EqualsTester {

	/*
	 * Contract when overriding equas() - object referred to as x, y, z ..., and is for 'any non-null reference of x, y, z ..."
	 * 1. Reflexive = x.equals(x) must return true;
	 * 2. Symmetric = x.equals(y) must return true if and only if y.equals(x) returns true
	 * 3. Transitive = if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) must return true;
	 * 4. Consistent = multiple invocation of x.equals(y) must consistently returns true, or consistently returns false
	 * 5. For any non-null reference value x, x.equals(null) must return false
	 */

	public static void main(String[] args) {
		// compareCaseInsensitiveString ();
		Integer a = new Integer(100);
		Integer b = new Integer(100);
		System.out.println(new Integer(100) == new Integer(100)); // false
		System.out.println(a == b); // false
		System.out.println(a == 100); // true

		comparePoint();
	}

	private static void comparePoint() {

		ColorPoint p2 = new ColorPoint(1, 2, Color.BLACK);

		Point p1 = new Point(1, 2);
		// ColorPoint p = new ColorPoint(1, 2, Color.RED);

		ColorPoint p3 = new ColorPoint(1, 2, Color.BLACK);


		System.out.printf("p1 equals p2? %s %n", p1.equals(p2));
		System.out.printf("p2 equals p3? %s %n", p2.equals(p3));
		System.out.printf("p1 equals p3? %s %n", p1.equals(p3)); // check transitivity


		if (p1.equals(p2) && p2.equals(p1) || (!p1.equals(p2) && !p2.equals(p1))) {
			System.out.println("symmetry check ok!");
		} else {
			System.out.println("Violated symmetry!");
		}

	}

	private static void compareCaseInsensitiveString() {
		CaseInsensitiveString cis = new CaseInsensitiveString("Polish");

		CaseInsensitiveString cis2 = new CaseInsensitiveString("polish");

		System.out.println("cis.equals(s) ? " + cis.equals(cis2));

		System.out.println("s.equals(cis) ? " + cis2.equals(cis));
	}

}
