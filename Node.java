package avlTree;

public class Node<E extends Comparable<E>> {
	protected E data;
	protected Node<E> left;
	protected Node<E> right;
	
	public Node(E data) {
		this(data, null, null);
	}
	
	public Node(E data, Node<E> left, Node<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		String s = this.data.toString() + " ";
		return s;
	}
}