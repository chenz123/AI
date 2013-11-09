package aiutils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

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
}
