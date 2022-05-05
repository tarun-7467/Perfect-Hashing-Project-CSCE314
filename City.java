package hash341;

import java.io.Serializable;

public class City implements Serializable{
	
	public String name;
	public float longitude;
	public float latitude;
	
	public City (String a, float b, float c) {
		name = a;
		longitude = b;
		latitude = c;
	}
	
	
	public City() {}

	public void setName(String n) {name = n;}
	
	public void setLongitude(float n) {longitude = n;}
	
	public void setLatitude(float n) {latitude = n;}
	
	public String getName() {return this.name;}
	
	public float getLongitude() {return this.longitude;}
	
	public float getLatitude() {return this.latitude;}
	
	public String toString() {
		return getName() + " (" + getLongitude() + "," + getLatitude() + ")";
	}
}

