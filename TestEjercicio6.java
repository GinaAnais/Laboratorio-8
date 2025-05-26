package avlTree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;


public class TestEjercicio6 {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        AVLTree<Integer> arbol = new AVLTree<>();
        arbol.insert(50);
		arbol.insert(25);
		arbol.insert(12);
		arbol.insert(6);
		arbol.insert(3);
		arbol.insert(70);
		arbol.insert(80);
		arbol.insert(90);
		arbol.insert(100);
		arbol.insert(1);
		arbol.insert(2);
		arbol.insert(4);
		arbol.insert(75);
		arbol.insert(95);
        System.out.println(arbol);
        System.out.println("Recorrido por niveles: " + arbol.recorridoPorNiveles());
        arbol.remove(50);
        System.out.println(arbol);
        System.out.println("Recorrido por niveles tras eliminar 50\n" + arbol.recorridoPorNiveles());
        arbol.remove(3);
        System.out.println(arbol);
        System.out.println("Recorrido por niveles tras eliminar 3\n" + arbol.recorridoPorNiveles());
        arbol.printTree();
    }
}