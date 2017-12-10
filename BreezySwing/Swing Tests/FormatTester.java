import javax.swing.*;
import BreezySwing.*;

public class FormatTester{

   public static void main (String[] args){

      int spaces = 4, precision = 2;
      String s = "Four";

      double d = 33.145;
      int i = 345;

      System.out.println("L: B" + Format.justify('l', s, spaces) + "E");
      System.out.println("R: B" + Format.justify('r', s, spaces) + "E");
      System.out.println("C: B" + Format.justify('c', s, spaces) + "E");
      System.out.println("L: B" + Format.justify('l', d, spaces, precision) + "E");
      System.out.println("R: B" + Format.justify('r', d, spaces, precision) + "E");
      System.out.println("C: B" + Format.justify('c', d, spaces, precision) + "E");
      System.out.println("L: B" + Format.justify('l', i, spaces) + "E");
      System.out.println("R: B" + Format.justify('r', i, spaces) + "E");
      System.out.println("C: B" + Format.justify('c', i, spaces) + "E");

   }
}
