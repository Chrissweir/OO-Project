package ie.gmit.sw;

import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MetricCalculator implements testInterface{

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

			ClassLoader cl = new URLClassLoader(urls);

			for (String className : graph.keySet()) {

				Class cls = Class.forName(className, false, cl);
				reflection(cls);

			}
		} catch (Exception e){

			e.printStackTrace();
		}

	}

	public void reflection(Class cls){

		int outdegree = 0;

		boolean iface = cls.isInterface();

		Class[] interfaces = cls.getInterfaces();
		for(Class i : interfaces){

			if(graph.containsKey(i.getName())) {
				outdegree++;
				Metric m = graph.get(i.getName());
				m.setInDegree(m.getInDegree() + 1);
			}
		}

		Constructor[] cons = cls.getConstructors(); //Get the set of constructors
		Class[] constructorParams;

		for(Constructor c : cons){

			constructorParams = c.getParameterTypes();
			for(Class param : constructorParams){

				if(graph.containsKey(param.getName())){

					outdegree++;

					Metric m = graph.get(param.getName());
					m.setInDegree(m.getInDegree() + 1);
				}
			}
		}

		Field[] fields = cls.getDeclaredFields();

		for(Field f : fields)
		{
			Type type = f.getType();
			//System.out.println(type.getTypeName());
			if(graph.containsKey(type.getTypeName()))
			{
				outdegree++;
				Metric m = graph.get(type.getTypeName());
				m.setInDegree(m.getInDegree() + 1);
			}
		}

		Method[] methods = cls.getDeclaredMethods(); //Get the set of methods
		Class[] methodParams;

		for(Method m : methods){

			Class methodReturnType = m.getReturnType();
			//System.out.println(methodReturnType.getName());
			if(graph.containsKey(methodReturnType.getName())){
				outdegree++;
				Metric mc = graph.get(methodReturnType.getName());
				mc.setInDegree(mc.getInDegree() + 1);
			}

			methodParams = m.getParameterTypes(); //Get method parameters
			for(Class mp : methodParams){
				//System.out.println(mp.getName());
				if(graph.containsKey(mp.getName())){
					outdegree++;
					Metric bm = graph.get(mp.getName());
					bm.setInDegree(bm.getInDegree() + 1);
				}
			} 
		}
		System.out.println();
		System.out.println("Class: " + cls.getName());
		graph.get(cls.getName()).setOutDegree(outdegree);
		System.out.println("Indegree: " + graph.get(cls.getName()).getInDegree());		
		System.out.println("Outdegree: " + graph.get(cls.getName()).getOutDegree());		
		System.out.println("Stability: " + graph.get(cls.getName()).getStability());
	}
}