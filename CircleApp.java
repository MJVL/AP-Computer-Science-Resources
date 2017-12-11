import java.util.Scanner;
public class CircleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner bob= new Scanner(System.in);
		Circle c=new Circle();
		
		double r;
		
		System.out.println("Enter your radius: ");
		r=bob.nextDouble();
		
		c.setRadius(r);
		
		System.out.println(c.toString());
		
		
	}

}
