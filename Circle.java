
public class Circle {
	//data
	private double myradius;
	//constructors
	public Circle()
	{
		myradius=0.0;
		
	}
	//methods
	public void setRadius(double r)
	{
		myradius=r;
	}
	public double getRadius()
	{
		return myradius;
	}
	public double findArea()
	{
		return Math.PI*Math.pow(myradius,2);
	}
	
	public String toString()
	{
		String output;
		output="Radius: "+myradius+"\n"+"Area: "+findArea();
		return output;
	}

}
