package com.pchang.misc.equals;

public final class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		if (s == null) throw new NullPointerException();
		this.s = s;
	}

	// Broken - violates symmetry!
	@Override
	public boolean equals(Object o) {
		boolean b1 = false;
		boolean b2 = false;
		if (o instanceof CaseInsensitiveString) {
			System.out.println("true");
			b1 = true;
			if (((CaseInsensitiveString) o).s.equalsIgnoreCase(s)) {
				System.out.println("true");
				b2 = true;
			}
		}



		System.out.printf("b1 && b2? %s - %s = %s : %n", b1, b2, b1 && b2);

		// return o instanceof CaseInsensitiveString && ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);

		if (o instanceof CaseInsensitiveString) {
			return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
		}
		if (o instanceof String) { // One-way interoperability!
			return s.equalsIgnoreCase((String) o);
		}
		return false;

	}

}
