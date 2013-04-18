import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Graphs.*;

public class TravelPlanner extends JFrame{
	TravelPlanner(){
		
	}
	
	public static void main(String[] args){
		System.out.println("test");
		
		Graph g = new MatrixGraph<Location>(10);
		
		Location lA = new Location("A");
		Location lB = new Location("B");
		Location lC = new Location("C");
		Location lD = new Location("D");
		Location lE = new Location("E");
		Location lF = new Location("F");
		Location lG = new Location("G");
		Location lH = new Location("H");
		Location lI = new Location("I");
		Location lJ = new Location("J");
		
		g.add(lA);
		g.add(lB);
		g.add(lC);
		g.add(lD);
		g.add(lE);
		g.add(lF);
		g.add(lG);
		g.add(lH);
		g.add(lI);
		g.add(lJ);
		
		g.connect(lA, lB, "bil", 5);
		g.connect(lB, lC, "bil", 5);
		g.connect(lC, lJ, "bil", 5);
		g.connect(lD, lE, "bil", 5);
		g.connect(lF, lH, "bil", 5);
		g.connect(lI, lH, "bil", 5);
		g.connect(lA, lH, "bil", 5);
		
		System.out.println(g);
		
		g.setConnectionWeight(lA, lB, "flyg", 347834);
		
		System.out.println(g);
		
	}
}
