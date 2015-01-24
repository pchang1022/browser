package com.pchang.misc;

import java.util.Scanner;
import java.util.Stack;

public class CompactStringInOnePass {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {

			System.out.println("Enter a string with  (enter -1 to exit): ");
			String str = s.nextLine();
			if (str == "-1") break;

			compactString(str);

		}
		s.close();

	}

	public static void compactString(String s) {
		Stack<Character> st = new Stack<Character>();
		StringBuffer sb = new StringBuffer("");
		char[] cAry = s.toCharArray();
		for (Character c : cAry) {
			if (c == ' ') {
				st.push(c);
			} else {
				if (!st.isEmpty()) {
					sb.append(st.pop());
					st.clear();
				}
				sb.append(c);
			}
		}

		System.out.println("compacted string: " + sb.toString());
	}

}
