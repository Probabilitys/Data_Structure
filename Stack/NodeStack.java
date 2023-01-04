package Stack;

import java.util.Arrays;

/**
 * Implement Stack ADT with Singly Linked List
 */

public class NodeStack<E> implements Stack<E> {

    protected Node<E> top;	// reference to the head node
    protected int size;	 // number of elements in the stack

    /** Constructor with no parameter */
    public NodeStack() {
        top = null;
        size = 0;
    }

    /** Initialize the stack with a given element */
    public NodeStack(E elem) {
        this();
        this.push(elem);
    }

    public int size() { return size; }

    public boolean isEmpty() {
        if (top == null) return true;
        return false;
    }

    public void push(E elem) {
        Node<E> v = new Node<E>(elem, top);	// create and link-in a new node
        top = v;
        size++;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
        E temp = top.getElement();
        top = top.getNext();	// link-out the former top node
        size--;
        return temp;
    }

    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
        return top.getElement();
    }

    /** Reverse the given array a */
    public static <E> void reverse(E[] a) throws EmptyStackException {
        Stack<E> S = new NodeStack<E>();
        for (int i=0; i < a.length; i++)
            S.push(a[i]);
        for (int i=0; i < a.length; i++)
            a[i] = S.pop();
    }

    /** Tester routine for reversing arrays */
    public static void main(String args[]) throws EmptyStackException {
        Integer[] a = {4, 8, 15, 16, 23, 42};  // autoboxing
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Boone"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
    }

}