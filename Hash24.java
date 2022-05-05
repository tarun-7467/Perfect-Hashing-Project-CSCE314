
/* File: Hash24.java

   Implements universal hash functions for 24-bit integers.
   This class is suitable for producing hash functions for 
   hash tables up to ~2^24 entries (roughly 16 million).

   Each call to the constructor will create a "random" hash function
   object. The hash() method can then be invoked through the object. 

   Note that the value returned by hash() must still be "mod out"
   by the table size.

*/
package hash341;
import java.util.* ;
import java.io.Serializable;


public class Hash24 implements Serializable {


   // some prime numbers of suitable size
   //
   static long prime1 = 16890581L ;   // prime number ~ 2^24 = 16777216
   static long prime2 = 17027399L ;   // larger prime ~ 2^24 = 16777216


   // remembers if the pseudo random generator has been initialized. 
   //
   static boolean initialized = false ;


   // reference to the pseudo-random generator (prg)
   //
   static Random prg ;


   // random values for the hash function
   //
   long random_a ;
   long random_b ;
   long random_c ;


   public Hash24() {

      // initialize the pseudo random generator, if not yet done.
      // Use system time in milliseconds as random seed.
      //
      if ( !initialized ) {
         prg = new Random ( System.currentTimeMillis() ) ;
         initialized = true ;
      }

      // generate and store some random values
      //
      random_a = prg.nextInt((int) prime2-1) + 1 ;   // 1 <= random_a < prime2
      random_b = prg.nextInt((int) prime2) ;         // 0 <= randomo_b < prime2
      random_c = prg.nextInt((int) prime1-1) + 1 ;   // 1 <= random_c < prime1

   }


   // A universal hash function.
   // Not that choice of number sizes should guarantee that the only
   // overflow is from the % prime2 and not from integer * or +.
   //
   public int hash(long x) {
       if (x >= prime2) throw new UnsupportedOperationException() ;
       return (int) ( ( random_a * x + random_b ) % prime2 ) ;
   }


   // A universal hash function for strings.
   // Computes  d[0] * c^t + d[1] * c^(t-1) + d[2] * c^(t-2) + ... + d[t]
   // where str = d[0..t]
   // Computation is done using Horner's rule.
   // The only overflow is from the % prime1 and not from 
   // integer + or integer *.
   //
   public int hash(String str) {
   
      long result = 0 ;
      for (int i = 0 ; i < str.length() ; i++) {
          result = ( random_c * result + str.charAt(i) ) % prime1 ;
      }

      return hash(result) ;   // call long to int hash()
   }


   // Print out vital info.
   //
   public  void dump() {
      System.out.println("*** Hash24 dump ***") ;
      System.out.println("prime1 = " + prime1) ;
      System.out.println("prime2 = " + prime2) ;
      System.out.println("random_a = " + random_a) ;
      System.out.println("random_b = " + random_b) ;
      System.out.println("random_c = " + random_c) ;
   }

}

//Hash24.java.txt
//Displaying Hash24.java.txt.