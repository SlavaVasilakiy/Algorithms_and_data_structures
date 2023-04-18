import java.util.Random;

public class BalancedTreeBuild {
	public static void main(String[] args) {
		printTree(treeBalanceTree(9), "");

		Node root = null;

		root = Node.insert(root, 50);
		root = Node.insert(root, 30);
		root = Node.insert(root, 20);
		root = Node.insert(root, 40);
		root = Node.insert(root, 70);
		root = Node.insert(root, 60);
		root = Node.insert(root, 80);

		System.out.println();
		printTree(root, "");
	}

	static void printTree(Node root, String sp) {
		if (root != null) {
			System.out.println(sp + root.value);
			printTree(root.left, sp + " ");
			printTree(root.right, sp + " ");
		}
	}

	static Node treeBalanceTree(int n) {
		Node node = null;

		if(n == 0){
			return node;
		} else {
			int nl = n / 2;
			int nr = n - nl - 1;
			node = new Node(new Random().nextInt(100));
			node.left = treeBalanceTree(nl);
			node.right = treeBalanceTree(nr);
		}

		return node;
	}
}

class Node {
	Node left;
	Node right;
	int value;

	public Node(int value) {
		this.value = value;
	}

	static Node insert(Node root, int newValue) {
		if (root == null) {
			return new Node(newValue);
		}

		if (newValue < root.value) {
			root.left = insert(root.left, newValue);
		} else {
			root.right = insert(root.right, newValue);
		}

		int balance = getBalance(root);
		if (balance > 1 && newValue < root.left.value) {
			return rightRotate(root);
		}
		if (balance < -1 && newValue > root.right.value) {
			return leftRotate(root);
		}
		if (balance > 1 && newValue > root.left.value) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		if (balance < -1 && newValue < root.right.value) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	static Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		return y;
	}

	static Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		return x;
	}

	static int height(Node node) {
		if (node == null) {
			return 0;
		}

		return 1 + Math.max(height(node.left), height(node.right));
	}

	static int getBalance(Node node) {
		if (node == null) {
			return 0;
		}

		return height(node.left) - height(node.right);
	}

}
