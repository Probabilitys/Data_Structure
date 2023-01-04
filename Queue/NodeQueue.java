package Queue;


/**
 * implement Queue ADT with Singly Linked List
 */


public class NodeQueue<E> implements Queue<E> {

    protected Node<E> head, tail;
    protected int size;

    public NodeQueue(){
        head = tail = null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size() == 0; }

    public E front() throws EmptyQueueException {
        if (size () == 0)
            throw new EmptyQueueException("Queue is empty.");

        return head.getElement();
    }

    public void enqueue(E elem) {
        Node<E> node = new Node<E>();
        node.setElement(elem);
        node.setNext(null);  // the new node becomes the tail

        if (size() == 0) {head = tail = node;} // the new node is both the head and tail if queue was empty
        else {tail.setNext(node);}  // add node at the tail of the list

        tail = node; // update the reference to the tail node
        size++;
    }


    public E dequeue() throws EmptyQueueException {
        if (size()== 0)
            throw new EmptyQueueException("Queue is empty.");
        E temp = head.getElement();
        head = head.getNext();
        size--;
        if (size() == 0)
            head = tail = null; // the queue is now empty
        return temp;
    }

}