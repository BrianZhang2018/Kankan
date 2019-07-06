package category.javabasic;

import java.util.BitSet;
import java.util.Arrays;

/**
 * Created by brianzhang on 11/5/18.
 */
public class CountPrimeNumber {
    public static void main(String[] args){
        //System.out.println(4>>2);
        System.out.println(countPrimes(10));
    }

    // Sieve_of_Eratosthenes algorithm
    // It is called the Sieve of Eratosthenes algorithm. In the boolean array m, m[n] means the number n. 
    // Thus for each time, if m[n] is a prime, we need to delete all the multiple of m[n]. And finally, the remaining numbers are primes.
    public static int countPrimes(int n) {
        if(n==0)
            return 0;
        
        boolean[] isPrimes = new boolean[n+1];
        Arrays.fill(isPrimes, true);
        int count = 0;
        
        for(int i=2; i<n; i++){
            if(!isPrimes[i])
                continue;
            count++;
            for(int j=i; j<n; j= j+i){
                isPrimes[j] = false;
            }
        }
        return count;
    }

    //BitSet Solution
    public static int countPrimes1(int n) {
        BitSet bs = new BitSet(n);
        bs.set(0); 
        bs.set(1);
        int ind = 0, count = 0;
        while(ind < n){
            ind = bs.nextClearBit(ind + 1);
            if(ind >= n)
                return count;
            count++;
            for(int i = 2 * ind; i < n; i += ind)
                bs.set(i);
        }
        return count;
    }
}