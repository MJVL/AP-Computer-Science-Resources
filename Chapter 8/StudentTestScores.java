import BreezySwing.*;

public class StudentTestScores extends GBFrame{

   // Declare window objects --------------------------------------

   private JButton addButton, modifyButton, firstButton,
                   previousButton, nextButton, lastButton;

   private JLabel blankLine1, nameLabel, test1Label, test2Label, test3Label,
                  averageLabel, blankLine2, countLabel, indexLabel;

   private JTextField nameField;

   private IntegerField test1Field, test2Field, test3Field,
                        averageField, countField, indexField;

   // Other instance variables ---------------------------------

   private Student[] students;         // Array of students
   private int indexSelectedStudent;   // Position of current student
   private int studentCount;           // Current number of students

   // Constructor-----------------------------------------------

   public StudentTestScores(){

      // Initialize the data
      indexSelectedStudent = -1;
      studentCount = 0;
      students = new Student[10];

      // Instantiate window objects
      addButton    = addButton ("Add"   ,2,4,1,1);
      modifyButton = addButton ("Modify",3,4,1,1);

      blankLine1     = addLabel  (""  , 6,1,1,1);
      firstButton    = addButton ("<<", 7,1,1,1);
      previousButton = addButton ("<",  7,2,1,1);
      nextButton     = addButton (">",  7,3,1,1);
      lastButton     = addButton (">>", 7,4,1,1);

      nameLabel     = addLabel ("Name"     ,1,1,1,1);
      test1Label    = addLabel ("Test 1"   ,2,1,1,1);
      test2Label    = addLabel ("Test 2"   ,3,1,1,1);
      test3Label    = addLabel ("Test 3"   ,4,1,1,1);
      averageLabel  = addLabel ("Average"  ,5,1,1,1);
      nameField  = addTextField      ("",1,2,2,1);
      test1Field = addIntegerField   (0 ,2,2,1,1);
      test2Field = addIntegerField   (0 ,3,2,1,1);
      test3Field = addIntegerField   (0 ,4,2,1,1);
      averageField = addIntegerField (0 ,5,2,1,1);
      blankLine2   = addLabel        (""              ,8,1,1,1);
      countLabel   = addLabel        ("Count"         ,9,1,1,1);
      countField   = addIntegerField (0               ,9,2,1,1);
      indexLabel   = addLabel        ("Current Index" ,9,3,1,1);
      indexField   = addIntegerField (-1              ,9,4,1,1);

      // These fields are read-only
      averageField.setEditable (false);
      countField.setEditable (false);
      indexField.setEditable (false);

      displayCurrentStudent();
   }

   // buttonClicked method--------------------------------------

   public void buttonClicked (JButton buttonObj){
      if      (buttonObj == addButton)      add();
      else if (buttonObj == modifyButton)   modify();
      else if (buttonObj == firstButton)    displayFirst();
      else if (buttonObj == previousButton) displayPrevious();
      else if (buttonObj == nextButton)     displayNext();
      else if (buttonObj == lastButton)     displayLast();
   }

   // Private methods-------------------------------------------

   private void add(){
      if (studentCount == students.length){
         messageBox ("SORRY: student array is full");
         return;
      }

      Student stu = getDataOnScreen();
      String str = stu.validateData();

      if (str != null){       // If the data is invalid,
         messageBox (str);    // then exit the method without
         return;              // adding the student
      }

      students[studentCount] = stu;
      indexSelectedStudent = studentCount;
      studentCount++;

      displayCurrentStudent();
   }

   private Student getDataOnScreen(){
      String nm = nameField.getText().trim();

      int[] tests = new int[3];
      tests[0] = test1Field.getNumber();
      tests[1] = test2Field.getNumber();
      tests[2] = test3Field.getNumber();

      Student stu = new Student (nm, tests);
      return stu;
   }

   private void modify(){
     // Completion of this method is left as an exercise
   }

   private void displayFirst(){
      if (studentCount == 0)
         indexSelectedStudent = -1;
      else
         indexSelectedStudent = 0;
      displayCurrentStudent();
   }

    private void displayPrevious(){
     // Completion of this method is left as an exercise
   }

   private void displayNext(){
      if (studentCount == 0)
         indexSelectedStudent = -1;
      else
         indexSelectedStudent
             = Math.min (studentCount - 1, indexSelectedStudent + 1);
      displayCurrentStudent();
   }

   private void displayLast(){
     // Completion of this method is left as an exercise
   }

   private void displayCurrentStudent(){
      if (indexSelectedStudent == -1){
         nameField.setText ("");
         test1Field.setNumber (0);
         test2Field.setNumber (0);
         test3Field.setNumber (0);
         averageField.setNumber (0);
      }else{
         Student stu = students[indexSelectedStudent];
         nameField.setText (stu.getName());
         test1Field.setNumber (stu.getScore(1));
         test2Field.setNumber (stu.getScore(2));
         test3Field.setNumber (stu.getScore(3));
         averageField.setNumber (stu.getAverage());
      }
      countField.setNumber (studentCount);
      indexField.setNumber (indexSelectedStudent);
   }

   // main------------------------------------------------------

   public static void main (String[] args){
      StudentTestScores theGUI = new StudentTestScores();
      theGUI.setSize (400, 250);
      theGUI.setVisible(true);
   }
}

