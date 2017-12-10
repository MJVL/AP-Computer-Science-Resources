/* Now you will be able to perform actions when a button is clicked 
to get and place text in/out a textfield and to get the state of checkboxes. 
This example will only let the button do actions. 
*/ 
import java.awt.*; 
import java.applet.*; 
// import an extra class for the ActionListener 
import java.awt.event.*; 
//import java.util.*;

// Tells the applet you will be using the ActionListener methods. 

public class TwoPointApplet extends Applet implements ActionListener, MouseListener
{ 

     Button graphButton; 
     Button wrongButton; 
     TextField x1Field;
	 TextField y1Field;
	 TextField x2Field;
	 TextField y2Field;
     CheckboxGroup radioGroup; 
     Checkbox radio1; 
     Checkbox radio2; 
     Checkbox radio3; 

     public void init()  
     { 
  // Now we will use the FlowLayout 
          setLayout(null); 
          graphButton = new Button("Graph!"); 
          wrongButton = new Button("Don't click!"); 
          x1Field = new TextField("Type x1 here",20); 
		  y1Field = new TextField("Type y1 here",20);
		  x2Field = new TextField("Type x2 here",20);
		  y2Field = new TextField("Type y2 here",20);
          radioGroup = new CheckboxGroup(); 
          radio1 = new Checkbox("Red", radioGroup,false); 
          radio2 = new Checkbox("Blue", radioGroup,true); 
          radio3 = new Checkbox("Green", radioGroup,false); 
          add(graphButton); 
          add(wrongButton); 
          add(x1Field);
		  add(y1Field);
		  add(x2Field);
		  add(y2Field);
          add(radio1); 
          add(radio2); 
          add(radio3); 
		  graphButton.setBounds(150,500,100,30); 
		  wrongButton.setBounds(350,500,100,30);
          x1Field.setBounds(25,10,100,40); 
		  y1Field.setBounds(175,10,100,40); 
		  x2Field.setBounds(325,10,100,40); 
		  y2Field.setBounds(475,10,100,40); 
          radio1.setBounds(125,100,100,30); 
          radio2.setBounds(275,100,100,30); 
          radio3.setBounds(425,100,100,30); 

  


  // Attach actions to the components 
          graphButton.addActionListener(this); 
          wrongButton.addActionListener(this); 
		  
		  

  // Add the MouseListener to your applet 
		  addMouseListener(this);

         } 

 // Here we will show the results of our actions 
         public void paint(Graphics g) 
         { 
			 g.setColor(Color.black);
			g.drawLine(150,300,450,300);
			g.drawLine(300,150,300,450);
  // If the radio1 box is selected then radio1.getState() will 
  // return true and this will execute 
          if (radio1.getState()) g.setColor(Color.red); 
  // If it was not red we'll try if it is blue 
        else if (radio2.getState()) g.setColor(Color.blue); 
  // Since always one radiobutton must be selected it must be green 
          else g.setColor(Color.green); 

  // Now that the color is set you can get the text out the TextField 
  // like this 
		  int x1,y1,x2,y2,xc1,yc1,xc2,yc2;
		  String p1,p2;
		  
		  x1=Integer.parseInt(x1Field.getText());
		  y1=Integer.parseInt(y1Field.getText());
		  xc1=300+x1;
		  yc1=300+(-1)*y1;
		  x2=Integer.parseInt(x2Field.getText());
		  y2=Integer.parseInt(y2Field.getText());
		  xc2=300+x2;
		  yc2=300+(-1)*y2;
		  
		  p1="("+x1+","+y1+")";
          g.drawString(p1,xc1+5,yc1+5);
		  p2="("+x2+","+y2+")";
          g.drawString(p2,xc2+5,yc2+5); 
		  g.drawOval(xc1-2,yc1-2,4,4);
		  g.drawOval(xc2-2,yc2-2,4,4);
		  g.drawLine(xc1,yc1,xc2,yc2);
     } 

 // When the button is clicked this method will get automatically called 
 // This is where you specify all actions. 

        public void actionPerformed(ActionEvent evt)  
         { 
  // Here we will ask what component called this method 
              if (evt.getSource() == graphButton)  
   // So it was the okButton, then let's perform his actions 
   // Let the applet perform Paint again. 
   // That will cause the aplet to get the text out of the textField 
   // again and show it. 
                   repaint(); 

  // Actions of the wrongButton 
          else if (evt.getSource() == wrongButton)  
          { 

   // Change the text on the button for fun 
               wrongButton.setLabel("TooBig"); 
   // Changes the text in the TextField 
               x1Field.setText("That was the wrong button!"); 
   // Lets the applet show that message. 
               repaint(); 
          } 
     }
		public void mouseClicked (MouseEvent me) { 

  // Save the coordinates of the click lke this. 
  xpos = me.getX(); 
  ypos = me.getY(); 
		}


} 
  
