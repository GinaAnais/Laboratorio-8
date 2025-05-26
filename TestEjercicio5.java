package avlTree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNotFound;


class TestEjercicio5 {
    public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
        AVLTree<Integer> avl = new AVLTree<>();

        // Supón que tienes el método insert para agregar elementos balanceando el árbol
        avl.insert(30);
        avl.insert(20);
        avl.insert(40);
        avl.insert(10);
        avl.insert(25);

        System.out.println(avl);
        System.out.println("Recorrido Preorden: " + avl.preOrder());
    }
}