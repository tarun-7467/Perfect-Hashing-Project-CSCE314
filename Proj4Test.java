/* File: Proj4Test.java

   Reads in CityTable from a file and exercises
   the find() method a few times.
 
*/

package hash341;

import java.util.* ;
import java.io.* ;
import hash341.City ;
import hash341.CityTable ;

public class Proj4Test{

   public static void printCity(City aCity) {
      if (aCity == null) {
         System.out.println("*** Not found!!!") ;
      } else {
         System.out.println( aCity.name + " (" + aCity.latitude + "," + aCity.longitude + ")" ) ;
      }
   }


   public static void main (String [] args) throws ClassNotFoundException, IOException {   

      CityTable US_Cities ;
      String cName ;

      US_Cities = CityTable.readFromFile("US_Cities_LL.ser") ;

      cName = "Arbutus, MD" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Catonsville, MD" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Baltimore, MD" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Amherst, MA" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "North Amherst, MA" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "South Amherst, MA" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Boston, MA" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Potsdam, NY" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Sault Sainte Marie, MI" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "International Falls, MN" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Truth or Consequences, NM" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Neverland, TX" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Anytown, USA" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "Garbage Land, NY" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "just some search string" ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

      cName = "a really long search string that is the first few sentences from Chapter 1 of Moby Dick: Call me Ishmael. Some years ago --- never mind how long precisely --- having little or no money in my purse, and nothing particular to interest me on shore, I thought I would sail about a little and see the watery part of the world. It is a way I have of driving off the spleen and regulating the circulation. Whenever I find myself growing grim about the mouth; whenever it is a damp, drizzly November in my soul; whenever I find myself involuntarily pausing before coffin warehouses, and bringing up the rear of every funeral I meet; and especially whenever my hypos get such an upper hand of me, that it requires a strong moral principle to prevent me from deliberately stepping into the street, and methodically knocking people's hats off --- then, I account it high time to get to sea as soon as I can.  This is my substitute for pistol and ball. With a philosophical flourish Cato throws himself upon his sword; I quietly take to the ship. There is nothing surprising in this. If they but knew it, almost all men in their degree, some time or other, cherish very nearly the same feelings towards the ocean with me." ;
      System.out.println("\nSearching for " + cName) ;
      printCity( US_Cities.find(cName) ) ;

   } 


}
