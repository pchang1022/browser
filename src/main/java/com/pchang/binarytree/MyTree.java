package com.pchang.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class MyTree {
	private Node root;
	private int numNode;
	private static final String LEFT = "left";
	private static final String RIGHT = "right";

	// size()

	MyTree(Node node) {
		root = node;
	}

	public int size() {
		if (root == null) {
			return 0;
		}
		return size(root);
	}

	public int size(Node node) {
		if (node != null) {
			System.out.printf("data: %d%n", node.data);
		}
		if (node == null) {
			return 0;
		}
		return 1 + size(node.left) + size(node.right);
	}

	public int maxDepth() {
		if (root == null) {
			return 0;
		}
		return maxDepth(root);
	}

	public int maxDepth(Node node) {
		if (node != null) {
			System.out.println("node.data = " + node.data);
		}
		if (node == null) {
			return 0;
		}
		int lDepth = maxDepth(node.left);
		int rDepth = maxDepth(node.right);
		return (Math.max(lDepth, rDepth) + 1);
		// return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
	}

	public void addChild(String position, int parentValue, int childValue) throws InvalidTreeConstructionException {
		System.out.println("\n*** Enter addChild: calling find!!!");

		Node parentNode = this.find(parentValue, position);
		StringBuffer errorMessage = new StringBuffer();
		if (parentNode == null) {
			errorMessage.append("\n*** Exit addChild--- Error: Either no node with value '").append(parentValue).append("' is found or it already has a ").append(position).append(" subtree.");
			printBuildTree(errorMessage.toString());
			throw new InvalidTreeConstructionException(errorMessage.toString());
		}
		errorMessage.append("Node '").append(parentValue).append("' already has a ").append(position).append(" subtree.");
		StringBuffer successMessage = new StringBuffer("\n---added ").append(childValue).append(" to ").append(position).append(" of ").append(parentValue);
		if (position.equals(RIGHT)) {
			if (parentNode.right != null) { // This is not likely
				printBuildTree(errorMessage.toString());
				throw new InvalidTreeConstructionException(errorMessage.toString());
			} else {
				parentNode.right = new Node(childValue);
				printBuildTree(successMessage.toString());
			}
		} else {
			if (parentNode.left != null) {
				printBuildTree(errorMessage.toString());
				throw new InvalidTreeConstructionException(errorMessage.toString());
			} else {
				parentNode.left = new Node(childValue);
				printBuildTree(successMessage.toString());
			}
		}
		System.out.println("*** Exit addChild");
	}

	private void printBuildTree(String message) {
		// System.out.println(message);
	}

	public Node find(int searchValue, String position) {
		Node target = findNode(this.root, searchValue, position, "");
		return target;
	}

	// For this purpose, current is always the root node
	private Node findNode(Node current, int searchValue, String position, String indent) {
		Node target = null;
		if (current != null) {
			printRecursion(indent + "findNode current is " + current.toString() + "-> " + current.data + " searchValue = " + searchValue + " position = " + position);
		} else {
			printRecursion(indent + "findNode current is null " + " searchValue = " + searchValue + " position = " + position);
		}
		indent += "  ";
		if (current == null) {
			printRecursion(indent + " return current is null");
			return null;

		} else if (current.data == searchValue) {
			// check to see if the branch we want is populated
			printRecursion(indent + " ******************* found! " + current.toString());

			if (position.equals(LEFT)) {
				if (current.left != null) {
					printRecursion(indent + " left already populated ... need to look at child - calling findNode with current.left");
					target = findNode(current.left, searchValue, position, indent);
					if (target == null) {
						printRecursion(indent + " left already populated ... need to look at child - calling findNode with current.right");
						target = findNode(current.right, searchValue, position, indent);
					}
				} else {
					printRecursion(indent + " return current: " + current.toString());
					return current;
				}
			} else {
				if (current.right != null) {
					printRecursion(indent + " left already populated ... need to look at child - calling findNode with current.left");
					target = findNode(current.left, searchValue, position, indent);
					if (target == null) {
						printRecursion(indent + " left already populated ... need to look at child - calling findNode with current.right");
						target = findNode(current.right, searchValue, position, indent);
					}
				} else {
					printRecursion(indent + " return current: " + current.toString());
					return current;
				}
			}
		} else {
			printRecursion(indent + " - calling findNode with current.left");
			target = findNode(current.left, searchValue, position, indent);
			if (target == null) {
				printRecursion(indent + " - calling findNode with current.right");
				target = findNode(current.right, searchValue, position, indent);
			}
		}

		if (target != null) {
			printRecursion(indent + " return target: " + target.toString());
		} else {
			printRecursion(indent + " return target is null ");
		}

		return target;
	}

	private void printRecursion(String message) {
		// System.out.println(message);
	}

	public void printLevelOrder() {
		System.out.println("Print Tree in Level Order");
		if (root == null) {
			System.out.println("Nothing to print!");
			return;
		}
		Queue<Node> cue = new LinkedList<Node>();
		cue.add(root);
		int count = cue.size();
		StringBuffer levelOuput = new StringBuffer("   ");
		while (count != 0) {
			String separator = count == 1 ? "" : ",";
			levelOuput.append(cue.peek().data).append(separator);
			if (cue.peek().left != null) {
				cue.add(cue.peek().left);
			}
			if (cue.peek().right != null) {
				cue.add(cue.peek().right);
			}
			cue.remove();
			count = count - 1;
			if (count == 0) {
				System.out.println(levelOuput);
				levelOuput = new StringBuffer("   ");
				count = cue.size();
			}
		}
	}

	/* Custom exception */
	class InvalidTreeConstructionException extends Exception {
		private static final long serialVersionUID = -1209443412796222L;

		public InvalidTreeConstructionException(String message) {
			super(message);
		}
	}
}
