package avlTree;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class BSTree<E extends Comparable<E>> {
	class Node {
		protected E data;
		protected Node left,right;
		
		public Node(E data) {
			this(data,null,null);
		}

		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			return ""+this.data;
		}
		
		public E getData() {
			return data;
		}
	}
	
	protected Node root;

	public BSTree() {
		this.root = null;
	}
	
	public void insert(E x) throws ItemDuplicated {
		this.root = insertRec(x, this.root);
		
	}

	protected Node insertRec(E x, Node actual) throws ItemDuplicated {
		Node res = actual;
		if (actual == null) {
			res = new Node(x);
		} else { // buscamos el lugar para inserción
			int resC = actual.data.compareTo(x);
			if (resC == 0)
				throw new ItemDuplicated(x + "esta duplcado");
			if (resC < 0)
				res.right = insertRec(x, actual.right);
			else
				res.left = insertRec(x, actual.left);
		}
		return res;
	}
	
	
	public E search(E x)  {
		Node res = searchRec(x, this.root);
		if (res == null) {
			System.out.println("El dato " + x + "no esta");
			return null;
		}
		return res.data;
	}

	protected Node searchRec(E x, Node n) {
		if (n == null)
			return null;
		else {
			int resC = n.data.compareTo(x);
			if (resC < 0)
				return searchRec(x, n.right);
			else if (resC > 0)
				return searchRec(x, n.left);
			else
				return n;
		}
	}
	
	public void remove(E x) throws ItemNotFound {
	    this.root = removeRec(x, this.root);
	}

	protected Node removeRec(E x, Node actual) throws ItemNotFound {
	    if (actual == null) {
	        throw new ItemNotFound("El dato " + x + " no se encontró");
	    }

	    int resC = actual.data.compareTo(x);
	    if (resC < 0) {
	        actual.right = removeRec(x, actual.right);
	    } else if (resC > 0) {
	        actual.left = removeRec(x, actual.left);
	    } else {
	        // Caso 1: El nodo es una hoja
	        if (actual.left == null && actual.right == null) {
	            actual = null;
	        }
	        // Caso 2: El nodo tiene un hijo
	        else if (actual.left == null) {
	            actual = actual.right;
	        } else if (actual.right == null) {
	            actual = actual.left;
	        }
	        // Caso 3: El nodo tiene dos hijos
	        else {
	            Node minNode = findMin(actual.right);
	            actual.data = minNode.data;
	            actual.right = removeRec(minNode.data, actual.right);
	        }
	    }

	    return actual;
	}

	protected Node findMin(Node actual) {
	    while (actual.left != null) {
	        actual = actual.left;
	    }
	    return actual;
	}
	
	public String postOrden() {
		if (this.root != null)
			return postOrden(this.root);
		else
			return "*";
	}

	protected String postOrden(Node actual){
		String res = "";
		if (actual.left != null) res += postOrden(actual.left);
		if (actual.right != null) res += postOrden(actual.right);
		return res + actual.toString() + ", ";
	}
	
	public String preOrden() {
	    if (this.root != null)
	        return preOrden(this.root);
	    else
	        return "*";
	}

	protected String preOrden(Node actual) {
	    String res = actual.toString() + ", ";
	    if (actual.left != null)
	        res += preOrden(actual.left);
	    if (actual.right != null)
	        res += preOrden(actual.right);
	    return res;
	}
	
	public String inOrden() {
	    if (this.root != null)
	        return inOrden(this.root);
	    else
	        return "*";
	}

	protected String inOrden(Node actual) {
	    String res = "";
	    if (actual.left != null)
	        res += inOrden(actual.left);
	    res += actual.toString() + ", ";
	    if (actual.right != null)
	        res += inOrden(actual.right);
	    return res;
	}

	public String toString() {
		return "INORDEN "+inOrden() +"\n" + "PREORDEN "+preOrden()+"\n"+"POSTORDEN "+postOrden();
	}

	public int countNonLeafNodes() {
	    return countNonLeafNodes(this.root);
	}

	protected int countNonLeafNodes(Node actual) {
	    if (actual == null || (actual.left == null && actual.right == null)) {
	        return 0;
	    } else {
	        return 1 + countNonLeafNodes(actual.left) + countNonLeafNodes(actual.right);
	    }
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	protected Node searchNode(E x, Node actual) {
	    if (actual == null) {
	        return null;
	    } else {
	        int resC = actual.data.compareTo(x);
	        if (resC < 0) {
	            return searchNode(x, actual.right);
	        } else if (resC > 0) {
	            return searchNode(x, actual.left);
	        } else {
	            return actual;
	        }
	    }
	}
	
	public int getNodeHeight(E x) {
	    Node node = searchNode(x, this.root);
	    if (node == null) {
	        return -1; // Indica que el nodo no existe en el árbol
	    }
	    return getNodeHeight(node);
	}

	protected int getNodeHeight(Node node) {
	    return calculateNodeHeight(node) - 1;
	}

	protected int calculateNodeHeight(Node actual) {
	    if (actual == null) {
	        return 0;
	    } else {
	        int leftHeight = calculateNodeHeight(actual.left);
	        int rightHeight = calculateNodeHeight(actual.right);
	        return 1 + Math.max(leftHeight, rightHeight);
	    }
	}
	
	public void preOrderIterative() {
	    if (this.root == null) {
	        return;
	    }

	    Stack<Node> stack = new Stack<>();
	    stack.push(this.root);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();
	        System.out.print(current.data.toString() + ", ");

	        if (current.right != null) {
	            stack.push(current.right);
	        }
	        if (current.left != null) {
	            stack.push(current.left);
	        }
	    }
	}
	
	public int calculateArea() {
	    int leafCount = countLeaves(this.root);
	    int height = calculateTreeHeight(this.root);
	    return leafCount * height;
	}

	protected int countLeaves(Node actual) {
	    if (actual == null) {
	        return 0;
	    } else if (actual.left == null && actual.right == null) {
	        return 1;
	    } else {
	        return countLeaves(actual.left) + countLeaves(actual.right);
	    }
	}

	protected int calculateTreeHeight(Node actual) {
	    if (actual == null) {
	        return 0;
	    } else {
	        int leftHeight = calculateTreeHeight(actual.left);
	        int rightHeight = calculateTreeHeight(actual.right);
	        return 1 + Math.max(leftHeight, rightHeight);
	    }
	}
	
	public Node getMinimumNode() {
	    if (this.root == null) {
	        return null; // El árbol está vacío
	    }

	    Node current = this.root;
	    while (current.left != null) {
	        current = current.left;
	    }

	    return current;
	}
	
	public Node getMaximumNode() {
	    if (this.root == null) {
	        return null; // El árbol está vacío
	    }

	    Node current = this.root;
	    while (current.right != null) {
	        current = current.right;
	    }

	    return current;
	}
	
	public void parenthesize() {
	    if (this.root != null) {
	        parenthesize(this.root, 0);
	    }
	}

	protected void parenthesize(Node node, int level) {
	    if (node != null) {
	        for (int i = 0; i < level; i++) {
	            System.out.print(" ");
	        }
	        
	        System.out.println(node.data.toString()+"{");

	        parenthesize(node.left, level + 1);
	        parenthesize(node.right, level + 1);

	    }
	}
	
	public int countNodes() {
	    if (root == null) {
	        return 0;
	    }

	    int count = 0;
	    Node current = root;
	    Node prev = null;

	    while (current != null) {
	        if (current.left == null) {
	            count++;
	            current = current.right;
	        } else {
	            prev = current.left;
	            while (prev.right != null && prev.right != current) {
	                prev = prev.right;
	            }
	            
	            if (prev.right == null) {
	                prev.right = current;
	                current = current.left;
	            } else {
	                prev.right = null;
	                count++;
	                current = current.right;
	            }
	        }
	    }

	    return count;
	}

	public String recorridoPorNiveles() {
        int altura = calculateNodeHeight(this.root);
        StringBuilder sb = new StringBuilder();
        sb.append("Altura: ").append(altura).append("\n");

        for (int nivel = 0; nivel < altura; nivel++) {
            List<String> resultado = new ArrayList<>();
            recorrerNivel(this.root, nivel, resultado);
            sb.append("Nivel ").append(nivel).append(": ");
            sb.append(String.join(", ", resultado));
            sb.append("\n");
        }

        return sb.toString();
    }

	private void recorrerNivel(Node nodo, int nivel, List<String> resultado) {
		if (nodo == null) return;
		if (nivel == 0) {
			resultado.add(nodo.data.toString());
		} else {
			recorrerNivel(nodo.left, nivel - 1, resultado);
			recorrerNivel(nodo.right, nivel - 1, resultado);
		}
	}
	
}