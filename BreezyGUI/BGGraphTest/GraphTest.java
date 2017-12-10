import java.awt.*;
import BreezyGUI.*;
 
public class GraphTest extends GBFrame{
 
   Label aLabel = addLabel ("A", 1, 1, 1, 1);
   IntegerField aField = addIntegerField (0, 1, 2, 1, 1);
   Label bLabel = addLabel ("B", 1, 3, 1, 1);
   IntegerField bField = addIntegerField (0, 1, 4, 1, 1);
   Label cLabel = addLabel ("C", 1, 5, 1, 1);
   IntegerField cField = addIntegerField (0, 1, 6, 1, 1);
   Label dLabel = addLabel ("D", 1, 7, 1, 1);
   IntegerField dField = addIntegerField (0, 1, 8, 1, 1);
   Label fLabel = addLabel ("F", 1, 9, 1, 1);
   IntegerField fField = addIntegerField (0, 1, 10, 1, 1);
 
   MenuItem lineItem = addMenuItem ("Graph", "Line");
   MenuItem barItem = addMenuItem ("Graph", "Bar");
   MenuItem pieItem = addMenuItem ("Graph", "Pie");
 
   private final int NUM_GRADES = 5;
   private final int X_LEFT = 100;
   private final int X_RIGHT = 300;
   private final int Y_TOP = 100;
   private final int Y_BOTTOM = 250;
   private final int BAR_WIDTH = 10;
 
   private int totalXPixels, totalYPixels;
   private int grades[];
   private char graphType;
 
   public GraphTest(){
      int i;
      grades = new int[NUM_GRADES];
      for (i = 0; i < grades.length; i++)
         grades[i] = 0;
      totalXPixels = X_RIGHT - X_LEFT + 1;
      totalYPixels = Y_BOTTOM - Y_TOP + 1;
      graphType = 'L';
   }
 
   public void menuItemSelected (MenuItem mi){
      if (mi == lineItem)
         graphType = 'L';
      else if (mi == barItem)
         graphType = 'B';
      else if (mi == pieItem)
         graphType = 'P';
      repaint();
   }
 
   public void paint (Graphics g){
      getInputData();
      switch (graphType){
         case 'L':
            drawLineGraph(g);
            break;
         case 'B':
            drawBarGraph(g);
            break;
         case 'P':
            drawPieGraph(g);
            break;
      }
   }
 
   private void getInputData(){
      grades[0] = aField.getNumber();
      grades[1] = bField.getNumber();
      grades[2] = cField.getNumber();
      grades[3] = dField.getNumber();
      grades[4] = fField.getNumber();
   }
 
   private void drawLineGraph (Graphics g){
      int i, x1, y1, x2, y2, largestNumber, xIncrement, yIncrement;
 
      drawAxes(g);
 
      // Compute the x and y increments.
 
      largestNumber = findLargest(grades);
      xIncrement = totalXPixels / NUM_GRADES;
      if (largestNumber == 0)
         yIncrement = 0;
      else
         yIncrement = totalYPixels / largestNumber;
 
      // Set the initial end point.
 
      x1 = X_LEFT;
      y1 = Y_BOTTOM;
 
      // Compute and plot the data points.
 
      for (i = 0; i < NUM_GRADES; i++){
         x2 = getXCoordinate(i + 1, xIncrement);
         y2 = getYCoordinate(grades[i], yIncrement);
         g.fillOval(x2, y2, 5, 5);
         g.drawLine(x1, y1, x2, y2);
         x1 = x2;
         y1 = y2;
      }
   }
 
   private void drawBarGraph (Graphics g){
      int i, x, y, height, largestNumber, xIncrement, yIncrement;
 
      drawAxes(g);
 
      // Compute the x and y increments.
 
      largestNumber = findLargest (grades);
      xIncrement = totalXPixels / NUM_GRADES;
      if (largestNumber == 0)
         yIncrement = 0;
      else
         yIncrement = totalYPixels / largestNumber;
 
      for (i = 0; i < NUM_GRADES; i++){
     x = getXCoordinate (i + 1, xIncrement);
     y = getYCoordinate (grades[i], yIncrement);
         x = x - BAR_WIDTH / 2;
         height = Y_BOTTOM - y + 1;
     g.fillRect(x, y, BAR_WIDTH, height);
      }
   }
 
   private void drawPieGraph (Graphics g){
 
      int totalUnits, centerX, centerY, radius;
	  int unitAngleSize, startAngle;
 
      // Set up center point and radius of the pie, and the unit angle size.
	  int a, tgrades;
	  tgrades=0;
	  for (a=0; a<5; a++)
	  {
		  tgrades=tgrades+grades[a];
	  }
      totalUnits = tgrades;
      centerX = getSize().width / 2;
      centerY = getSize().height / 2;
      radius = centerX - centerX / 3;
      centerX = radius;
      centerY = centerY - centerY / 3;
	  unitAngleSize = 360 / totalUnits;
	  startAngle = 0;
	  System.out.println(totalUnits);
	  System.out.println(NUM_GRADES);
	  System.out.println(unitAngleSize);
	  System.out.println("Total= "+tgrades);
      // Draw the wedges in the pie.
 
      for (int i = 0; i < (NUM_GRADES); i++){
         int centralAngle = unitAngleSize * grades[i];
		 System.out.println(startAngle + " " + centralAngle);
         g.setColor(intToColor(i));
         g.fillArc(centerX, centerY, radius, radius, startAngle, centralAngle);
         startAngle = startAngle + centralAngle;

      }
 
      g.setColor(Color.black);
   }
 
   private void drawAxes(Graphics g){
      g.drawLine(X_LEFT, Y_TOP, X_LEFT, Y_BOTTOM);
      g.drawLine(X_LEFT, Y_BOTTOM, X_RIGHT, Y_BOTTOM);
   }
 
   private int getXCoordinate(int i, int xIncrement){
      return X_LEFT + xIncrement * i;
   }
 
   private int getYCoordinate(int numStudents, int yIncrement){
      return Y_BOTTOM - yIncrement * numStudents;
   }
 
   private int findLargest(int a[]){
      int i;
      int loc = 0;
      for (i = 1; i < a.length; i++)
         if (a[i] > a[loc])
            loc = i;
      return a[loc];
   }
 
   private int sum(int a[]){
      int i;
      int total= 0;
      for (i = 0; i < a.length; i++)
         total = total + a[i];
      return total;
   }
 
   private Color intToColor(int i){
      Color color = Color.black;
      switch (i){
         case 0:
            color = Color.red;
            break;
         case 1:
            color = Color.green;
            break;
         case 2:
            color = Color.blue;
            break;
         case 3:
            color = Color.yellow;
            break;
         case 4:
            color = Color.magenta;
            break;
      }
      return color;
   }
 
   public static void main (String[] args){
      Frame frm = new GraphTest();
      frm.setSize (400, 300);
      frm.setVisible (true);
   }
}


