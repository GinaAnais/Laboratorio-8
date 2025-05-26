package avlTree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class TestEjercicio4 {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        AVLTree<Integer> arbol = new AVLTree<>();
        arbol.insert(50);
		arbol.insert(30);
		arbol.insert(70);
		arbol.insert(20);
		arbol.insert(40);
		arbol.insert(60);
		arbol.insert(80);
		arbol.insert(10);
		arbol.insert(25);
		arbol.insert(65);
        System.out.println(arbol.recorridoPorNiveles());

    }
}