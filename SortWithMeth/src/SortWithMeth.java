import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SortWithMeth {
	private static Scanner scanner = new Scanner(System.in);
	
	public static ArrayList<Integer> bubbleSort(ArrayList<Integer> list){
		boolean switched;;
		int temp;
		int iterations = 0;
		
		System.out.println("Sorting with Bubblesort...");
		long start = System.currentTimeMillis();
		for (;;){
			iterations += 1;
			switched = false;
			for(int i = 0; i < list.size()-1; i++){
				if (list.get(i) > list.get(i+1)){
					temp = list.get(i);
					list.set(i, list.get(i+1));
					list.set(i+1, temp);
					switched = true;
				}
			}
			if (!switched){
				System.out.println("Sorted!, took " + (System.currentTimeMillis()-start) + "ms");
				System.out.println("No of iterations: " + iterations);
				return list;
			}
		}
	}
	
	public static ArrayList<Integer> insertSort(ArrayList<Integer> list){
		int i, j, newValue;
		
		System.out.println("Sorting with Insertion sort...");
		long start = System.currentTimeMillis();
		for (i = 1; i < list.size(); i++){
			newValue = list.get(i);
			j = i;
			while (j > 0 && list.get(j - 1) > newValue){
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, newValue);
		}
		System.out.println("Sorted!, took " + (System.currentTimeMillis()-start) + "ms");
		
		return list;
		
	}
	
	public static void quickSort(ArrayList<Integer> list, int left, int right){
	      int index = partition(list, left, right);
	      if (left < index - 1)
	            quickSort(list, left, index - 1);
	      if (index < right)
	            quickSort(list, index, right);
	}
	
	/*public ArrayList<Integer> quickSortThread(ArrayList<Integer> list){
	
	}*/
	
	public  static int partition(ArrayList<Integer> list, int left, int right){
	      int i = left, j = right;
	      int tmp;
	      int pivot = list.get((left + right) / 2);
	     
	      while (i <= j) {
	            while (list.get(i) < pivot)
	                  i++;
	            while (list.get(j) > pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = list.get(i);
	                  list.set(i, list.get(j));
	                  list.set(j, tmp);
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
	
	
	public static void main(String[] args){		
		System.out.println("Program started!");
		Random rand = new Random();
		
		System.out.println("Populating list...");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 10000; i++){
			list.add(i);
		}
		
		boolean run = true;
		while(run){
			System.out.println("\nRandomizing...");
			for (int j = 0; j < 200000; j++){
				int n = list.remove(rand.nextInt(list.size()));
				list.add(n);
			}
			
			System.out.print("0: exit   1: bubblesort   2: insertionsort   3: quicksort >> ");
			int command = scanner.nextInt();
			System.out.println();
			
			switch(command){
			case 0:
				run = false;
				break;
			case 1:
				bubbleSort(list);
				System.out.println(list);
				break;
			case 2:
				insertSort(list);
				System.out.println(list);
				break;
			case 3:
				System.out.println("Sorting with quicksort...");
				long start = System.currentTimeMillis();
				quickSort(list, 0, list.size()-1);
				System.out.println("Sorted!, took " + (System.currentTimeMillis()-start) + "ms");
				System.out.println(list);
				break;
			default:
				break;
			}
		}
		System.out.println("Exiting...");
		System.exit(0);
	}
}
