import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Main {

        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                int number = sc.nextInt();
              
                for(int i=0; i < number; i++){
                        int m = sc.nextInt();
                        String[] input = new String[m];
                        
                        String woord = sc.next();
                       
                        for(int j = 0 ; j < m  ; m ++ )
                        {
                        	input[j] = sc.next();
                        }
                        
                        
                        sort(input);
                        

                       System.out.println(input.toString());
                }
        }
        
        
        
        
        
        // quicksort the array
        public static void sort(Comparable[] a) {
            shuffle(a);
            sort(a, 0, a.length - 1);
        }

        // quicksort the subarray from a[lo] to a[hi]
        private static void sort(Comparable[] a, int lo, int hi) { 
            if (hi <= lo) return;
            int j = partition(a, lo, hi);
            sort(a, lo, j-1);
            sort(a, j+1, hi);
            assert isSorted(a, lo, hi);
        }

        // partition the subarray a[lo .. hi] by returning an index j
        // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        private static int partition(Comparable[] a, int lo, int hi) {
            int i = lo;
            int j = hi + 1;
            Comparable v = a[lo];
            while (true) { 

                // find item on lo to swap
                while (less(a[++i], v))
                    if (i == hi) break;

                // find item on hi to swap
                while (less(v, a[--j]))
                    if (j == lo) break;      // redundant since a[lo] acts as sentinel

                // check if pointers cross
                if (i >= j) break;

                exch(a, i, j);
            }

            // put v = a[j] into position
            exch(a, lo, j);

            // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
            return j;
        }

       /***********************************************************************
        *  Rearranges the elements in a so that a[k] is the kth smallest element,
        *  and a[0] through a[k-1] are less than or equal to a[k], and
        *  a[k+1] through a[n-1] are greater than or equal to a[k].
        ***********************************************************************/
        public static Comparable select(Comparable[] a, int k) {
            if (k < 0 || k >= a.length) {
                throw new RuntimeException("Selected element out of bounds");
            }
            shuffle(a);
            int lo = 0, hi = a.length - 1;
            while (hi > lo) {
                int i = partition(a, lo, hi);
                if      (i > k) hi = i - 1;
                else if (i < k) lo = i + 1;
                else return a[i];
            }
            return a[lo];
        }



       /***********************************************************************
        *  Helper sorting functions
        ***********************************************************************/
        
        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return (v.compareTo(w) < 0);
        }
            
        // exchange a[i] and a[j]
        private static void exch(Object[] a, int i, int j) {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }


       /***********************************************************************
        *  Check if array is sorted - useful for debugging
        ***********************************************************************/
        private static boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i-1])) return false;
            return true;
        }
        
        

        /**
         * Rearrange the elements of an array in random order.
         */
        public static void shuffle(Object[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of a double array in random order.
         */
        public static void shuffle(double[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of an int array in random order.
         */
        public static void shuffle(int[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }


        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(Object[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length)
                throw new RuntimeException("Illegal subarray range");
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(double[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length)
                throw new RuntimeException("Illegal subarray range");
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(int[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length)
                throw new RuntimeException("Illegal subarray range");
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }
        
        /**
         * Return an integer uniformly between 0 and N-1.
         */
        public static int uniform(int N) {
            return random.nextInt(N);
        }

        ///////////////////////////////////////////////////////////////////////////
        //  STATIC METHODS BELOW RELY ON JAVA.UTIL.RANDOM ONLY INDIRECTLY VIA
        //  THE STATIC METHODS ABOVE.
        ///////////////////////////////////////////////////////////////////////////

        /**
         * Return int uniformly in [a, b).
         */
        public static int uniform(int a, int b) {
            return a + uniform(b - a);
        }

        /**
         * Return real number uniformly in [a, b).
         */
        public static double uniform(double a, double b) {
            return a + uniform() * (b-a);
        }

        /**
         * Return a boolean, which is true with probability p, and false otherwise.
         */
        public static boolean bernoulli(double p) {
            return uniform() < p;
        }

        /**
         * Return a boolean, which is true with probability .5, and false otherwise.
         */
        public static boolean bernoulli() {
            return bernoulli(0.5);
        }

        private static Random random = new Random();
        
        /**
         * Return real number uniformly in [0, 1).
         */
        public static double uniform() {
            return random.nextDouble();
        }


}