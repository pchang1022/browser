package com.pchang.misc.hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

import com.pchang.misc.equals.Dog;

public class HashCollectionTester {

	public static void main(String[] args) {

		useHashMap();


	}

	public static void useHashMap() {
		HashMap<Dog, Integer> hashmap = new HashMap<Dog, Integer>();

		hashmap.put(new Dog("red"), 10);
		hashmap.put(new Dog("black"), 15);
		hashmap.put(new Dog("white"), 5);
		hashmap.put(new Dog("white"), 20);

		System.out.println("size of hashmap:  " + hashmap.size());
		
		System.out.println("content of hashmap:  " + hashmap.toString());
		
		for (Entry<Dog, Integer> entry: hashmap.entrySet()) {
			// System.out.println("key:value = " + entry.getKey() + " : " + entry.getValue() + "hashcode = " + entry.hashCode());
			// String dogColor = StringUtils.leftPad(entry.getKey().toString(), 10); // ---key:value = red dog : 10 - hashCode = 1833642003

			// String dogColor = String.format("%10s", entry.getKey().toString());

			System.out.printf(" ---key:value = %10s: %6d - hashCode = %d%n", entry.getKey(), entry.getValue(), entry.hashCode());
		}
		
	}

}
