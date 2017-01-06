package ie.gmit.sw;

import java.util.List;

public class TestClass {

	Metric m;
	String test = "testMethod";
	
	public TestClass() {
		Test();
		Method(test);
	}

	public String Test(){
		Reflection r = new Reflection();
		return r.test;
		
	}
	
	public String Method(String test){
		return test;
	}
}