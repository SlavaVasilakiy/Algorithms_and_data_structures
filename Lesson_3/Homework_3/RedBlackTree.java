public class RedBlackTree<T extends Comparable<T>> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node<T> root;

	private static class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;
		boolean color;

		public Node(T data, boolean color) {
			this.data = data;
			this.color = color;
		}
	}

	public void add(T data) {
		root = add(root, data);
		root.color = BLACK;
	}

	private Node<T> add(Node<T> node, T data) {
		if (node == null) {
			return new Node<>(data, RED);
		}

		if (data.compareTo(node.data) < 0) {
			node.left = add(node.left, data);
		} else if (data.compareTo(node.data) > 0) {
			node.right = add(node.right, data);
		}

		if (isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}

		return node;
	}

	private boolean isRed(Node<T> node) {
		if (node == null) {
			return false;
		}
		return node.color == RED;
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	private void flipColors(Node<T> node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}

	public void printTree() {
		if (root == null) {
			System.out.println("Дерево пустое.");
			return;
		}
		printTree(root, "", false);
	}

	private void printTree(Node<T> node, String prefix, boolean isLeft) {
		if (node == null) {
			return;
		}

		System.out.print(prefix);
		System.out.print(isLeft ? "├──" : "└──");
		System.out.print(node.color == RED ? ANSI_RED : ANSI_BLACK);
		System.out.print(node.data.toString());
		System.out.println(ANSI_RESET);

		printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
		printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
	}

	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RESET = "\u001B[0m";

}
