public class Tester{
   public static void main(String[] args){

      // Create an initial array with 3 strings.
      Object[] array = {"hi", "there", "Mary"};          
      int logicalSize = 3;
      boolean successful = false;

      // Insert strings at positions 0 and 1.
      successful = insertItem(array, logicalSize, 0, "Jack");
      if (successful)
         logicalSize++;  
      successful = insertItem(array, logicalSize, 1, "says");
      if (successful)
         logicalSize++;  

      // Display new physical size and contents.
      System.out.println(array.length);                  
      for (int i = 0; i < logicalSize; i++) 
         System.out.print(array[i] + " ");
   }

   // Definitions of array methods go here
   static public boolean insertItem(Object[] array, int logicalSize,
                                    int targetIndex, Object newItem){
      // Check for a full array and return false if full
      if (logicalSize == array.length)
         return false;

      // Check for valid target index return false if not valid
      if (targetIndex < 0 || targetIndex > logicalSize)
         return false;

      // Shift items down by one position
      for (int i = logicalSize; i > targetIndex; i--)
         array[i] = array[i - 1];

      // Add new item, increment logical size, and return true
      array[targetIndex] = newItem;
      return true;
   }

   static public boolean removeItem(Object[] a, int logicalSize, 
                                    int targetIndex){
      // Exercise
      return false;
   }

}

