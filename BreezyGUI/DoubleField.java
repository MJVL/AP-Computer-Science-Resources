//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved

 package BreezyGUI;
 import java.awt.*;
 import BreezyGUI.Format;

 public class DoubleField extends TextField {
    private int precision;
    private double number;

    private void displayNumber(){
       if (precision == -1)
          setText ("" + number);
       else
          setText (Format.justify ('l', number, 0, precision));
    }

    public DoubleField (double num){
       number = num;
       precision = -1;
       displayNumber();
    }

    public void setPrecision (int prec){
       if (prec > 10) precision = 10;
       else if (prec < 0) precision = -1;
       else precision = prec;
       displayNumber();
    }

    public int getPrecision(){
       return precision;
    }

    public void setNumber (double num){
       number = num;
       displayNumber();
    }

    public boolean isValid(){
       try{
          double num = new Double (getText().trim()).doubleValue();
          return true;
       }catch(NumberFormatException e){
          return false;
       }
    }

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

