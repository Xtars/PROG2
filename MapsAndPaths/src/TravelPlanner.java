import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import Graphs.*;

public class TravelPlanner extends JFrame{
	private static boolean saved;
	
	TravelPlanner(){
		super("Maps and paths");
		saved = false;
		
		/*addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
				try {
					FileOutputStream fil = new FileOutputStream("AllPersons.pj");
					ObjectOutputStream out = new ObjectOutputStream(fil);
					out.writeObject(allPersons);
					System.exit(0);
				} catch (IOException e){
					JOptionPane.showMessageDialog(m, "IOException: " + e, "Fel", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
		    }
		});*/
		
		JMenuBar mb = new JMenuBar();
		JMenu[] menu = new JMenu[2];
		menu[0] = new JMenu("File");
		menu[1] = new JMenu("Operations");
		setJMenuBar(mb);
		for (JMenu j : menu){
			mb.add(j);
		}
		
		FindPath fpListener = new FindPath();
		ShowConn scListener = new ShowConn();
		NewLoc nlListener = new NewLoc();
		NewConn ncListener = new NewConn();
		EditConn ecListener = new EditConn();
		
		JMenuItem[] fileMenu = new JMenuItem[5];
		fileMenu[0] = new JMenuItem("New");
		fileMenu[0].addActionListener(new NewFile());
		fileMenu[1] = new JMenuItem("Open");
		fileMenu[1].addActionListener(new Open());
		fileMenu[2] = new JMenuItem("Save");
		fileMenu[2].addActionListener(new Save());
		fileMenu[3] = new JMenuItem("Save as");
		fileMenu[3].addActionListener(new SaveAs());
		fileMenu[4] = new JMenuItem("Exit");
		fileMenu[4].addActionListener(new Exit());
		for (JMenuItem i : fileMenu){
			menu[0].add(i);
		}
		
		JMenuItem[] opsMenu = new JMenuItem[5];
		opsMenu[0] = new JMenuItem("Find path");
		opsMenu[0].addActionListener(fpListener);
		opsMenu[1] = new JMenuItem("Show connections");
		opsMenu[1].addActionListener(scListener);
		opsMenu[2] = new JMenuItem("New location");
		opsMenu[2].addActionListener(nlListener);
		opsMenu[3] = new JMenuItem("New connection");
		opsMenu[3].addActionListener(ncListener);
		opsMenu[4] = new JMenuItem("Change connections");
		opsMenu[4].addActionListener(ecListener);
		for (JMenuItem i : opsMenu){
			menu[1].add(i);
		}
		
		
		// NORTH
		JPanel buttonPanel = new JPanel();
		JButton[] buttons = new JButton[5];
		buttonPanel.setLayout(new FlowLayout());
		add(buttonPanel);
		buttons[0] = new JButton("Find path");
		buttons[0].addActionListener(fpListener);
		buttons[1] = new JButton("Show connections");
		buttons[1].addActionListener(scListener);
		buttons[2] = new JButton("New location");
		buttons[2].addActionListener(nlListener);
		buttons[3] = new JButton("New connections");
		buttons[3].addActionListener(ncListener);
		buttons[4] = new JButton("Edit connection");
		buttons[4].addActionListener(ecListener);
		for (JButton b : buttons){
			buttonPanel.add(b, BorderLayout.NORTH);
		}
		
		pack();
		setLocation(new Point(400,300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class FindPath implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("FindPath");
		}
	}
	class ShowConn implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("ShowConn");
		}
	}
	class NewLoc implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("NewLoc");
		}
	}
	class NewConn implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("NewConn");
		}
	}
	class EditConn implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("EditConn");
		}
	}
	
	class NewFile implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("New");
		}
	}
	class Open implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Open");
		}
	}
	class Save implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Save");
		}
	}
	class SaveAs implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("SaveAs");
		}
	}
	class Exit implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Exit");
		}
	}
	
	public static void main(String[] args){		
		TravelPlanner tp = new TravelPlanner();
		
		MatrixGraph<Location> g = new MatrixGraph<Location>(10);

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
		g.connect(lA, lC, "flyg", 4);
		g.connect(lB, lC, "bil", 2);
		g.connect(lC, lD, "bil", 7);
		g.connect(lD, lE, "bil", 4);
		g.connect(lF, lG, "bil", 6);
		
		System.out.println(g);
		
		//g.add(lFail); // lFail-name == lA-name -> should produce a fail
		//g.remove(lB);
		//g.connect(lA, lB, "bil", 5); // there already a "bil" connection between lA and lB -> should produce a fail
		//g.disconnect(lB, lA);
		//g.setConnectionWeight(lA, lC, "flyg", 232);
		//g.setConnectionWeight(lA, lD, "bil", 5); // no edge here, should produce a fail
		//g.setConnectionWeight(lA, lB, "bilNope", 5); // there are edges, but not with that name, should produce a fail
		//g.setConnectionWeight(lA, lB, "bil", -5); // weight can't be negative, should produce a fail
		System.out.println(g.getEdgesFrom(lA));
		System.out.println(g.getEdgesBetween(lA, lC));
		//System.out.println(g.fastestPath(g, lA, lE));
		//System.out.println(g.fastestPath(lA, lF)); // no path between those nodes, should produce a fail
		
		//System.out.println();
		//System.out.println(g);
		
	}
}
