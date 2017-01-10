package ie.gmit.sw;

/**
 * 
 * @author Christopher Weir - G00309429
 * This Class is used for calculating the stability metric
 */
public class Metric {

	private int inDegree;
	private int outDegree;
	private String className;

    /**
     * Gets the name of the Class
     * @return
     * Returns the name of the Class as a String
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the name of the Class.
     *
     * @param className
     * The name of the Class as a String.
     */
    public void setClassName(String className) {

        this.className = className;
    }

    /**
     * Gets the in degree for the Class.
     *
     * @return
     * Returns the the in degree as an int.
     */
    public int getInDegree() {

        return inDegree;
    }

    /**
     * Sets the in degree for the Class.
     *
     * @param inDegree
     * The in degree of the Class.
     */
    public void setInDegree(int inDegree) {

        this.inDegree = inDegree;
    }

    /**
     * Gets the out degree for the Class.
     *
     * @return
     * Returns the out degree as an int.
     */
    public int getOutDegree() {

        return outDegree;
    }

    /**
     * Sets the out degree for the Class.
     *
     * @param outDegree
     * The out degree of the class
     */
    public void setOutDegree(int outDegree) {

        this.outDegree = outDegree;
    }

    /**
     * Uses the inDegree and outDegree to calculate the stability.
     *
     * @return
     * Returns the positional stability as a float
     */
	public float getStability(){
		float stability = 1f;
		
		if(getOutDegree() > 0)
		{
			stability = ((float)getOutDegree() /( (float)getInDegree() + (float)getOutDegree()));
		}
		else{
			stability = 0f;
		}
		return (float) (Math.round(stability * 100d)/100d);
	}	
}