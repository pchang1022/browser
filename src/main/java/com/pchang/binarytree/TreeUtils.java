package com.pchang.binarytree;

import com.pchang.binarytree.MyTree.InvalidTreeConstructionException;


public class TreeUtils {
	public enum DemoTree {
		DEFAULT_TREE, TREE_WITH_NON_UNIQUE_VALUE, BALANCED_TREE_5, ONE_SIDED_TREE_15
	}

	private static final String LEFT = "left";
	private static final String RIGHT = "right";
	
	public static void main(String[] args) {
		MyTree tree = build123();
		System.out.println("size of tree: " + tree.size() + "\n");

		MyTree tree123 = new MyTree(new Node(5));
		try {
			createUnOrderedTree(tree123, DemoTree.DEFAULT_TREE);
			tree123.printLevelOrder();
			int height = tree123.maxDepth();
			System.out.printf("Height: %d%n", height);
		} catch (InvalidTreeConstructionException e) {
			e.printStackTrace();
		}

	}

	public static MyTree build123() {

		Node node = new Node(2, new Node(1), new Node(3));
		MyTree tree123 = new MyTree(node);
		return tree123;
	}
	
	public static void createUnOrderedTree(MyTree tree, DemoTree type) throws InvalidTreeConstructionException {
		try {
			switch (type) {
				case DEFAULT_TREE: {
					tree.addChild(LEFT, 5, 3);
					tree.addChild(RIGHT, 5, 1);
					tree.addChild(LEFT, 3, 9);
					tree.addChild(LEFT, 1, 4);
					tree.addChild(LEFT, 4, 2);
					tree.addChild(RIGHT, 1, 5);
					break;
				}
				case TREE_WITH_NON_UNIQUE_VALUE: {
					// root = new Node(5);
					tree.addChild(LEFT, 5, 3);
					tree.addChild(RIGHT, 5, 1);

					tree.addChild(LEFT, 3, 9);
					tree.addChild(LEFT, 1, 4);
					tree.addChild(RIGHT, 1, 5);

					tree.addChild(LEFT, 4, 2);

					tree.addChild(LEFT, 5, 500);
					tree.addChild(RIGHT, 5, 1500);

					tree.addChild(RIGHT, 1500, 1);
					tree.addChild(RIGHT, 1, 999);
					break;
				}
				case BALANCED_TREE_5: {
					// root = new Node(1);
					tree.addChild(LEFT, 1, 2);
					tree.addChild(RIGHT, 1, 3);

					tree.addChild(LEFT, 2, 4);
					tree.addChild(RIGHT, 2, 5);

					tree.addChild(LEFT, 3, 6);
					tree.addChild(RIGHT, 3, 7);

					tree.addChild(LEFT, 4, 8);
					tree.addChild(RIGHT, 4, 9);

					tree.addChild(LEFT, 5, 10);
					tree.addChild(RIGHT, 5, 11);

					tree.addChild(LEFT, 6, 12);
					tree.addChild(RIGHT, 6, 13);

					tree.addChild(LEFT, 7, 14);
					tree.addChild(RIGHT, 7, 15);
					// 8,9,10,11,12,13,14,15
					tree.addChild(LEFT, 8, 16);
					tree.addChild(RIGHT, 8, 17);

					tree.addChild(LEFT, 9, 18);
					tree.addChild(RIGHT, 9, 19);

					tree.addChild(LEFT, 10, 20);
					tree.addChild(RIGHT, 10, 21);

					tree.addChild(LEFT, 11, 20);
					tree.addChild(RIGHT, 11, 21);

					tree.addChild(LEFT, 12, 20);
					tree.addChild(RIGHT, 12, 21);

					tree.addChild(LEFT, 13, 20);
					tree.addChild(RIGHT, 13, 21);

					tree.addChild(LEFT, 14, 20);
					tree.addChild(RIGHT, 14, 21);

					tree.addChild(LEFT, 15, 20);
					tree.addChild(RIGHT, 15, 21);
					break;
				}
				case ONE_SIDED_TREE_15: {
					// root = new Node(1);
					tree.addChild(LEFT, 1, 2);

					tree.addChild(LEFT, 2, 3);
					tree.addChild(LEFT, 3, 4);
					tree.addChild(LEFT, 4, 5);
					tree.addChild(LEFT, 5, 6);
					tree.addChild(LEFT, 6, 7);

					tree.addChild(LEFT, 7, 8);
					tree.addChild(LEFT, 8, 9);
					tree.addChild(LEFT, 9, 10);
					tree.addChild(LEFT, 10, 11);

					tree.addChild(LEFT, 11, 12);
					tree.addChild(LEFT, 12, 13);
					tree.addChild(LEFT, 13, 14);
					tree.addChild(LEFT, 14, 15);
					break;
				}
			}
		} catch (InvalidTreeConstructionException e) {
			throw e;
		}
	}


}
