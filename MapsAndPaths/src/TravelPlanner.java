import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Graphs.*;
import java.util.*;

public class TravelPlanner extends JFrame{
	TravelPlanner(){
		
	}
	
	public static void main(String[] args){		
		MatrixGraph g = new MatrixGraph<Location>(10);
		
		Location lA = new Location("A");
		Location lFail = new Location("A");
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
		
		g.connect(lA, lB, "bil", 2);
		g.connect(lA, lB, "tåg", 1);
		g.connect(lA, lB, "flyg", 3);
		g.connect(lB, lC, "bil", 2);
		g.connect(lC, lD, "bil", 7);
		g.connect(lD, lE, "bil", 4);
		g.connect(lF, lG, "bil", 6);
		
		System.out.println(g);
		
		//g.add(lFail); // lFail-name == lA-name -> should produce a fail
		//g.remove(lB);
		//g.connect(lA, lB, "bil", 5); // there already a "bil" connection between lA and lB -> should produce a fail
		//g.disconnect(lB, lA);
		//g.setConnectionWeight(lA, lB, "bil", 5);
		//System.out.println(g.getEdgesFrom(lA));
		//System.out.println(g.getEdgesBetween(lA, lB));
		ArrayList al = g.fastestPath(lA, lE);
		System.out.println(al);
		
		System.out.println();
		System.out.println(g);
		
	}
}
