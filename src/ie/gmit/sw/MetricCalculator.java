package ie.gmit.sw;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MetricCalculator {

	private Map<String, Metric> graph = new HashMap();
	private List<Class> jarClasses;
	private String jar;

	public MetricCalculator(List<Class> jarContent, String jarFile){
		this.jarClasses = jarContent;
		this.jar = jarFile;
		addClassToMap();
		calculateMetric();

	}

	public void addClassToMap(){
		System.out.println("Add to class started");
		for(int i = 0; i < jarClasses.size(); i++)
		{
			graph.put(jarClasses.get(i).getName(), new Metric());
			graph.get(jarClasses.get(i).getName()).setClassName(jarClasses.get(i).getName());
			System.out.println(jarClasses.get(i).getName());
		}
		System.out.println(graph.keySet());
		System.out.println("map size: " + graph.size());
	}

	public void calculateMetric(){

		try {
			File file  = new File(jar);
	        URL url = file.toURI().toURL();
	        URL[] urls = new URL[]{url};
			// create a ClassLoader to load classes from the JAR file
			ClassLoader cl = new URLClassLoader(urls);

			// loop for each key in the classMetrics map
			for (String className : graph.keySet()) {

				// get handle on class, using the class loader, not intialising the class
				Class cls = Class.forName(className, false, cl);
				System.out.println(className);
				// analyse class to calculate in and out degree
				new Reflection(cls);

			} // foreach
		} catch (Exception e){

			e.printStackTrace();
		}

	} // calculateBasicMetric()
}