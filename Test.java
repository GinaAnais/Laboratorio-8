package avlTree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;

public class Test {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        AVLTree<Integer> arbol = new AVLTree<>();

        // Caso 1: RSR (rotación simple a la derecha)
        System.out.println("Caso 1: RSR");
        arbol.insert(50);
        arbol.insert(40);
        arbol.insert(30);  // Inserción que genera RSR
        System.out.println(arbol);
        // Aquí limpia o crea un árbol nuevo para el siguiente caso
        arbol = new AVLTree<>();

        // Caso 2: RSL (rotación simple a la izquierda)
        System.out.println("Caso 2: RSL");
        arbol.insert(30);
        arbol.insert(40);
        arbol.insert(50);  // Inserción que genera RSL
        System.out.println(arbol);
        arbol = new AVLTree<>();

        // Caso 3: RDR (rotación doble a la derecha)
        System.out.println("Caso 3: RDR");
        arbol.insert(50);
        arbol.insert(30);
        arbol.insert(40);  // Inserción que genera RDR
        System.out.println(arbol);
        arbol = new AVLTree<>();

        // Caso 4: RDL (rotación doble a la izquierda)
        System.out.println("Caso 4: RDL");
        arbol.insert(30);
        arbol.insert(50);
        arbol.insert(40);  // Inserción que genera RDL
        System.out.println(arbol);

    }
}
