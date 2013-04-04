import java.io.Serializable;

public class Person implements Serializable{
	private int startNr;
	private String namn;
	private String country;
	private int age;
	private double time = Double.MAX_VALUE;
	
	Person(int startnr, String namn, String country, int age){
		this.startNr = startnr;
		this.namn = namn;
		this.country = country;
		this.age = age;
	}
	
	// Get methods for all attributes
	public int getStartNr(){
		return startNr;
	}
	public String getNamn(){
		return namn;
	}
	public String getCountry(){
		return country;
	}
	public int getAge(){
		return age;
	}
	public double getTime(){
		return time;
	}
	
	public String printTime(){
		if (time == Double.MAX_VALUE)
			return "--";
		else
			return ""+time;
	}		
	
	// Set methods for the attributes that can be modified
	public void setTime(double time){
		this.time = time;
	}
	
	public String toString(){
		return startNr + "  " + namn + "\t" + age + " \t" + printTime() + "\n";
	}
}
