package Tree;

public interface CompleteBinaryTree<E> extends BinaryTree<E> {

    //Add to T and return a new external node v storing
    //element elem such that the resulting tree is a complete
    //binary tree with last node v.

    public Position<E> add(E elem);

    // Remove the last node of T and return its element
    public E remove();
}
