package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestRunner {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Reader reader = new Reader();
        reader.readJarFile("C:/Users/Chris/Documents/GitHub/OO-Project/string-service.jar");
    }
}
