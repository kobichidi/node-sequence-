public class NodePositionList<E> implements PositionList<E> {
    protected int numElts;            // Number of elements in the list
    protected DNode<E> header, trailer; // Special sentinels

     /** Constructor that creates an empty list; O(1) time */
      public NodePositionList() {
        numElts = 0;
        header = new DNode <E> (null, null, null); // create header
        trailer = new DNode <E> (header, null, null);             // create trailer
        header.setNext(trailer);       // make header and trailer point to each other
      }
      /** Checks if position is valid for this list and converts it to
        *  DNode if it is valid; O(1) time */
      protected DNode<E> checkPosition(Position<E> p)
        throws InvalidPositionException {
        if (p == null)
          throw new InvalidPositionException
                ("Null position passed to NodeList");
        if (p == header)
                throw new InvalidPositionException
                  ("The header node is not a valid position");
        if (p == trailer)
                throw new InvalidPositionException
                  ("The trailer node is not a valid position");
        try {
          DNode<E> temp = (DNode<E>) p;
          if ((temp.getPrev() == null) || (temp.getNext() == null))
                throw new InvalidPositionException
                  ("Position does not belong to a valid NodeList");
          return temp;
        } catch (ClassCastException e) {
          throw new InvalidPositionException
                ("Position is of wrong type for this list");
        }
      }

       /** Returns the number of elements in the list;  O(1) time */
        public int size() { return numElts; }
        /** Returns whether the list is empty;  O(1) time  */
        public boolean isEmpty() { return (numElts == 0); }
        /** Returns the first position in the list; O(1) time */
        public Position<E> first()
            throws EmptyListException {
          if (isEmpty())
             throw new EmptyListException("List is empty");
          return  header.getNext();
        }

        public Position<E> last()
                                            throws EmptyListException {
                                          if (isEmpty())
                                            throw new EmptyListException("List is empty");
                                          return trailer.getPrev();
        }
}
