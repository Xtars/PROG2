import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.io.*;

public class Marathon extends JFrame{
	JTextArea listArea;
	private static ArrayList<Person> allPersons = new ArrayList<Person>();
	
	Marathon(){
		super("DSV Kista Marathon");
		
		// Read in existing persons from file
		try {
			FileInputStream fil = new FileInputStream("AllPersons.pj");
			ObjectInputStream in = new ObjectInputStream(fil);
			allPersons = (ArrayList<Person>) in.readObject();
		} catch (FileNotFoundException e){
			// No file found. Continue with empty list
		} catch (IOException e){
			add(new JOptionPane("IOException: " + e));
			System.exit(3);
		} catch (ClassNotFoundException e){
			add(new JOptionPane("ClassNotFoundException: " + e));
			System.exit(4);
		}
		
		// NORTH -- 
		add(new JLabel("DSV Kista Marathon"), BorderLayout.NORTH);
		
		// CENTER
		listArea = new JTextArea();
		listArea.setEditable(false);
		JScrollPane listScroll = new JScrollPane(listArea);
		add(listScroll, BorderLayout.CENTER);
		
		// SOUTH
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton newPersonButton = new JButton("Ny");
		newPersonButton.addActionListener(new NewPerson());
		JButton showButton = new JButton("Visa");
		showButton.addActionListener(new Show());
		JButton timeButton = new JButton("Tid");
	//	timeButton.addActionListener(new Time());
		southPanel.add(newPersonButton);
		southPanel.add(showButton);
		southPanel.add(timeButton);
		add(southPanel, BorderLayout.SOUTH);
		
		// EAST
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(5,1));
		JLabel sortByLabel = new JLabel("Sortering");
		JRadioButton[] radioArray = new JRadioButton[4];
		radioArray[0] = new JRadioButton("Startnr", true);
		radioArray[1] = new JRadioButton("Namn", false);
		radioArray[2] = new JRadioButton("Ålder", false);
		radioArray[3] = new JRadioButton("Tid", false);
		ButtonGroup radioGroup = new ButtonGroup();
		for (JRadioButton b : radioArray){
			radioGroup.add(b);
		}
		eastPanel.add(sortByLabel);
		for (JRadioButton b : radioArray){
			eastPanel.add(b);
		}
		add(eastPanel, BorderLayout.EAST);
		
		setSize(new Dimension(350,350));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class Show implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			listArea.setText("");
			for(Person p : allPersons){
				listArea.append(p.toString());
			}
		}
	}
	
	class NewPersonForm extends JPanel{
		private JTextField nameField = new JTextField(10);
		private JTextField countryField = new JTextField(10);
		private JTextField ageField = new JTextField(5);
		public NewPersonForm(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel row1 = new JPanel();
			nameField = new JTextField(10);
			row1.add(new JLabel("Namn:"));
			row1.add(nameField);
			add(row1);
			JPanel row2 = new JPanel();
			countryField = new JTextField(10);
			row2.add(new JLabel("Land:"));
			row2.add(countryField);
			add(row2);
			JPanel row3 = new JPanel();
			ageField = new JTextField(5);
			row3.add(new JLabel("Ålder:"));
			row3.add(ageField);
			add(row3);
		}
		public String getName(){
			return nameField.getText();
		}
		public String getCountry(){
			return countryField.getText();
		}
		public int getAge(){
			return Integer.parseInt(ageField.getText());
		}
	}
	
	class NewPerson implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			NewPersonForm newPersonForm = new NewPersonForm();
			for(;;){
				try {
					int i = JOptionPane.showConfirmDialog(null, newPersonForm, "Ny person",JOptionPane.OK_CANCEL_OPTION);
						if (i == 0){
							Person p = new Person(	allPersons.size()+1,
													newPersonForm.getName(),
													newPersonForm.getCountry(),
													newPersonForm.getAge());
							allPersons.add(p);
						}
					break;
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Ålder måste vara ett heltal!", "Fel", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static void main(String[] args){
		new Marathon();
	}
}
