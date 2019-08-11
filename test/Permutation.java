import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue rQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rQ.enqueue(StdIn.readString());
        }
        int i = 0;
        for( Object s : rQ) {
            if(i < k) {
                StdOut.println(s);
            }
            i++;
        }
    }
}
