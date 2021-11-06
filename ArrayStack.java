public class ArrayStack<E> {
    
    protected int capacity;
    public static final int CAPACITY = 1;
    protected E S[];
    protected int top = -1;

    public ArrayStack() {
      this(CAPACITY);
    }

    public ArrayStack(int c) {
        capacity = c;
        S = (E[]) new Object[capacity];
    }

    public int size() {
        return top+1;
    }

    public boolean isEmpty() {
        if (top < 0) return true;
        else return false;
    }

    public void push(E element) {
        if (size() == capacity)
            doubleSize();
        S[++top] = element;
    }

    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
        return S[top];
    }

    public E pop() throws EmptyStackException {
        E element;
        if (isEmpty())
            throw new EmptyStackException("Stack is empty.");
        element = S[top];
        S[top--] = null; // dereference S[top] for garbage collection.
        return element;
    }

    public void doubleSize() {
        capacity *= 2;
        E[] N = (E[]) new Object[capacity];
        for (int i = 0; i < size(); ++i) {
            N[i] = S[i];
        }
        S = N;
    }

    public int getCapacity() {
        return capacity;
    }

    public String toString() {
        String s;
        s = "[";
        for (int i = 0; i < size(); ++i) {
            s += S[size()-i] + ", ";
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) throws EmptyStackException {

        int[] A = {1,2,3,4,5};

        ArrayStack<Integer> intA = new ArrayStack<Integer>();

        // print array A
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }

        // add element to stack
        for (int i = 0; i < A.length; i++) {
            intA.push(A[i]);
            System.out.println("size: " + intA.size());
            System.out.println("capacity: " + intA.getCapacity());
        }

        // display the top element
        System.out.println("top: " + intA.top());

        // pop out element and modify the array
        for (int i = 0; i < A.length; i++) {
            A[i] = intA.pop();
        }

        // print array A
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }

        System.out.println("capacity: " + intA.getCapacity());
        
    }

}
