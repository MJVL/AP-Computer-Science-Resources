//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

 package BreezySwing;
 import javax.swing.*;
 import BreezySwing.Format;

 /**
 * A DoubleField is a component that allows the editing of a double (floating-point number),
 * and allows the user to avoid messy conversions to and from strings.
 */
 public class DoubleField extends JTextField {
    private int precision;
    private double number;

    private void displayNumber(){
       if (precision == -1)
          setText ("" + number);
       else
          setText (Format.justify ('l', number, 0, precision));
    }

/**
 * Creates a DoubleField containing the specified number.
 */
    public DoubleField (double num){
       number = num;
       precision = -1;
       displayNumber();
    }

/**
 * Sets the precision of the number displayed in the DoubleField to the specified value.
 * @param    prec the number of figures of precision (>= 0 and <= 10).
 */
     public void setPrecision (int prec){
       if (prec > 10) precision = 10;
       else if (prec < 0) precision = -1;
       else precision = prec;
       displayNumber();
    }

/**
 * Returns the precision of the number displayed in the DoubleField.
 */
     public int getPrecision(){
       return precision;
    }

/**
 * Sets the number displayed in the DoubleField to the specified value.
 */

    public void setNumber (double num){
       number = num;
       displayNumber();
    }

/**
 * Returns true if the data in the DoubleField represent a floating-point number,
 * or false otherwise.
 */
     public boolean isValid(){
       try{
          double num = new Double (getText().trim()).doubleValue();
          return true;
       }catch(NumberFormatException e){
          return false;
       }
    }

/**
 * Returns the number in the DoubleField, or 0 if the data in the field do not
 * represent a valid floating-point number.
 */
     public double getNumber(){
       try{
          number = new Double (getText().trim()).doubleValue();
       }catch(NumberFormatException e){
          number = 0;
          displayNumber();
       }
       return number;
    }
 }

