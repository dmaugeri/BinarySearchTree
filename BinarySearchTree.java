import java.util.*;

public class BinarySearchTree<T extends Comparable<T>>
{
    private Node<T> root;

    public BinarySearchTree()
    {
        this.root = null;
    }

    public Node<T> findNode(T value)
    {
        Node<T> currentRoot = root;

        while (currentRoot != null)
        {
            T currentValue = currentRoot.getValue();
            if (value.compareTo(currentValue) == 0) break;

            if (value.compareTo(currentValue) < 0)
                currentRoot = currentRoot.getLeftChild();
            else if (value.compareTo(currentValue) > 0)
                currentRoot = currentRoot.getRightChild();
        }

        return currentRoot;
    }

    public Node<T> recursiveFindNode(T value)
    {
        return findNode(this.root, value);
    }

    private Node<T> findNode(Node<T> root, T value)
    {
        if (root == null) return null;
        
        T currentValue = root.getValue();

        if (currentValue.compareTo(value) == 0) return root;

        if (value.compareTo(currentValue) < 0) 
            return findNode(root.getLeftChild(), value);
        else //if (value.compareTo(currentValue) > 0) 
            return findNode(root.getRightChild(), value);
    }

    private Node<T> insertNodeRecursiveHelper(Node<T> root, Node<T> n)
    {
        if (root == null) return n; 

        T rootValue = root.getValue();
        T newNodeValue = n.getValue();

        if (newNodeValue.compareTo(rootValue) < 0)
        {
            if (root.getLeftChild() != null)
                return insertNodeRecursiveHelper(root.getLeftChild(), n);
            else
                root.setLeftChild(n);
        }
        else if (newNodeValue.compareTo(rootValue) > 0)
        {
            if (root.getRightChild() != null)
                return insertNodeRecursiveHelper(root.getRightChild(), n);
            else
                root.setRightChild(n);
        }

        return null;
    }
    public Node<T> insertValueRecursive(T value)
    {
        Node<T> node = new Node<T>(value);
        if (root == null)
        {
            root = node;
            return root;
        }
        return insertNodeRecursiveHelper(root, node);
    }

    public Node<T> insertValue(T value)
    {
        return null;
    }

    public void DFS()
    {
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Set<Node<T>> set = new HashSet<Node<T>>();

        stack.push(root);
        Node<T> currentRoot;
        while (!stack.empty())
        {
            currentRoot = stack.pop();
            System.out.println(currentRoot.getValue());
            if (!set.contains(currentRoot))
            {
                set.add(currentRoot);
                
                Node<T> leftChild = currentRoot.getLeftChild();
                Node<T> rightChild = currentRoot.getRightChild();

                if (rightChild != null)
                    stack.push(rightChild);
                if (leftChild != null)
                    stack.push(leftChild);
            }
        }
    }

    public void BFS()
    {
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        Set<Node<T>> set = new HashSet<Node<T>>();

        queue.add(root);
        set.add(root);
        System.out.println(root.getValue());
        Node<T> currentRoot;

        while((currentRoot = queue.poll()) != null)
        {
            //loop through each child of the tree
            //since it is a bst we will only have a left and right child so we only have to print them
            //and mark them by adding them to the set

            Node<T> leftChild = currentRoot.getLeftChild();
            Node<T> rightChild = currentRoot.getRightChild();

            if (!set.contains(leftChild) && leftChild != null)
            {
                queue.add(leftChild);
                set.add(leftChild);
                System.out.println(leftChild.getValue());
            }

            if (!set.contains(rightChild) && rightChild != null)
            {
                queue.add(rightChild);
                set.add(rightChild);
                System.out.println(rightChild.getValue());
            }
        }
    }


    public void printInOrder()
    {
        InOrderTraversal(root);
    }

    public void printPreOrder()
    {
        PreOrderTraversal(root);
    }

    public void printPostOrder()
    {
        PostOrderTraversal(root);
    }

    private void InOrderTraversal(Node<T> root)
    {
        if (root == null)
            return;

        InOrderTraversal(root.getLeftChild());
        System.out.println(root.getValue());
        InOrderTraversal(root.getRightChild());
    }

    private void PreOrderTraversal(Node<T> root)
    {
        if (root == null)
            return;

        System.out.println(root.getValue());
        PreOrderTraversal(root.getLeftChild());
        PreOrderTraversal(root.getRightChild());
    }

    private void PostOrderTraversal(Node<T> root)
    {
        if (root == null)
            return;

        PostOrderTraversal(root.getLeftChild());
        PostOrderTraversal(root.getRightChild());
        System.out.println(root.getValue());
    }
}
