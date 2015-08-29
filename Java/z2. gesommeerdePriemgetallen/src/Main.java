import java.util.ArrayList;
import java.util.Scanner;

class Main {

        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                int op = sc.nextInt();

                // hoofdlus
                for(int i=0; i < op; i++){
                	
                        int m = sc.nextInt();
                        
                        int n = sc.nextInt();
                        
                        ArrayList<Integer> pr = getPrimes(n);
                        
                        int max = 0;
                        int maxsum = 0;
                        
                        for(int j  = 0 ; j <  pr.size() ; j++)
                        {
                        	if(pr.get(j) >= m)
                        	{
                        		int sum = 0;
                        		int teller = 0;
                        		
                        		for(int k = j; k < pr.size() ; k++)
                        		{
                        			sum += pr.get(k);
                        			teller++;
                        			if(sum > n)
                        			{
                        				break;
                        			}
                        			else if(pr.contains(sum))
                        			{
                        				if( teller > max )
                        				{
                        					max = teller;
                        					maxsum = sum;
                        				}
                        			}
                        			
                        		}
                        	}
                        }
                        
                        	System.out.println(maxsum);
                        }
                     }
        


private static ArrayList<Integer> getPrimes(int maxPrime) {

    // for how this works see http://primes.utm.edu/howmany.shtml
    int estimateNumPrimes = (int) (1.02*(maxPrime / (Math.log(maxPrime)-1)));
    ArrayList<Integer> primeList = new ArrayList<Integer>(estimateNumPrimes);

    /**
     * array where index represents odd number, eg if primes[x] = true
     * then (x*2)+1 is prime.
     */
    boolean[] primes = new boolean[maxPrime/2+1];


    java.util.Arrays.fill(primes, true);
    primes[0] = false; // this actually represents 1
    primes[1] = true;  // this actually represents 3
    primeList.add(2);
    int rootMP = (int) Math.floor(Math.sqrt(maxPrime));
    int halfMax = maxPrime/2;

    // get next prime
    for (int i=3; i<=rootMP;) {
        // use prime to amrk off all mutiples as not prime
        for(int j=((i*3)/2); j<=halfMax; j+=i) {
            primes[j] = false;
        }

        // just want to move up index's while not prime
        // rolled out like this to prevent needing division to get index
        i+=2;
        int k = i/2;
        while(primes[k]==false) {
            k++;
        }
        i = (k*2)+1;
    }

    // the index's represent odd numbers starting with 1
    for(int i=0; i<=halfMax; i++) {
        if(primes[i]) {
            primeList.add((i*2)+1);
        }
    }

    return primeList;
}
}