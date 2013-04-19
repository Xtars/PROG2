import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Graphs.*;

public class TravelPlanner extends JFrame{
	TravelPlanner(){
		
	}
	
	public static void main(String[] args){		
		Graph g = new MatrixGraph<Location>(10);
		
		Location lA = new Location("A");
		Location lB = new Location("B");
		Location lC = new Location("C");
		Location lD = new Location("D");
		Location lE = new Location("E");
		Location lF = new Location("F");
		Location lG = new Location("G");
		
		g.add(lA);
		g.add(lB);
		g.add(lC);
		g.add(lD);
		g.add(lE);
		g.add(lF);
		g.add(lG);
		
		g.connect(lA, lB, "bil", 1);
		g.connect(lA, lD, "flyg", 28);
		g.connect(lB, lC, "bil", 2);
		g.connect(lC, lD, "bil", 7);
		g.connect(lD, lE, "bil", 4);
		g.connect(lE, lF, "bil", 5);
		g.connect(lF, lG, "bil", 6);
		
		System.out.println(g);
		
	}
}
