import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;
    private int length = 0;

    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        try {
            return queue[0] == null;
        }
        catch (NullPointerException e){
            return false;
        }
    }

    // return the number of items on the randomized queue
    public int size() {
        return length;
    }

    // add the item
    public void enqueue(Item item) {
        if( item == null ){
            throw new IllegalArgumentException();
        }
        if(n == queue.length) {
            resize(n*2);
        }
        queue[n] = item;
        n++;
        length++;
    }

    // remove and return a random item
    public Item dequeue() {
        if( queue[0] == null ) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(0, length);
        Item val = queue[rand];
        int lastInd = 0;
        for(int i = rand; i < length; i++){
            if( length == 1){
                queue[i] = null;
            } else if ( i + 1 < length) {
                queue[i] = queue [i + 1];
            }
            lastInd = i;
        }
        queue[lastInd] = null;
        if(n < queue.length / 4) {
            resize(queue.length / 2);
        }
        n--;
        length--;
        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if( queue[0] == null ) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(0, length);
        return queue[rand];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{

        private int current = 0;
        private int[] index = new int[length];

        public RandomIterator() {
            for(int i = 0; i < index.length; i++) {
                index[i] = i;
            }
            int ranA = StdRandom.uniform(0, index.length);
            int ranB = StdRandom.uniform(0, index.length);
            for(int i = 0; i < index.length; i++) {
                int temp = index[ranA];
                index[ranA] = index[ranB];
                index[ranB] = temp;
                ranA = StdRandom.uniform(0, index.length);
                ranB = StdRandom.uniform(0, index.length);
            }
        }

        public boolean hasNext() {
            return current < length;
        }

        public Item next() {
            if(current >= length || queue[0] == null) {
                throw new NoSuchElementException();
            }
            Item item = queue[index[current]];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int count) {
        Item[] newList = (Item[]) new Object[count];
        int bound = 0;
        if(count > queue.length) {
            bound = queue.length;
        }
        else {
            bound = count;
        }
        for (var i = 0; i < bound; i++) {
            newList[i] = queue[i];
        }
        queue = newList;
    }

    public static void main(String[] args){
        RandomizedQueue randQ = new RandomizedQueue<String>();
        randQ.enqueue(9);
        randQ.isEmpty();
        randQ.isEmpty();
        randQ.enqueue(22);
        StdOut.println(randQ.dequeue());
        StdOut.println(randQ.dequeue());
        randQ.enqueue(49);
        StdOut.println(randQ.dequeue());
//        StdOut.println(randQ.isEmpty());
//        StdOut.println(randQ.size());
//        randQ.enqueue(new String("1"));
//        randQ.enqueue(new String("2"));
//        randQ.enqueue(new String("3"));
//        randQ.enqueue(new String("4"));
//        StdOut.println(randQ.isEmpty());
//        StdOut.println(randQ.size());
//        StdOut.println(randQ.dequeue());
//        StdOut.println(randQ.size());
//        StdOut.println(randQ.sample());
//        StdOut.println(randQ.sample());
//        for( Object s : randQ){
//            StdOut.println(s);
//        }
    }
}
