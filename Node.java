import java.util.*;

public class Node<T extends Comparable<T>>
{
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T value;

    public Node(T value)
    {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(T value, Node<T> leftChild, Node<T> rightChild)
    {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void setLeftChild(Node<T> leftChild)
    {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<T> rightChild)
    {
        this.rightChild = rightChild;
    }

    public Node<T> getLeftChild()
    {
        return this.leftChild;
    }

    public Node<T> getRightChild()
    {
        return this.rightChild;
    }

    public T getValue()
    {
        return this.value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }
}
