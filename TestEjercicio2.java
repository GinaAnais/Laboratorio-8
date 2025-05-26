package avlTree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class TestEjercicio2 {
	public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
		AVLTree<Integer> arbol1 = new AVLTree<Integer>();
		arbol1.insert(50);
		arbol1.insert(40);
		arbol1.insert(60);
		arbol1.insert(30);
		arbol1.remove(60);
		System.out.println(arbol1);
	}
	
}