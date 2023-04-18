public class Main {
	public static void main(String[] args) {
		RedBlackTree<Integer> tree = new RedBlackTree<>();

		tree.add(5);
		tree.add(39);
		tree.add(29);
		tree.add(10);
		tree.add(9);
		tree.add(35);
		tree.add(7);
		tree.add(14);
		tree.add(20);

		tree.printTree();
	}
}

