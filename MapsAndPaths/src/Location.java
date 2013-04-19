public class Location {
	private String name;
	
	Location(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean equals(Object o){
		if (o == null || this.getClass() != o.getClass()){
			return false;
		} else {
			Location l = (Location) o;
			return l.getName().equals(this.getName());
		}
	}
	
	public String toString(){
		return name;
	}
}
