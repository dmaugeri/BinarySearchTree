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

    public Node<T> insertValueRecursive(T value)
    {
        Node<T> node = new Node<T>(value);
        if (root == null)
            this.root = node;
        return insertNodeRecursiveHelper(root, node);
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

    public T deleteValue(T value)
    {
        return deleteValueFind(this.root, value);
    }

    /*
     * find the node to delete and it's parent
     * then delete the node
     */
    private T deleteValueFind(Node<T> root, T value)
    {
        Node<T> parent = null;
        Node<T> nodeToBeDeleted = null;
        Node<T> currentNode = root;

        while (currentNode != null)
        {
            T currentValue = currentNode.getValue();
            if (value.compareTo(currentValue) == 0)
            {
                nodeToBeDeleted = currentNode;
                break;
            }
            else if (value.compareTo(currentValue) > 0)
            {
                parent = currentNode;
                currentNode = currentNode.getRightChild();
            }
            else if (value.compareTo(currentValue) < 0)
            {
                parent = currentNode;
                currentNode = currentNode.getLeftChild();
            }
        }

        //root is null
        if (nodeToBeDeleted == null)
            return null;

        return deleteNode(parent, nodeToBeDeleted);
    }

    /*
     * Delete's the node 
     * if parent is null it deletes the root
     * return the value being deleted or null if it fails
     */
    private T deleteNode(Node<T> parent, Node<T> nodeToBeDeleted)
    {
        if (parent == null)
            return deleteRootOfTree();

        Node<T> leftChild = nodeToBeDeleted.getLeftChild();
        Node<T> rightChild = nodeToBeDeleted.getRightChild();

        if (leftChild == null && rightChild == null)
        {
            T value = nodeToBeDeleted.getValue();
            setAppropriateChildForParentNode(parent, value, null);
            return value;
        }
        else if (leftChild != null && rightChild == null)
        {
            T value = nodeToBeDeleted.getValue();
            setAppropriateChildForParentNode(parent, value, nodeToBeDeleted.getLeftChild());
            return value;
        }
        else if (leftChild == null && rightChild != null)
        {
            T value = nodeToBeDeleted.getValue();
            setAppropriateChildForParentNode(parent, value, nodeToBeDeleted.getRightChild());
            return value;
        }
        else if (leftChild != null && rightChild != null)
        {
            T value = nodeToBeDeleted.getValue();
            Node<T> min = findMinNodeAndRemoveReferenceToIt(rightChild);
            if (min == null)
                return null;

            min.setLeftChild(leftChild);
            min.setRightChild(rightChild);
            setAppropriateChildForParentNode(parent, value, min);
            return value;
        }


        return nodeToBeDeleted.getValue();
    }

    private T deleteRootOfTree()
    {
        if (this.root == null)
            return null;

        Node<T> leftChild = this.root.getLeftChild();
        Node<T> rightChild = this.root.getRightChild();
        if (leftChild == null && rightChild == null)
        {
            T value = this.root.getValue();
            this.root = null;
            return value;
        }
        else if (leftChild != null && rightChild == null)
        {
            T value = this.root.getValue();
            this.root = null;
            this.root = leftChild;
            return value;
        }
        else if (leftChild == null && rightChild != null)
        {
            T value = this.root.getValue();
            this.root = null;
            this.root = rightChild;
            return value;
        }
        else //if (leftChild != null && rightChild != null)
        {
            //find the minimum of the right subtree of the node to be deleted
            T value = this.root.getValue();
            Node<T> min = findMinNodeAndRemoveReferenceToIt(rightChild);
            if (min == null)
                return null;

            min.setLeftChild(root.getLeftChild());
            min.setRightChild(root.getRightChild());
            this.root = min;
            return value;
        }

    }

    private void setAppropriateChildForParentNode(Node<T> parent, T compareValue, Node<T> newChild)
    {
        if (parent == null)
            return;

        T parentValue = parent.getValue();
        if (compareValue.compareTo(parentValue) > 0)
            parent.setRightChild(newChild);
        else if (compareValue.compareTo(parentValue) < 0)
            parent.setLeftChild(newChild);
    } 

    /*
     * Will return null if root is null
     */
    private Node<T> findMinNodeAndRemoveReferenceToIt(Node<T> root)
    {
        Node<T> currentRoot = root;
        Node<T> parent = null;

        while (currentRoot != null)
        {
            Node<T> newMin = currentRoot.getLeftChild();
            if (newMin == null)
            {
                //removes the reference of the min node in the tree
                //by finding the parent of the min node
                //and the min node must be on the left of the parent
                if (parent != null)
                    parent.setLeftChild(null);
                return currentRoot;
            }
            else
            {
                parent = currentRoot;
                currentRoot = currentRoot.getLeftChild();
            }
        }
        return null;
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
            System.out.print(currentRoot.getValue() + ",");
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
        System.out.print(root.getValue() + ",");
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
                System.out.print(leftChild.getValue() + ",");
            }

            if (!set.contains(rightChild) && rightChild != null)
            {
                queue.add(rightChild);
                set.add(rightChild);
                System.out.print(rightChild.getValue() + ",");
            }
        }
    }

    public void printInOrder()
    {
        InOrderTraversal(this.root);
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
        System.out.print(root.getValue() + ",");
        InOrderTraversal(root.getRightChild());
    }

    private void PreOrderTraversal(Node<T> root)
    {
        if (root == null)
            return;

        System.out.print(root.getValue() + ",");
        PreOrderTraversal(root.getLeftChild());
        PreOrderTraversal(root.getRightChild());
    }

    private void PostOrderTraversal(Node<T> root)
    {
        if (root == null)
            return;

        PostOrderTraversal(root.getLeftChild());
        PostOrderTraversal(root.getRightChild());
        System.out.print(root.getValue() + ",");
    }
}
