//Copyright Martin Osborne and Ken Lambert 1998-2001
//All rights reserved

package BreezySwing;

/**
 * The class Format contains methods to format data that are left-justified,
 * right-justified, or centered within a given number of columns.
 */
 public class Format {

/**
 * Returns a string that is formatted according to the justification type and the
 * specified width.  If the width is less than the length of the string, returns
 * a string of stars (*) whose length equals the width.
 * @param    leftRight the type of justification ('l', 'c', or 'r').
 * @param    str the string to be formatted.
 * @param    width the number of columns in which the string is placed.
 * Examples:
 * <pre>
 *
 *   String right = Format.justify('l', "Hi", 4);
 *   String left = Format.justify('c', "Hi", 4);
 *   String center = Format.justify('r', "Hi", 4);
 *   String noChange = Format.justify('r', "Hi", 2);
 *   String tooFew = Format.justify('r', "Hi", 1);
 *
 *   left now refers to      "Hi  "
 *   center now refers to    " Hi "
 *   right now refers to     "  Hi"
 *   noChange now refers to  "Hi"
 *   tooFew now refers to    "**"
 * </pre>
 */
    private static String padStringToWidth (char leftRight, String str, int width){
      int strLength, i;
      // If str is too long replace by *s
      // Else jusfify in a field of specified width by padding with blanks
      if (width <= 0) return str;
      strLength = str.length();
      if (strLength > width){
         str = "";
         for (i = 1; i <= width; i++) str = str + "*";
      }
      else if (leftRight == 'r' || leftRight == 'R')
         for (i = 1; i <= width - strLength; i++) str = " " + str;
      else if (leftRight == 'l' || leftRight == 'L')
         for (i = 1; i <= width - strLength; i++) str = str + " ";
      else{
         for (i = 1; i <= (width - strLength)/2; i++) str = " " + str;
         for (i = 1; i <= (width - strLength)/2; i++) str = str + " ";
      }
      return str;
   }


    public static String justify (char leftRight, String str, int width){
   return padStringToWidth (leftRight, str, width);
   }


/**
 * Converts a character to a string and returns it formatted formatted according to the justification type and the
 * specified width.
 * @param    leftRight the type of justification ('l', 'c', or 'r').
 * @param    ch the character to be formatted.
 * @param    width the number of columns in which the character is placed.
 */
   public static String justify (char leftRight, char ch, int width){
   return padStringToWidth (leftRight, "" + ch, width);
   }

/**
 * Converts a long to a string and returns it formatted formatted according to the justification type and the
 * specified width.
 * @param    leftRight the type of justification ('l', 'c', or 'r').
 * @param    x the long integer to be formatted.
 * @param    width the number of columns in which the integer is placed.
 */
    public static String justify (char leftRight, long x, int width){
   return padStringToWidth (leftRight, "" + x, width);
   }

/**
 * Converts a double to a string and returns it formatted according to the justification type and the
 * specified width and precision. The decimal point occupies a column in the formatted number.
 * @param    leftRight the type of justification ('l', 'c', or 'r').
 * @param    x the number to be formatted.
 * @param    width the number of columns in which the number is placed.
 * @param    precision the number of places of precision retained in the formatted number.
 * Examples:
 * <pre>
 *   String fourPlaces = Format.justify('r', 3.1416, 7, 4);
 *   String threePlaces = Format.justify('r', 3.1416, 7, 3);
 *   String twoPlaces = Format.justify('r', 3.1416, 7, 2);
 *   String noPlaces = Format.justify('r', 3.1416, 7, 0);
 *
 *   fourPlaces now refers to  " 3.1416"
 *   threePlaces now refers to "  3.142"
 *   twoPlaces now refers to   "   3.14"
 *   noPlaces now refers to    "      3"
 * </pre>

 */
    public static String justify (char leftRight, double x, int width, int precision){
      int exp, i,posdp;
      double rounder;
      String str;
      String sign = "";

      // Test with x = -123 precision = 0 should get -123
      if (x < 0) {
         x = -x;
         sign = "-";
      }

      // Test with x = 0 precision = 0 should get 0
      // Test with x = 123 precision = -3 should get 123
      // Test with x = 123.456 precision = 2 should get 123.46
      // Test with x = 123.5 precision 0 should get 124
      if (precision < 0) precision = 0;
      // x = x + .00005 where the number of 0s matches the precision
      rounder = 1.0;
      for (i = 1; i <= precision; i++) rounder = rounder/10.0;
      x = x + 0.5 * rounder;

      // Assume x = d.dddddddddddddd * 10**exp   (15 significant digits)
      // 1. calculate exp
      // 2. set x = d.dddddddddddddd
      // 3. set x = d.dddddddddddddd * 10**14
      //          = ddddddddddddddd
      // 4. str = "ddddddddddddddd"
      exp = 0;                                     //1 and 2
      while (x >= 10) {x = x/10; exp += 1;};
      while (x < 1) {x = x*10; exp -= 1;};
      for (i = 1; i <= 14; i++) x = x*10.0;        //3
      str = "" + (long)x;                          //4

      // Must now put a decimal point back in at the appropriate place
      // Test with x = 1.23456789012345e14 precision = 0 should get 123456789012345
      // Test with x = 1.23456789012345e16 precision = 0 should get 12345678901234500
      // Test with x = 1.23456789012345e13 precision = 0 should get 12345678901235
      // Test with x = 1.2346e0 precision = 3 should get 1.235
      // Test with x = 1.235e-3 precision = 5 should get .00124

      if (exp >= 14){
        // str = "ddddddddddddddd." or str = "ddddddddddddddd" + "00000."
        // where necessary number of 0s appended
        for (i = 1; i <= exp-14; i++) str = str + "0";
        str = str + ".";
      }else if (exp >= 0){
         // str = "d.dddddddddddddd"  when exp = 0 or
         // str = "dddddddd.ddddddd"  when exp = 1,2,3 etc or
         // str = "dddddddddddddd.d"  when exp = 13
         // where decimal point inserted in proper place
         str = sign + str.substring(0, exp+1) + "." +
               str.substring (exp+1, str.length());
      }else if (exp < 0){
         // str = ".00000ddddddddddddddd" where necessary number of 0s prepended
         for (i = 1; i <= -exp - 1; i++) str = "0" + str;
         str = sign + "0." + str;
      }

      // Use the precision to trim  extra digits from the right end
      // Test with
      //   x = 123.789 precision = 0  should get 124
      //   x = 123.789 precision = 1  should get 123.8
      //   x = 123.789 precision = 5  should get 123.78900
      posdp = str.indexOf('.');
      if (precision == 0)
         str = str.substring(0,posdp);
      else{
         // Add a bunch of 0s to right end so that substring will not go out of bounds
         for (i = 1; i <= precision; i++) str = str + "0";
         str = str.substring(0, posdp+1) + str.substring(posdp+1, posdp + 1 + precision);
      }

      return padStringToWidth (leftRight, str, width);
   }

   public static void testJustify(){
      System.out.println (justify('r',"abc", 5) + ":::  abc");
      System.out.println (justify('l',"abc", 5) + ":::abc  :::");
      System.out.println (justify('l',"abc", 0) + ":::abc:::");
      System.out.println (justify('r',"abc", 2) + ":::**");
      System.out.println ("");

      System.out.println (justify('r','a', 2) + "::: a");
      System.out.println (justify('l','a', 2) + ":::a :::");
      System.out.println (justify('r','a', -1) + ":::a");
      System.out.println ("");

      System.out.println (justify('r',123,0) +  ":::123");
      System.out.println (justify('r',123,4) +  "::: 123");
      System.out.println (justify('r',123,2) +  ":::**");
      System.out.println (justify('l',123,0) +  ":::123");
      System.out.println (justify('l',123,4) +  ":::123 :::");
      System.out.println (justify('l',123,2) +  ":::**");
      System.out.println ("");

      System.out.println (0 + ":::" + justify('r',0,0,0) + ":::0");
      System.out.println (0 + ":::" + justify('r',0,1,0) + ":::0");
      System.out.println (0 + ":::" + justify('r',0,4,2) + ":::0.00");
      System.out.println (-123 + ":::" + justify('r',-123,0,0) + ":::-123");
      System.out.println (123 + ":::" + justify('r',123,0,-3) + ":::123");
      System.out.println (123.456 + ":::" + justify('r',123.456,0,02) + ":::123.46");
      System.out.println (123.5 + ":::" + justify('r',123.5,0,0) + ":::124");
      System.out.println (1.23456789012345e14 + ":::" + justify('r',1.23456789012345e14,0,0) + ":::123456789012345");
      System.out.println (1.23456789012345e16 + ":::" + justify('r',1.23456789012345e16,0,0) + ":::12345678901234500");
      System.out.println (1.23456789012345e13 + ":::" + justify('r',1.23456789012345e13,0,0) + ":::12345678901235");
      System.out.println (1.2346e0 + ":::" + justify('r',1.2346e0,0,3) + ":::1.235");
      System.out.println (1.235e-3 + ":::" + justify('r',1.235e-3,0,5) + ":::0.00124");
      System.out.println (123.789 + ":::" + justify('r',123.789,0,0) + ":::124");
      System.out.println (123.789 + ":::" + justify('r',123.789,0,1) + ":::123.8");
      System.out.println (.789 + ":::" + justify('r',.789,0,5) + ":::0.78900");
      System.out.println (.789 + ":::" + justify('r',.789,7,5) + ":::0.78900");
      System.out.println (.789 + ":::" + justify('r',.789,8,5) + "::: 0.78900");
      System.out.println (.789 + ":::" + justify('r',.789,5,5) + ":::*****");
      System.out.println (-123 + ":::" + justify('r',-123,0,0) + ":::-123");
      System.out.println (-123 + ":::" + justify('r',-123,4,0) + ":::-123");
      System.out.println (-123 + ":::" + justify('r',-123,5,0) + "::: -123");
      System.out.println (-123 + ":::" + justify('r',-123,6,0) + ":::  -123");
      System.out.println (-123 + ":::" + justify('r',-123,3,0) + ":::***");
      System.out.println (-123 + ":::" + justify('l',-123,5,0) + ":::-123 :::");
      System.out.println (-123 + ":::" + justify('l',-123,6,0) + ":::-123  :::");
   }
}


