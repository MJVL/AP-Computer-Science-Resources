//BreezyGUI Copyright Martin Osborne and Ken Lambert 1998-2000
//All rights reserved

 package BreezyGUI;
 import java.awt.*;

 public class IntegerField extends TextField {
    public IntegerField (int num){
       setText ("" + num);
    }

    public void setNumber (int num){
       setText ("" + num);
    }

    public boolean isValid(){
       try{
          int num = new Integer (getText().trim()).intValue();
          return true;
       }catch(NumberFormatException e){
          return false;
       }
    }

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

