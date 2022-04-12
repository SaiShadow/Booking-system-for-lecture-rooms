package edu.kit.informatik;

/**
 * Represents a linked list. Most of the code has been copied from the lecture
 * notes.
 * 
 * @author uogok
 * @version 2.5
 *
 * @param <T> can be Person or Event.
 */
public class LinkedList<T> {
    private ListCell head;

    /**
     * Represents the constructor of the class LinkedList.
     */
    public LinkedList() {
        this.head = null;
    }

    /**
     * Gets ListCell at spot objectID.
     * 
     * @param objectID traverses to this spot and returns the ListCell at this spot.
     * @return A ListCell at the spot objectID.
     */
    public ListCell getObjectWithID(int objectID) {

        ListCell c = this.head;
        for (int i = 0; i <= objectID - 1; i++) {
            c = c.next;
        }
        return c;
    }

    /**
     * Traverses to the last cell in the LinkedList.
     * 
     * @return the last ListCell.
     */
    public ListCell traverseToLastCell() {
        ListCell c = this.head;
        while (c.next != null) {
            c = c.next;
        }
        return c;
    }

    /**
     * Gets the number of ListCells in the LinkedList.
     * 
     * @return The number of ListCells in the LinkedList. If there are no cells then
     *         return 0, if there is one cell then return 1 etc.
     */
    public int getSize() {
        int count = 0;
        for (ListCell c = this.head; c != null; c = c.next) {

            count = count + 1;

        }
        return count;

    }

    /**
     * Adds this object at the end of the LinkedList.
     * 
     * @param p This object is to be added to the end of the LinkedList.
     */
    public void push(T p) {
        // add to empty list
        if (this.head == null) {
            // addFirst(p)
            this.head = new ListCell(p, null);
            return;
        }
        // add to non-empty list
        ListCell c = this.head;
        while (c.next != null) {
            c = c.next;
        }
        // now c.next == null
        c.next = new ListCell(p, null);
    }

    /**
     * Removes the ListCell with this particular object from the LinkedList and
     * return how many times it removed it.
     * 
     * @param r Removes the ListCell with this object from the LinkedList.
     * @return number of times it removed this object(returns the number of
     *         duplicate cells with the same object in the LinkedList).
     */
    public int remove(T r) {
        ListCell c = this.head;
        int matches = 0;
        // remove head element(s)
        while (c != null && c.getContent() == r) {
            c = c.next;
            this.head = c;
            matches = matches + 1;
        }
        // empty list?
        if (c == null) {
            return matches; // nothing more to do
        }
        // traverse list
        while (c.next != null) {
            if (c.next.getContent() == r) {
                // found! now, remove it
                c.next = c.next.next;
                matches = matches + 1;
            } else {
                c = c.next;
            }
        }
        return matches;
    }

    /**
     * Checks if the LinkedList contains a ListCell with this object.
     * 
     * @param p checks if a ListCell contains this object.
     * @return true if the LinkedList contains a ListCell with the object p.
     */
    public boolean contains(T p) {
        for (ListCell c = head; c != null; c = c.next) {
            if (c.getContent() == p) {
                return true;
            }
        }
        return false;
    }

    class ListCell {
        private T content;
        private ListCell next;

        /**
         * Constructor for the class ListCell.
         * 
         * @param p Is an object which the ListCell has to contain.
         * @param n reference to the next cell.
         */
        public ListCell(T p, ListCell n) {
            this.setContent(p);
            this.next = n;
        }

        /**
         * Gets the content of the list.
         * 
         * @return The object saved in the list under the variable content.
         */
        public T getContent() {
            return this.content;
        }

        /**
         * Sets the content of the ListCell.
         * 
         * @param content is to be saved into the ListCell.
         */
        public void setContent(T content) {
            this.content = content;
        }
    }

}
