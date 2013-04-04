import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.io.*;

public class Marathon extends JFrame{
	JTextArea listArea;
	private static ArrayList<Person> allPersons = new ArrayList<Person>();
	JRadioButton[] radioArray = new JRadioButton[4];
	private static Marathon m;
	Show updateTextArea = new Show();
	
	Marathon(){
		super("DSV Kista Marathon");
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
				try {
					FileOutputStream fil = new FileOutputStream("AllPersons.pj");
					ObjectOutputStream out = new ObjectOutputStream(fil);
					out.writeObject(allPersons);
					System.out.println("Saved allPersons successfully!");
					System.exit(0);
				} catch (IOException e){
					JOptionPane.showMessageDialog(m, "IOException: " + e, "Fel", JOptionPane.ERROR_MESSAGE);
					System.out.println("allPersons was not saved.");
					System.exit(1);
				}
		    }
		});
		
		// Read in existing persons from file
		try {
			FileInputStream fil = new FileInputStream("AllPersons.pj");
			ObjectInputStream in = new ObjectInputStream(fil);
			allPersons = (ArrayList<Person>) in.readObject();
		} catch (FileNotFoundException e){
			// File not found, continue with empty list
		} catch (IOException e){
			JOptionPane.showMessageDialog(this, "IOException: " + e, "Fel", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		} catch (ClassNotFoundException e){
			JOptionPane.showMessageDialog(this, "ClassNotFoundException: " + e, "Fel", JOptionPane.ERROR_MESSAGE);
			System.exit(3);
		}
		
		// NORTH -- 
		add(new JLabel("DSV Kista Marathon"), BorderLayout.NORTH);
		
		// CENTER
		listArea = new JTextArea();
		listArea.setEditable(false);
		for(Person p : allPersons){
			listArea.append(p.toString());
		}
		JScrollPane listScroll = new JScrollPane(listArea);
		add(listScroll, BorderLayout.CENTER);
		
		// SOUTH
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton newPersonButton = new JButton("Ny");
		newPersonButton.addActionListener(new NewPerson());
		JButton showButton = new JButton("Visa");
		showButton.addActionListener(updateTextArea);
		JButton timeButton = new JButton("Tid");
		timeButton.addActionListener(new AddTime());
		southPanel.add(newPersonButton);
		southPanel.add(showButton);
		southPanel.add(timeButton);
		add(southPanel, BorderLayout.SOUTH);
		
		// EAST
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(5,1));
		JLabel sortByLabel = new JLabel("Sortering");
		radioArray[0] = new JRadioButton("Startnr", true);
		radioArray[1] = new JRadioButton("Namn", false);
		radioArray[2] = new JRadioButton("Ålder", false);
		radioArray[3] = new JRadioButton("Tid", false);
		SortLiss radioListener = new SortLiss();
		ButtonGroup radioGroup = new ButtonGroup();
		for (JRadioButton b : radioArray){
			b.addActionListener(updateTextArea);
			b.addActionListener(radioListener);
			radioGroup.add(b);
		}
		eastPanel.add(sortByLabel);
		for (JRadioButton b : radioArray){
			eastPanel.add(b);
		}
		add(eastPanel, BorderLayout.EAST);
		
		setSize(new Dimension(350,350));
		setLocation(new Point(400,300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	} // Constructor
	
	// Listener for radio-buttons
	class SortLiss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			if (radioArray[0].isSelected()){
				StartnrCmp cmp = new StartnrCmp();
				Collections.sort(allPersons, cmp);
			}
			else if (radioArray[1].isSelected()){
				NameCmp cmp = new NameCmp();
				Collections.sort(allPersons, cmp);
			}
			else if (radioArray[2].isSelected()){
				AgeCmp cmp = new AgeCmp();
				Collections.sort(allPersons, cmp);
			}
			else if (radioArray[3].isSelected()){
				TimeCmp cmp = new TimeCmp();
				Collections.sort(allPersons, cmp);
			}
		} // actionPerformed
	} // SortLiss
	
	// Listener for "Tid"-button
	class AddTime implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			TimeForm timeForm = new TimeForm();
			boolean updated = false;
			for(;;){
				try {
					int i = JOptionPane.showConfirmDialog(m, timeForm, "Ny tid",JOptionPane.OK_CANCEL_OPTION);
					if (i == 0){ // OK was pushed, look for the entered startNr in allPersons
						for (Person p : allPersons){
							if (p.getStartNr() == timeForm.getStartNr()){
								p.setTime(timeForm.getTime());
								updated = true;
								updateTextArea.actionPerformed(null); // Update textArea automatically when a time has been updated
								break;
							} // if
						} // for
						if (updated) // If updated, break the infinite loop
							break;
						else  // Else, show error then run another iteration of the infinite loop
							JOptionPane.showMessageDialog(m, "Det startnummret hittades inte!", "Fel", JOptionPane.ERROR_MESSAGE);
					} // if i == 0
					else if (i == 2) // Cancel was pushed, break the infinite loop
						break;
					else if (i == -1) // Window closed using X, break the infinite loop
						break;
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(m, "Fel input!", "Fel", JOptionPane.ERROR_MESSAGE);
				} // catch
			} // infnite for
		} // actionPerformed
	} // AddTime
	
	// Listener for "Visa"-button
	class Show implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			listArea.setText("");
			for(Person p : allPersons){
				listArea.append(p.toString());
			}
		}
	}
	
	// Listener for "Ny"-button	
	class NewPerson implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			NewPersonForm newPersonForm = new NewPersonForm();
			for(;;){
				try {
					int i = JOptionPane.showConfirmDialog(m, newPersonForm, "Ny person",JOptionPane.OK_CANCEL_OPTION);

					// OK was pressed with no NFE	
					if (i == 0){
						// Create the person based on the answers in the form
						Person p = new Person(	allPersons.size()+1,
												newPersonForm.getName(),
												newPersonForm.getCountry(),
												newPersonForm.getAge());
						// Add to the list and save the list to file
						allPersons.add(p);
						updateTextArea.actionPerformed(null); // Update textArea automatically when a new person has been added
					} // if i == 0
					// This break will break the loop if a person was added or if Cancel/X was pressed
					break;
				} catch (NumberFormatException e){
					// Wrong type on the Age field, loop will run again
					JOptionPane.showMessageDialog(m, "Ålder måste vara ett heltal!", "Fel", JOptionPane.ERROR_MESSAGE);
				}
			} // infnite for
		} // actionPerformed
	} // NewPerson
	
	// Compare two persons by startNr
	class StartnrCmp implements Comparator<Person>{
		public int compare(Person p1, Person p2){
			return p1.getStartNr() - p2.getStartNr();
		}
	}
	
	// Compare two persons by name
	class NameCmp implements Comparator<Person>{
		public int compare(Person p1, Person p2){
			return p1.getNamn().compareTo(p2.getNamn());
		}
	}
	
	// Compare two persons by age
	class AgeCmp implements Comparator<Person>{
		public int compare(Person p1, Person p2){
			return p1.getAge() - p2.getAge();
		}
	}
	
	// Compare two persons by time
	class TimeCmp implements Comparator<Person>{
		public int compare(Person p1, Person p2){
			if (p1.getTime() > p2.getTime())
				return 1;
			if (p1.getTime() < p2.getTime())
				return -1;
			else
				return 0;
		}
	}
	
	// Form for the "Tid"-popup
	class TimeForm extends JPanel{
		private JTextField startNrField = new JTextField(10);
		private JTextField timeField = new JTextField(10);
		public TimeForm(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel row1 = new JPanel();
			startNrField = new JTextField(10);
			row1.add(new JLabel("Startnr:"));
			row1.add(startNrField);
			add(row1);
			JPanel row2 = new JPanel();
			timeField = new JTextField(10);
			row2.add(new JLabel("Tid:"));
			row2.add(timeField);
			add(row2);
		}
		public int getStartNr(){
			return Integer.parseInt(startNrField.getText());
		}
		public double getTime(){
			return Double.parseDouble(timeField.getText());
		}
	} // TimeForm
	
	// Form for the "Ny"-popup
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
	} // NewPersonForm
	
	public static void main(String[] args){
		m = new Marathon();
	}
}