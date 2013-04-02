
public class Person {
	private int startNr;
	private String namn;
	private String country;
	private int age;
	private double time = -1;
	
	Person(int startnr, String namn, String country, int age){
		this.startNr = startnr;
		this.namn = namn;
		this.country = country;
		this.age = age;
	}
	
	// Get methods for all attributes
	public int getStartnr(){
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
	
	// Set methods for the attributes that can be modified
	public void setCountry(String country){
		this.country = country;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setTime(double time){
		this.time = time;
	}
	
	public String toString(){
		return startNr + "  " + namn + "  " + age + "  " + time;
	}
}
