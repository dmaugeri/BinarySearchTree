import java.util.*;

public class BinarySearchTreeTest
{
    public static final String SEPARATOR = "--------";
    public static void main(String[] args)
    {
        inOrderTraversalTest();
        System.out.println();
        postOrderTaversalTest();
        System.out.println();
        preOrderTraversalTest();
        System.out.println();
        DFSTest();
        System.out.println();
        BFSTest();
        System.out.println();
        recursiveFindNodeTest();
        System.out.println();
        findNodeTest();
        System.out.println();
        deleteNodeTest();
    }

    public static void deleteNodeTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.deleteValue(10);
        System.out.println("Delete Node Test");
        System.out.println(SEPARATOR);

        System.out.println("Case delete root with no leaves");
        System.out.println("Expected:");
        System.out.println(" ");
        System.out.println("Result:");
        bst.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.deleteValue(10);
        System.out.println("Case delete root with one left child");
        System.out.println("Expected:");
        System.out.println("3,5,7");
        System.out.println("Result:");
        bst.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
        bst2.insertValueRecursive(10);
        bst2.insertValueRecursive(20);
        bst2.insertValueRecursive(15);
        bst2.insertValueRecursive(25);
        bst2.deleteValue(10);
        System.out.println("Case delete root with one right child");
        System.out.println("Expected:");
        System.out.println("15,20,25");
        System.out.println("Result:");
        bst2.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        BinarySearchTree<Integer> bst3 = new BinarySearchTree<Integer>();
        bst3.insertValueRecursive(10);
        bst3.insertValueRecursive(20);
        bst3.insertValueRecursive(15);
        bst3.insertValueRecursive(25);
        bst3.insertValueRecursive(5);
        bst3.insertValueRecursive(3);
        bst3.insertValueRecursive(7);
        bst3.insertValueRecursive(6);
        bst3.deleteValue(10);
        System.out.println("Case delete root with 2 children");
        System.out.println("Expected:");
        System.out.println("3,5,6,7,15,20,25");
        System.out.println("Result:");
        bst3.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        BinarySearchTree<Integer> bst5 = new BinarySearchTree<Integer>();
        bst5.insertValueRecursive(10);
        bst5.insertValueRecursive(15);
        bst5.insertValueRecursive(20);
        bst5.insertValueRecursive(25);
        bst5.deleteValue(15);
        System.out.println("Case delete node with only right children");
        System.out.println("Expected:");
        System.out.println("10,20,25");
        System.out.println("Result:");
        bst5.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        BinarySearchTree<Integer> bst6 = new BinarySearchTree<Integer>();
        bst6.insertValueRecursive(10);
        bst6.insertValueRecursive(7);
        bst6.insertValueRecursive(6);
        bst6.insertValueRecursive(5);
        bst6.deleteValue(7);
        System.out.println("Case delete node with only left children");
        System.out.println("Expected:");
        System.out.println("5,6,10");
        bst6.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);

        BinarySearchTree<Integer> bst4 = new BinarySearchTree<Integer>();
        bst4.insertValueRecursive(10);
        bst4.insertValueRecursive(20);
        bst4.insertValueRecursive(15);
        bst4.insertValueRecursive(25);
        bst4.insertValueRecursive(5);
        bst4.insertValueRecursive(3);
        bst4.insertValueRecursive(7);
        bst4.insertValueRecursive(6);
        bst4.deleteValue(5);
        System.out.println("case delete node with 2 children");
        System.out.println("Expected:");
        System.out.println("3,6,7,10,15,20,25");
        System.out.println("Result:");
        bst4.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void inOrderTraversalTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("InOrderTraversalTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("2,3,4,5,7,10,15,20,25,");
        System.out.println("Result:");
        bst.printInOrder();
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void postOrderTaversalTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("PostOrderTraversalTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("2,4,3,7,5,15,25,20,10");
        System.out.println("Result:");
        bst.printPostOrder();
        System.out.println();
        System.out.println(SEPARATOR);
    }
    public static void preOrderTraversalTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("PreOrderTraversalTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("10,5,3,2,4,7,20,15,25");
        System.out.println("Result:");
        bst.printPreOrder();
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void DFSTest()
    {

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("DFS");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("10,5,3,2,4,7,20,15,25");
        System.out.println("Result:");
        bst.DFS();
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void BFSTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("BFSTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("10,5,20,3,7,15,25,2,4");
        System.out.println("Result:");
        bst.BFS();
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void recursiveFindNodeTest()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("RecursiveFindNodeTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("15,10,null,25");
        System.out.println("Result:");
        System.out.print(bst.recursiveFindNode(15).getValue() + ",");
        System.out.print(bst.recursiveFindNode(10).getValue() + ",");
        System.out.print(bst.recursiveFindNode(40) + ",");
        System.out.print(bst.recursiveFindNode(25).getValue());
        System.out.println();
        System.out.println(SEPARATOR);
    }

    public static void findNodeTest()
    {

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insertValueRecursive(10);
        bst.insertValueRecursive(5);
        bst.insertValueRecursive(20);
        bst.insertValueRecursive(3);
        bst.insertValueRecursive(7);
        bst.insertValueRecursive(15);
        bst.insertValueRecursive(25);
        bst.insertValueRecursive(2);
        bst.insertValueRecursive(4);
        System.out.println("FindNodeTest");
        System.out.println(SEPARATOR);
        System.out.println("Expected:");
        System.out.println("15,10,null,25");
        System.out.println("Result:");
        System.out.print(bst.findNode(15).getValue() + ",");
        System.out.print(bst.findNode(10).getValue() + ",");
        System.out.print(bst.findNode(40) + ",");
        System.out.print(bst.findNode(25).getValue());
        System.out.println();
        System.out.println(SEPARATOR);
    }
}
