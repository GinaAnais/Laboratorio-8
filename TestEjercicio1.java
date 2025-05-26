package avlTree;

import Exceptions.ItemDuplicated;
public class TestEjercicio1 {
	public static void main(String[] args) throws ItemDuplicated {
		// Prueba 1
		System.out.println("PRUEBA 1");		
		BSTree<Integer> arbol1 = new BSTree<Integer>();
		arbol1.insert(10);
		arbol1.insert(5);
		arbol1.insert(2);
		
		System.out.println("BSTREE");
		System.out.println("Altura : " +arbol1.getNodeHeight(arbol1.getRoot()));
		System.out.println("Buscar " +arbol1.search(2));
		System.out.println("----------");		

	
		
		AVLTree<Integer> arbolAVL1 = new AVLTree<Integer>();
		arbolAVL1.insert(10);
		arbolAVL1.insert(5);
		arbolAVL1.insert(2);
		System.out.println("AVL");
		System.out.println("Altura : " +arbolAVL1.getNodeHeight(arbolAVL1.getRoot()));
		System.out.println("Buscar : " +arbolAVL1.search(2));
		System.out.println("----------");		
		System.out.println(" ");		
		
		// Prueba 2
		System.out.println("PRUEBA 2");		
		BSTree<Integer> arbol2 = new BSTree<Integer>();
		arbol2.insert(20);
		arbol2.insert(10);
		arbol2.insert(8);
		arbol2.insert(6);
		arbol2.insert(4);
		
		System.out.println("BSTREE");		
		System.out.println("Altura : " +arbol2.getNodeHeight(arbol2.getRoot()));
		System.out.println("Buscar " +arbol2.search(4));
		System.out.println("----------");		

		
		AVLTree<Integer> arbolAVL2 = new AVLTree<Integer>();
		arbolAVL2.insert(20);
		arbolAVL2.insert(10);
		arbolAVL2.insert(8);
		arbolAVL2.insert(6);
		arbolAVL2.insert(4);
		System.out.println("AVL");		
		System.out.println("Altura : " +arbolAVL2.getNodeHeight(arbolAVL2.getRoot()));
		System.out.println("Buscar : " +arbolAVL2.search(4));
		System.out.println("----------");		

	}
}