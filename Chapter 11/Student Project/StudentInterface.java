import TerminalIO.KeyboardReader;
import com.sun.java.util.collections.*;

public class StudentInterface{

   public static void main (String[] args){
      // Instantiate the student array and the keyboard object
	   Student[] ArrStu=new Student[3];
	   int[] arravg = new int[3];
	   ArrayList stualist= new ArrayList();
      
      KeyboardReader reader = new KeyboardReader();

      String name;
      int score;

      // Input the  student's data
	   for(int j=0;j<=2;j++)
	   {
		   name = reader.readLine("Enter the  student's name: ");
		   ArrStu[j]= new Student();
		   ArrStu[j].setName(name);
		   for (int i = 1; i <= 3; i++)
		   {
			   score = reader.readInt("Enter the student's score: ");
			   ArrStu[j].setScore(i, score);
		   }


		   arravg[j]=ArrStu[j].getAverage();
		   stualist.add(ArrStu[j]);
	   }

      

      // Output the  students' information
	   for(int i=0;i<=2;i++)
	   {
		   System.out.println(ArrStu[i]);
		  
	   }
	   Arrays.sort(arravg);
	   for(int i=0;i<=2;i++)
	   {
		   System.out.println(arravg[i]);
		  
	   }

		exchangeSortAvg(stualist);

	   for (int i = 0; i < stualist.size(); i++)
	   {
		   
		   System.out.println(stualist.get(i).toString());
	   }

	
	  reader.pause();
   }
	static public void exchangeSortAvg(ArrayList a)
	{
		int alfavg,albavg;
		Student bob = new Student();
		Student fred = new Student();
		for (int f = 0; f < a.size()-1; f++)
		{
			for (int b = f+1; b < a.size(); b++)
			{
				fred=(Student)a.get(f);
				bob=(Student)a.get(b);
				alfavg=fred.getAverage();
				albavg=bob.getAverage();
				System.out.println(alfavg+"-"+albavg);
				if(alfavg>albavg)
				{
					Student temp= new Student();
					temp=(Student)a.get(f);
					a.set(f,a.get(b));
					a.set(b,temp);
				}
			}
		}
	}
}

