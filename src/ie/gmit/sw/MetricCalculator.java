package ie.gmit.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Christopher Weir - G00309429	
 *
 *	This Class is responsible for calculating the stability
 */
/**
 * @author Chris
 *
 */
public class MetricCalculator{

	private Map<String, Metric> graph = new HashMap();
	private List<Class> jarClasses;
	private String jar;
	private JarReader j = new JarReader();

	/**
	 * 
     * Constructor
     *
     * @param jarFile
     * jarFile is a String that represents the JAR file path
     *
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public MetricCalculator(String jarFile) throws FileNotFoundException, IOException{
		this.jar = jarFile;
		this.jarClasses = j.readJarFile(jar);
		addClassToMap();
		calculateMetric();
	}

	/**
     * @return
     * Returns the metrics in the format of a 2 dimensional array to be used in
     * the GUI Table.
     * 
     */
	public Object[][] getData(){

		int i = 0;
		Object[][] data = new Object[graph.size()][4];

		// For each metric object in the map
		for(Metric m : graph.values()){
			// Add the data to the array
			data[i][0] = m.getClassName();  // set class name
			data[i][1] = m.getStability();  // set stability
			data[i][2] = m.getOutDegree();  // set outDegree
			data[i][3] = m.getInDegree();   // set inDegree

			i++;
		}

		return data;
	}

	/**
	 * Adds the class names to the map with the metrics
	 */
	private void addClassToMap(){

		System.out.println("Adding the Classes to the Map!\n");
		System.out.println("The following classes are being added:");
		for(int i = 0; i < jarClasses.size(); i++)
		{
			graph.put(jarClasses.get(i).getName(), new Metric());
			graph.get(jarClasses.get(i).getName()).setClassName(jarClasses.get(i).getName());
			System.out.println(jarClasses.get(i).getName());
		}
		System.out.println("The Map now contains " + graph.size() + " classes:");
		System.out.println(graph.keySet());
	}

	/**
	 * Loads each Class from the Jar File and Starts the calculations
	 * to get the in and out degree of each class using Java Reflection
	 */
	private void calculateMetric(){

		try {
			// Get the jar file
			File file = new File(jar);
			URL url = file.toURI().toURL();
			URL[] urls = new URL[]{url};

			// Use a ClassLoader to load the classes from the jar file
			ClassLoader cl = new URLClassLoader(urls);

			// For every key in the graph map
			for (String className : graph.keySet()) {
				// Load the Class
				Class cls = Class.forName(className, false, cl);

				// Use Java Reflection to calculate the in and out degree
				reflection(cls);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Uses Java Reflection to calculate the in and out degree of 
	 * each class
	 *
	 * @param cls
	 * The class being calculated.
	 */
	public void reflection(Class cls){
		// A list to track each referenced class to avoid duplicates
		List<String> classList = new ArrayList<String>();
		int outdegree = 0;

		Class[] interfaces = cls.getInterfaces(); // Get the set of Interfaces
		for(Class i : interfaces){
			//System.out.println(i.getName());
			if(graph.containsKey(i.getName())) {
				if(!classList.contains(i.getName())){
					//System.out.println(i.getName());
					classList.add(i.getName());
					outdegree++;
					Metric m = graph.get(i.getName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}

		Constructor[] cons = cls.getConstructors(); //Get the set of Constructors
		Class[] constructorParams;
		for(Constructor c : cons){
			constructorParams = c.getParameterTypes();
			for(Class param : constructorParams){
				//System.out.println(param.getName());
				if(graph.containsKey(param.getName())){
					//System.out.println(param.getName());
					if(!classList.contains(param.getName())){
						//System.out.println(param.getName());
						classList.add(param.getName());
						outdegree++;
						Metric m = graph.get(param.getName());
						m.setInDegree(m.getInDegree() + 1);
					}
				}
			}
		}

		Field[] fields = cls.getDeclaredFields(); // Get the set of Fields
		for(Field f : fields){
			Type type = f.getType();
			//System.out.println(type.getTypeName());
			if(graph.containsKey(type.getTypeName())){
				if(!classList.contains(type.getTypeName())){
					//System.out.println(type.getTypeName());
					classList.add(type.getTypeName());
					outdegree++;
					Metric m = graph.get(type.getTypeName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}

		Method[] methods = cls.getDeclaredMethods(); //Get the set of Methods
		Class[] methodParams;

		for(Method m : methods){
			Class methodReturnType = m.getReturnType();
			//System.out.println(methodReturnType.getName());
			if(graph.containsKey(methodReturnType.getName())){
				if(!classList.contains(methodReturnType.getName())){
					//System.out.println(methodReturnType.getName());
					classList.add(methodReturnType.getName());
					outdegree++;
					Metric mc = graph.get(methodReturnType.getName());
					mc.setInDegree(mc.getInDegree() + 1);
				}
			}

			methodParams = m.getParameterTypes(); //Get method parameters
			for(Class mp : methodParams){
				//System.out.println(mp.getName());
				if(graph.containsKey(mp.getName())){
					if(!classList.contains(mp.getName())){
						//System.out.println(mp.getName());
						classList.add(mp.getName());
						outdegree++;
						Metric bm = graph.get(mp.getName());
						bm.setInDegree(bm.getInDegree() + 1);
					}
				}
			} 
		}
		System.out.println();
		System.out.println("Class: " + cls.getName());
		// Set the total out degree of the Class
		graph.get(cls.getName()).setOutDegree(outdegree);
		System.out.println("Indegree: " + graph.get(cls.getName()).getInDegree());		
		System.out.println("Outdegree: " + graph.get(cls.getName()).getOutDegree());		
		System.out.println("Stability: " + graph.get(cls.getName()).getStability());
	}
}