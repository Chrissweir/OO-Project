package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 
 * @author Christopher Weir - G00309429
 * This class is responsible for taking in a jar file and reading its classes.
 *
 */

public class Reader {
	
	public Reader() {}

	/**
	 * Retrieves the jar from the specified jar
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readJarFile(String jarFile) throws FileNotFoundException, IOException{
		JarInputStream in = new JarInputStream(new FileInputStream(new File(jarFile)));
		JarEntry next = in.getNextJarEntry();
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
				System.out.println(name);
			}
			next = in.getNextJarEntry();
		}
		in.close();
	}
}