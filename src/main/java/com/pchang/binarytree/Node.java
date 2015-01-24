package com.pchang.binarytree;


public class Node {
	public int data;
	public Node left;
	public Node right;

	public Node(int aData) {
		data = aData;
		left = null;
		right = null;
	}

	public Node(int aData, Node leftNode, Node rightNode) {
		data = aData;
		left = leftNode;
		right = rightNode;
	}//



}
