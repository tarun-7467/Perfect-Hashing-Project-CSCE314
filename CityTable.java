package hash341;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CityTable implements Serializable{
	
	public ArrayList<City> cityArray = new ArrayList<City>();
	public ArrayList<ArrayList<City>> Hashtable_Primary = new ArrayList<ArrayList<City>>();
    public ArrayList<ArrayList<City>> Hashtable_Secondary = new ArrayList<ArrayList<City>>();
	public ArrayList<Hash24> hasharr = new ArrayList<Hash24>();
//    public ArrayList<Integer> cityCounter = new ArrayList<Integer>(24);
	int numCities = 0;
	int maxCollisions;
	boolean validTable;
	Hash24 h = new Hash24();
	
	public CityTable (String fname, int tsize) throws IOException {
	

		File file = new File("US_Cities_LL.txt");
	    Scanner sc = new Scanner(file);
	 
	    while (sc.hasNextLine()) {
	    	String a = sc.nextLine();
	    	float b = Float.parseFloat(sc.next());
	    	float c = Float.parseFloat(sc.next());
	    	
	    	if(sc.hasNextLine()) {
	    		sc.nextLine();
	    	}
	    	
	    	City temp = new City(a, b, c);
	    	cityArray.add(temp);
	    	numCities++;
	    	
	    }
	    
	    for(int i = 0; i < tsize; i++) {
			Hashtable_Primary.add(new ArrayList<City>());
		}
	    
	    System.out.println("Primary hash table hash function: ");
	    h.dump();
	    System.out.println();
	    
	    for(City a : cityArray) {
	    	int index = h.hash(a.name) % tsize;
	    	Hashtable_Primary.get(index).add(a);
	    }
	    
	    
	    System.out.println("Primary hash table statistics: ");
	    System.out.println("	Number of Cities: " + numCities);
	    System.out.println("	Table size: " + tsize);
	    
	    int [] collisions = new int[tsize];
	    
	    for(int i = 0; i < Hashtable_Primary.size(); i++) {
	    	collisions[i] = Hashtable_Primary.get(i).size();
	    }
	    
	    for(int i = 0; i < collisions.length; i++) {
	    	if(maxCollisions < collisions[i]) {
	    		maxCollisions = collisions[i];
	    	}
	    }
	    
	    System.out.println("	Max collisions = " + maxCollisions);
	    
	    int [] frequency = new int[25];
	    for(int i = 0; i < collisions.length; i++) {
	    	frequency[collisions[i]] += 1;
	    }
	    
	    for(int i = 0; i < 25; i++) {
	    	System.out.println("	# of primary slots with " + i + " cities = " + frequency[i]);
	    }
	    
	    System.out.println();
	    
	    System.out.println("*** Cities in the slot with most collisions ***");
	    
	    for(int i = 0; i < Hashtable_Primary.size(); i++) {
	    	if(Hashtable_Primary.get(i).size() == maxCollisions) {
	    		for(City a : Hashtable_Primary.get(i)) {
	    			System.out.println("	" + a);
	    		}
	    	}
	    }
	    
	    System.out.println();
	    
	    System.out.println("Secondary hash table statistics: ");
	    
	    for(int i = 0; i < tsize; i++) {
			Hashtable_Secondary.add(new ArrayList<City>());
		}
	    
	    for(int i = 0; i < tsize; i++) {
	    	hasharr.add(null);
	    }
	    
	    ArrayList<Integer> countTracker = new ArrayList<Integer>(tsize);
	    for(int i = 0; i < tsize; i++) {
	    	countTracker.add(0);
	    }
	    
	    for(int i = 0; i < Hashtable_Primary.size(); i++) {
	    	
	    	if(Hashtable_Primary.get(i).size() > 1) {
	    		validTable = false;
	    		int hashCount = 0;
		    	while(!validTable) {
//		    		System.out.println("a");
		    		ArrayList<City> new_cityArray = new ArrayList<City>(collisions[i] * collisions[i]);
		    		for(int l = 0; l < collisions[i] * collisions[i]; l++) {
		    			new_cityArray.add(null);
		    		}
		    		Hash24 h2 = new Hash24();
		    		hashCount++;
		    	    for(int j = 0; j < Hashtable_Primary.get(i).size(); j++) {
//		    	    	System.out.println("b");
		    	    	int index = h2.hash(Hashtable_Primary.get(i).get(j).getName());
		    	    	index %= (collisions[i] * collisions[i]);
//		    	    	System.out.println(index);
//		    	    	System.out.println(new_cityArray.size());
		    	    	if(new_cityArray.get(index) != null) {
//		    	    		System.out.println("c");
		    	    		break;
		    	    	}
		    	    	else {
		    	    		new_cityArray.set(index,  Hashtable_Primary.get(i).get(j));
		    	    		if(j == Hashtable_Primary.get(i).size() - 1) {
//		    	    			System.out.println("lets go");
		    	    			validTable = true;
		    	    			hasharr.set(i, h2);
		    	    		}
		    	    		Hashtable_Secondary.set(i, new_cityArray);
//		    	    		System.out.println("d");
		    	    	}
		    		}
		    	}
		    	
		    	countTracker.set(i, hashCount);
	    	}
	    	
	    	else {
	    		Hashtable_Secondary.set(i,  Hashtable_Primary.get(i));
	    	}
	    }
	    
	    int [] frequency2 = new int[21];
	    for(int i = 0; i < countTracker.size(); i++) {
	    	frequency2[countTracker.get(i)] += 1;
	    }
//	    System.out.println("aaaaaaaaa"); 
	    for(int i = 1; i < 21; i++) {
	    	System.out.println("	# of secondary hash tables trying " + i + " hash functions = " + frequency2[i]);
	    }
	    
	    int sum = 0;
	    for(int i = 1; i < 21; i++) {
	    	sum += frequency2[i];
	    }
	    
	    System.out.println();
	    System.out.println("Number of secondary hash tables with more than 1 item = " + sum);
	    
	    double average = 0;
	    for(int i = 1; i < 21; i++) {
	    	average += (i * frequency2[i]);
	    }
	    average /= sum;
	    
	    System.out.println("Average # of hash functions tried = " + average);
	   
	    
	}
	
	public CityTable() {}

	public City find(String cName) {
		City c = new City();
		int index = h.hash(cName) % Hashtable_Primary.size();
		
		if(Hashtable_Primary.get(index) == null) {
			return null;
		}
		
		else if(Hashtable_Primary.get(index).size() == 1 && Hashtable_Primary.get(index).get(0).getName().equals(cName)){
			return Hashtable_Primary.get(index).get(0);
		}
		
		else if(Hashtable_Primary.get(index).size() > 1) {
			Hash24 h3 = hasharr.get(index);
			int secondIndex = h3.hash(cName) % Hashtable_Secondary.get(index).size();
			if(Hashtable_Secondary.get(index).get(secondIndex) == null) {
				return null;
			}
			else if(Hashtable_Secondary.get(index).get(secondIndex).getName().equals(cName)) {
				return Hashtable_Secondary.get(index).get(secondIndex);
			}
					
		}
		return null;
		
	}
	
	public void writeToFile(String fName) throws IOException {
		FileOutputStream fon = new FileOutputStream(fName);
		
		ObjectOutputStream foon = new ObjectOutputStream(fon);
		
		foon.writeObject(this);
		
		foon.close();
	}
	
	
	public static CityTable readFromFile(String fName) throws FileNotFoundException, IOException, ClassNotFoundException{
//		FileInputStream fin = new FileInputStream(fName);
//		ObjectInputStream fon = new ObjectInputStream(fin);
//		CityTable example2 = (CityTable) fon.readObject();
////		fon.readObject(Hashtable_Primary);
//		fon.close();
//		
//		return example2;
		
		CityTable example2 = null;

		ObjectInputStream fon = new ObjectInputStream(new FileInputStream(fName));
		example2 = (CityTable) fon.readObject();
		fon.close();
		
		
		return example2;
	}
	
//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		CityTable example = new CityTable("US_Cities_LL.ser", 1600);
//		
//		System.out.println("The City You Searched For is: " + example.find("Abbeville, LA"));
//		City a = example.find("Abbeville, LA");
////		System.out.println("https://www.google.com/maps?z=10&q=" + a.getLatitude() + "," + a.getLongitude());
//		System.out.println("www.google.com/maps?z=10&q=" + a.getLatitude() + "," + a.getLongitude());
//		
//	}
	
}
		

