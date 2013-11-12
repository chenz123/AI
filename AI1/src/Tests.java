import java.util.ArrayList;
import java.util.Collections;


public class Tests {

	
	public static void main(String[] args){
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (Integer i = 0; i < 20; i++){
			Integer j = i % 10;
			//al.add(Collections.binarySearch(al, j, new IntegerComparator()) +1, j);
			int index = Collections.binarySearch(al, j, new IntegerComparator());
			al.add(index < 0 ? (index+1) * -1 : index, j);
			//al.add(j);
			//Collections.sort(al);
			System.out.println("Added : " + j.intValue());
		}
		System.out.println(al.toString());
	}
}
