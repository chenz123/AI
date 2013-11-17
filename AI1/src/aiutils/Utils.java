package aiutils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.Collections;
import java.util.Comparator;

public class Utils {

	public static String readFile(String strFile) throws IOException {
		File file = new File(strFile);
		URI uri = file.toURI();
		byte[] bytes = null;

		bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(uri));
		return new String(bytes);
	}

	public static void writeFile(String content, String strFile)
			throws IOException {
		File file = new File(strFile);
		URI uri = file.toURI();
		java.nio.file.Files.write(Paths.get(uri), content.getBytes());

	}
	
	public static <T> void addToSortedList(AbstractList<T> list, T key, Comparator<T> comparator ){
		int index = Collections.binarySearch(list, key, comparator);
		// handle non existent key
		index = index < 0 ? (index+1) * -1 : index;
		// push to last location possible
		while (index < list.size() && comparator.compare(list.get(index), key) == 0){
			index++;
		}
		list.add(index, key);
	}
}
