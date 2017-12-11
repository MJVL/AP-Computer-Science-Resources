import TerminalIO.KeyboardReader;

public class StudentInterface{

   public static void main (String[] args){
      // Instantiate the students and the keyboard object
      Student student1 = new Student();
      Student student2 = new Student();
      KeyboardReader reader = new KeyboardReader();

      String name;
      int score;

      // Input the first student's data
      name = reader.readLine("Enter the first student's name: ");
      student1.setName(name);
      for (int i = 1; i <= 3; i++){
         score = reader.readInt("Enter the student's score: ");
         student1.setScore(i, score);
      }

      // Input the second student's data
      name = reader.readLine("Enter the second student's name: ");
      student2.setName(name);
      for (int i = 1; i <= 3; i++){
         score = reader.readInt("Enter the student's score: ");
         student2.setScore(i, score);
      }

      // Output the two students' information
      System.out.println(student1);
      System.out.println(student2);

      // Output the student with the highest score
      if (student1.getHighScore() > student2.getHighScore()){
         name = student1.getName();
         score = student1.getHighScore();
      }else{
         name = student2.getName();
         score = student2.getHighScore();
      }
      System.out.println(name + " has the highest score: " + score);
      
      // Output the student with the highest average score
      if (student1.getAverage() > student2.getAverage()){
         name = student1.getName();
         score = student1.getAverage();
      }else{
         name = student2.getName();
         score = student2.getAverage();
      }
      System.out.println(name + " has the highest average score: " + score);
	  reader.pause();
   }
}

