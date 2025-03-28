
 public interface PositionList<E> {
    /** Returns the number of elements in this list. */
     public int size();
     /** Returns whether the list is empty. */
     public boolean isEmpty();
     /** Returns the first node in the list. */
     public Position<E> first() throws EmptyListException;
     /** Returns the last node in the list. */
     public Position<E> last() throws EmptyListException;
     /** Returns the node after a given node in the list. */
     public Position<E> next(Position<E> p)
       throws InvalidPositionException, BoundaryViolationException;
     /** Returns the node before a given node in the list. */
     public Position<E> prev(Position<E> p)
       throws InvalidPositionException, BoundaryViolationException;
     /** Inserts an element at the front of the list, returning new position. */
     public void addFirst(E e);
     /** Inserts and element at the back of the list, returning new position. */
     public void addLast(E e);
     /** Inserts an element after the given node in the list. */
     public void addAfter(Position<E> p, E e)
       throws InvalidPositionException;
     /** Inserts an element before the given node in the list. */
     public void addBefore(Position<E> p, E e)
       throws InvalidPositionException;
     /** Removes a node from the list, returning the element stored there. */
     public E remove(Position<E> p) throws InvalidPositionException;
     /** Replaces the element stored at the given node, returning old element. */
     public E set(Position<E> p, E e) throws InvalidPositionException;

     /** Returns an iterator of all the elements in the list. */
	 public iterable<E> iter();

	 // return an iterable object containing the positions in the collection as elements
	 public PositionList <Position<E>> positions();

}
