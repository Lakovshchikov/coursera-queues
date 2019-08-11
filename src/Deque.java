import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>{

    private class Node {

        public Item item;
        public Node next;
        public Node prev;

        Node(Item _item) {
            item = _item;
            next = null;
            prev = null;
        }
    }
    private Node first;
    private Node last;
    private int length;

    public Deque() {
        first = null;
        last = null;
        length = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return length;
    }

    public void addFirst(Item item) {
        if( item == null ){
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        if( oldFirst != null ) {
            oldFirst.prev = first;
        }
        length++;
        if( length == 1){
            last = first;
        }
    }

    public void addLast(Item item) {
        if( item == null ){
            throw new IllegalArgumentException();
        }
        if( last == null) {
            addFirst(item);
        }
        else{
            last.next = new Node(item);
            last.next.prev = last;
            last = last.next;
            length++;
        }

//        Node last = first;
//        if( last != null ){
//            while (last.next != null){
//                last = last.next;
//            }
//            last.next = new Node(item);
//        }
//        else{
//            first = new Node(item);
//        }
    }

    public Item removeFirst() {
        if( isEmpty() ) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        if(length == 1) {
            last = null;
            first = null;
            length = 0;
        }
        else{
            first = oldFirst.next;
            first.prev = null;
            length--;
        }
        return oldFirst.item;
    }

    public Item removeLast() {
        if( isEmpty() ) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        if(length == 1) {
            last = null;
            first = null;
            length = 0;
        }
        else{
            last = last.prev;
            last.next = null;
            length--;
        }
//        Node last = first;
//        Item res;
//        if(last.next != null){
//            while (last.next.next != null){
//                last = last.next;
//            }
//            res = last.next.item;
//            last.next = null;
//        }
//        else{
//            res = last.item;
//            first = null;
//        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            try {
                Item item = current.item;
                if (current == null) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                return item;
            }
            catch (NullPointerException e){
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.removeLast();
        deque.addFirst(3);
        deque.isEmpty();
        deque.removeLast();
    }
}
