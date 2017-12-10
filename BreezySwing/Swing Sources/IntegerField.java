//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package BreezySwing;
import javax.swing.*;

/**
 * An IntegerField is a component that allows the editing of an integer (whole number),
 * and allows the user to avoid messy conversions to and from strings.
 */
  public class IntegerField extends JTextField {


/**
 * Creates an IntegerField containing the specified number.
 */
     public IntegerField (int num){
       setText ("" + num);
    }

/**
 * Sets the number displayed in the IntegerField to the specified value.
 */
     public void setNumber (int num){
       setText ("" + num);
    }

/**
 * Returns true if the data in the IntegerField represent an integer,
 * or false otherwise.
 */
     public boolean isValid(){
       try{
          int num = new Integer (getText().trim()).intValue();
          return true;
       }catch(NumberFormatException e){
          return false;
       }
    }

/**
 * Returns the number in the IntegerField, or 0 if the data in the field do not
 * represent a valid integer.
 */
     public int getNumber(){
       int num;
       try{
          num = new Integer (getText().trim()).intValue();
       }catch(NumberFormatException e){
          num = 0;
          setText ("" + num);
       }
       return num;
    }
 }

