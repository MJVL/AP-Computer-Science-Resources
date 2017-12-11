public class TestSortAlgorithms{

   public static void main(String[] args){

      int[] a = new int[20];

      //Initialize the array to random numbers between 0 and 99
      for (int i = 0; i < a.length; i++)
         a[i] = (int)(Math.random() * 100);

      printArray(a);
      selectionSort(a);
      //bubbleSort(a);
      //insertionSort(a);
      printArray(a);
   }

   public static void selectionSort(int[] a){
      for (int i = 0; i < a.length - 1; i++){
         int minIndex = findMinimum(a, i);
         if (minIndex != i)
            swap(a, i, minIndex);
      }
   }

   public static int findMinimum(int[] a, int first){
      int minIndex = first;

      for (int i = first + 1; i < a.length; i++)
         if (a[i] < a[minIndex])
            minIndex = i;

      return minIndex;
   }

   public static void swap(int[] a, int x, int y){
      int temp = a[x];
      a[x] = a[y];
      a[y] = temp;
   }  

   public static void bubbleSort(int[] a){
      int k = 0;
      boolean exchangeMade = true;

      // Make up to n - 1 passes through array, exit early if no exchanges
      // are made on previous pass

      while ((k < a.length - 1) && exchangeMade){
         exchangeMade = false;
         k++;
         for (int j = 0; j < a.length - k; j++)
            if (a[j] > a[j + 1]){
               swap(a, j, j + 1);
               exchangeMade = true;
            }
	     }
   }

   public static void insertionSort(int[] a){
      int itemToInsert, j;
      boolean stillLooking;

      // On the kth pass, insert item k into its correct position among
      // the first k entries in array.

      for (int k = 1; k < a.length; k++){
	      // Walk backwards through list, looking for slot to insert a[k]
         itemToInsert = a[k];
         j = k - 1;
         stillLooking = true;
         while ((j >= 0) && stillLooking )
            if (itemToInsert  < a[j]) {
               a[j + 1] = a[j];
               j--;
            }else
               stillLooking = false;
            // Upon leaving loop, j + 1 is the index
            // where itemToInsert  belongs
            a[j + 1] = itemToInsert;
      }
   }

   static public void printArray(int[] a){
      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + " ");
      System.out.println("");
   }

}


