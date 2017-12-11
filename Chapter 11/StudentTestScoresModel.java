public class StudentTestScoresModel{

   private Student[] students;       // The list of students
   private int studentCount;         // The logical size
   private Student currentStudent;   // The currently selected student

   public StudentTestScoresModel(){
      students = new Student[15];
      studentCount = 0;
      currentStudent = null;
   }

   public Student getCurrentStudent(){
      return currentStudent;
   }

   public String[] getNames(){
      String[] names = new String[studentCount];
      for (int i = 0; i < studentCount; i++)
         names[i] = students[i].getName();
      return names;
   }

   public void setCurrentStudent(String name){
      currentStudent = findStudent(name);
   }

   public String add(Student newStudent){
      String message = newStudent.validateData();
      if (message != null)
         return message;
      if (studentCount == students.length)
         return "SORRY: student array is full";
      Student stu = findStudent(newStudent.getName());
      if (stu != null)
         return "SORRY: that student's name is already in use";
      students[studentCount] = newStudent;
      currentStudent = newStudent;
      studentCount++;
      return null;
   }

   public void delete(){
      // Exercise
   }

   private Student findStudent(String name){
      for (int i = 0; i < studentCount; i++)
         if (name.equals(students[i].getName()))
            return students[i];
      return null;
   }

}
