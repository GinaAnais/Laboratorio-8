package avlTree;

import Exceptions.ItemDuplicated;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;



public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
	class NodeAVL extends Node {
		protected int bf;

        public NodeAVL(E data) {
            this(data, null, null);
        }

        public NodeAVL(E data, Node left, Node right) {
            super(data, left, right);
            this.bf = 0;
        }

        public String toString() {
            return super.toString() + " (" + bf + ")";
        }
	}
	private boolean height; // indicador de cambio de altura
	
	public void insert(E data) throws ItemDuplicated {
		this.height = false;
		setRoot(insertRec(data,(NodeAVL)getRoot()));
	}
	
	public NodeAVL insertRec(E x, NodeAVL node) throws ItemDuplicated {
		NodeAVL fat = node;
		if (node == null) {
			this.height = true;
			fat = new NodeAVL(x);
		}
		else {
			int resC = node.data.compareTo(x);
			if(resC == 0)throw new ItemDuplicated(x+" ya se encuentra en el arbol...");
			if(resC < 0) {
				fat.right = insertRec(x, (NodeAVL)node.right);
				if(this.height)
					switch(fat.bf) {
					case -1: 
						fat.bf = 0;
						this.height = false;
						break;
					case 0:
						fat.bf = 1;
						this.height = true;
						break;
					case 1: //bf = 2
						fat = balanceToLeft(fat);
						this.height = false;
						break;

				}
			}
			else {
			    fat.left = insertRec(x, (NodeAVL) node.left);
			    if (this.height) {
			        switch (fat.bf) {
			            case 1:
			                fat.bf = 0;
			                this.height = false;
			                break;
			            case 0:
			                fat.bf = -1;
			                this.height = true;
			                break;
			            case -1: // bf = -2
			                fat = balanceToRight(fat);
			                this.height = false;
			                break;
			        }
			    }
			}
		}
		return fat;
	}
	
	private NodeAVL balanceToLeft(NodeAVL node) {
		NodeAVL hijo = (NodeAVL) node.right;
		if (hijo == null) return node;

		switch (hijo.bf) {
			case 0:
				// Caso especial en eliminación: rotación simple pero no cambia la altura
				node = rotateSL(node);
				node.bf = -1;
				((NodeAVL) node.left).bf = 1;
				this.height = false;
				break;

			case 1:
				// Rotación simple a la izquierda
				node = rotateSL(node);
				node.bf = 0;
				((NodeAVL) node.left).bf = 0;
				this.height = true;
				break;

			case -1:
				// Rotación doble: derecha sobre hijo, izquierda sobre nodo
				NodeAVL nieto = (NodeAVL) hijo.left;
				node.right = rotateSR(hijo);
				node = rotateSL(node);

				if (nieto != null) {
					switch (nieto.bf) {
						case -1:
							node.bf = 0;
							((NodeAVL) node.left).bf = 0;
							((NodeAVL) node.right).bf = 1;
							break;
						case 0:
							node.bf = 0;
							((NodeAVL) node.left).bf = 0;
							((NodeAVL) node.right).bf = 0;
							break;
						case 1:
							node.bf = 0;
							((NodeAVL) node.left).bf = -1;
							((NodeAVL) node.right).bf = 0;
							break;
					}
				} else {
					node.bf = 0;
					((NodeAVL) node.left).bf = 0;
					((NodeAVL) node.right).bf = 0;
				}

				this.height = true;
				break;
		}

		return node;
	}

	private NodeAVL balanceToRight(NodeAVL node) {
		NodeAVL hijo = (NodeAVL) node.left;
		if (hijo == null) return node;

		switch (hijo.bf) {
			case 0:
				// Caso especial en eliminación: rotación simple pero no cambia la altura
				node = rotateSR(node);
				node.bf = 1;
				((NodeAVL) node.right).bf = -1;
				this.height = false;
				break;

			case -1:
				// Rotación simple a la derecha
				node = rotateSR(node);
				node.bf = 0;
				((NodeAVL) node.right).bf = 0;
				this.height = true;
				break;

			case 1:
				// Rotación doble: izquierda sobre hijo, derecha sobre nodo
				NodeAVL nieto = (NodeAVL) hijo.right;
				node.left = rotateSL(hijo);
				node = rotateSR(node);

				if (nieto != null) {
					switch (nieto.bf) {
						case -1:
							node.bf = 0;
							((NodeAVL) node.left).bf = 0;
							((NodeAVL) node.right).bf = 1;
							break;
						case 0:
							node.bf = 0;
							((NodeAVL) node.left).bf = 0;
							((NodeAVL) node.right).bf = 0;
							break;
						case 1:
							node.bf = 0;
							((NodeAVL) node.left).bf = -1;
							((NodeAVL) node.right).bf = 0;
							break;
					}
				} else {
					node.bf = 0;
					((NodeAVL) node.left).bf = 0;
					((NodeAVL) node.right).bf = 0;
				}

				this.height = true;
				break;
		}

		return node;
	}

	private NodeAVL rotateSL(NodeAVL node){
		NodeAVL p = (NodeAVL)node.right;
		node.right = p.left;
		p.left = node;
		node = p;
		return node;
	}
	
	private NodeAVL rotateSR(NodeAVL node) {
	    NodeAVL p = (NodeAVL) node.left;
	    node.left = p.right;
	    p.right = node;
	    node = p;
	    return node;
	}
	
	public void remove(E x) throws ItemNotFound {
	    this.height = false;
	    setRoot(removeRec(x, (NodeAVL) getRoot()));
	}

	protected NodeAVL removeRec(E x, NodeAVL actual) throws ItemNotFound {
		if (actual == null) {
			throw new ItemNotFound("El dato " + x + " no se encontró");
		}

		int resC = x.compareTo(actual.data);
		if (resC < 0) {
			actual.left = removeRec(x, (NodeAVL) actual.left);
			if (this.height) {
				actual = balanceToRight(actual); // Cambia al lado derecho
			}
		} else if (resC > 0) {
			actual.right = removeRec(x, (NodeAVL) actual.right);
			if (this.height) {
				actual = balanceToLeft(actual); // Cambia al lado izquierdo
			}
		} else {
			// Nodo encontrado
			this.height = true;
			// Caso 1: hoja
			if (actual.left == null && actual.right == null) {
				this.height = true;
    			return null;
			}
			// Caso 2: un solo hijo
			else if (actual.left == null) {
				this.height = true;
    			return (NodeAVL) actual.right;
			} else if (actual.right == null) {
				this.height = true;
    			return (NodeAVL) actual.left;
			}
			// Caso 3: dos hijos
			else {
				NodeAVL minNode = (NodeAVL) findMin((NodeAVL) actual.right);
				actual.data = minNode.data;
				actual.right = removeRec(minNode.data, (NodeAVL) actual.right);
				if (this.height) {
					actual = balanceToLeft(actual); // ya eliminaste del lado derecho
				}
			}
		}

		return actual;
	}


	// Método público para iniciar el recorrido preorden
    public String preOrder() {
        if (root == null)
            return "*";
        return preOrder(root);
    }

    // Método recursivo privado para recorrido preorden
    private String preOrder(Node node) {
        if (node == null)
            return "";
        String result = node.data.toString() + ", ";
        result += preOrder(node.left);
        result += preOrder(node.right);
        return result;
    }

	public void printTree() {
		printTree((NodeAVL) getRoot(), "", true);
	}

	private void printTree(NodeAVL node, String prefix, boolean isTail) {
		if (node == null) return;
		System.out.println(prefix + (isTail ? "└── " : "├── ") + node.data + " (" + node.bf + ")");
		printTree((NodeAVL) node.left, prefix + (isTail ? "    " : "│   "), false);
		printTree((NodeAVL) node.right, prefix + (isTail ? "    " : "│   "), true);
	}

}