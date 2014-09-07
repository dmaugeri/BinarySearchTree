import java.util.*;

public class BinarySearchTreeTest
{
    public static void main(String[] args)
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        bst.insertValueRecursive(10);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);

        System.out.println("In Order: ");
        System.out.println("----------------");
        bst.printInOrder();
        System.out.println("----------------");
        System.out.println("PreOrder: ");
        System.out.println("----------------");
        bst.printPreOrder();
        System.out.println("----------------");
        System.out.println("PostOrder: ");
        System.out.println("----------------");
        bst.printPostOrder();
        System.out.println("----------------");
        System.out.println("BFS: ");
        System.out.println("----------------");
        bst.BFS();
        System.out.println("----------------");
        System.out.println("DFS: ");
        System.out.println("----------------");
        bst.DFS();
    }
}