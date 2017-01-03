package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

public class Metric {
	
	private int inDegree;
	private int outDegree;
	private Map<String, Metric> graph = new HashMap<>();
}
