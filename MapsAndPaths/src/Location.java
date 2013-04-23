public class Location implements Comparable<Location>{
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
			return o.hashCode() == this.hashCode();
		}
	}
	
	public int compareTo(Location l){
		return name.compareTo(l.getName());
	}
	
	public String toString(){
		return name;
	}
}
