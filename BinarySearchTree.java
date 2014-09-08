import java.util.*;

public class BinarySearchTree<T extends Comparable<T>>
{
    private Node<T> root;

    public BinarySearchTree()
    {
        this.root = null;
    }

    /*
     * returns null if node does not exist
     */
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

    /*
     * returns null if node does not exist
     */
    public Node<T> recursiveFindNode(T value)
    {
        return findNode(this.root, value);
    }

    /*
     * returns null if node does not exist
     */
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
        if (root == null || n == null)
            return n; 

        T rootValue = root.getValue();
        T newNodeValue = n.getValue();

        if (newNodeValue.compareTo(rootValue) < 0)
        {
            if (root.getLeftChild() != null)
                return insertNodeRecursiveHelper(root.getLeftChild(), n);
            else
            {
                root.setLeftChild(n);
                n.setParent(root);
            }
        }
        else if (newNodeValue.compareTo(rootValue) > 0)
        {
            if (root.getRightChild() != null)
                return insertNodeRecursiveHelper(root.getRightChild(), n);
            else
            {
                root.setRightChild(n);
                n.setParent(root);
            }
        }

        return null;
    }
    public Node<T> insertValueRecursive(T value)
    {
        Node<T> node = new Node<T>(value);
        if (root == null)
            this.root = node;
        return insertNodeRecursiveHelper(root, node);
    }
    
    public T deleteValueV2(T value)
    {
        return deleteValueFindV2(this.root, value);
    }

    /*
     * Find's the node to be deleted and the parent of that node
     * and passes it to the function deleteNode that actually deletes the node
     * using the parent and the node to be deleted
     */
    private T deleteValueFindV2(Node<T> root, T value)
    {
        //not in the tree
        if (root == null)
            return null;

        T rootValue = root.getValue();
        if (value.compareTo(rootValue) < 0)
        {
            Node<T> leftChild = root.getLeftChild();
            Node<T> rightChild = root.getRightChild();
           
            //if left child is the one we want to delete, pass the parent and the leftchild to delete
            if (leftChild != null && value.compareTo(leftChild.getValue()) == 0)
                return deleteNode(root, leftChild);
            //if right child is the one we want to delete pass the parent and the rightchild to delete
            else if (rightChild != null && value.compareTo(rightChild.getValue()) == 0)
                return deleteNode(root, rightChild);

            //else keep looking
            else
                return deleteValueFindV2(leftChild, value);
        }
        else if (value.compareTo(rootValue) > 0)
        {
            Node<T> leftChild = root.getLeftChild();
            Node<T> rightChild = root.getRightChild();
            
            //if left child is the one we want to delete, pass the parent and the leftchild to delete
            if (leftChild != null && value.compareTo(leftChild.getValue()) == 0)
                return deleteNode(root, leftChild);
            //if right child is the one we want to delete pass the parent and the rightchild to delete
            else if (rightChild != null && value.compareTo(rightChild.getValue()) == 0)
                return deleteNode(root, rightChild);
            //else keep looking
            else
                return deleteValueFindV2(rightChild, value);
        }
        else if (value.compareTo(rootValue) == 0)
        {
            //we are deleting the root
            return deleteNode(null, root);

        }
            return null;

    }

    /*
     * Delete's the node if parent is null it deletes the root
     */
    private T deleteNode(Node<T> parent, Node<T> nodeToBeDeleted)
    {
        if (parent != null)
        {
            System.out.println("Parent of node to be deleted: " + parent.getValue() + " Value: " + nodeToBeDeleted.getValue());
        }
        else
            System.out.println("Parent of node to be deleted: " + parent + " Value: " + nodeToBeDeleted.getValue());
        return nodeToBeDeleted.getValue();
    }

    /*
     *Deletes value assuming that each child knows it's parent node
     *Returns null if value does not exist in the tree
     */
    public T deleteValue(T value)
    {
        Node<T> nodeToBeDeleted = findNode(value);
        if (nodeToBeDeleted == null)
            return null;
        return deleteNode(nodeToBeDeleted.getParent(), nodeToBeDeleted);
    }

    public Node<T> insertValue(T value)
    {
        //TODO: implement this function non-recursively
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
