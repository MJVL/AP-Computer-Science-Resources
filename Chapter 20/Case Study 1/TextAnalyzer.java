/* TextAnalyzer.java
Analyze a text file and determine the number of words, the longest word,
and the length of the longest word. Ignore numbers.
*/

import javax.swing.*;
import BreezySwing.*;
import java.io.*;

public class TextAnalyzer extends GBFrame{

    
    //Window objects
   private JLabel fileLabel;
   private JTextField fileField;
   private JButton doReport;
   private JTextArea outputArea;

   //Instance variables
   private int wordCount;          //The number of words in the text
   private String longestWord;     //The longest word in the text

   //Constructor
   public TextAnalyzer() {
      fileLabel     = addLabel ("File name",1,1,1,1);
      fileField = addTextField ("words.txt",1,2,1,1);
      doReport     = addButton ("Analyze"  ,2,1,2,1);
      outputArea = addTextArea (""         ,3,1,2,3);
      setTitle("Text Analyzer");
   }

   //Analyze the text
   public void buttonClicked (JButton buttonObj){

      //Get the file name and initialize the instance variables
      String fileName = fileField.getText();
      wordCount = 0;
      longestWord = "";

      try{
      
         //Instantiate a file stream on the text file, analyze, and
         //print the results
         FileInputStream fileStream = new FileInputStream(fileName);
         analyzeFile(fileStream);
         fileStream.close();
         printStatistics();
         
      }catch (IOException e){
      
         //Report error conditions
         messageBox("File not opened\n" + e.toString());
         
      }
   }


   //Instantiate a stream tokenizer on the file stream, process the stream
   //one token at a time, count the tokens, and identify the longest token.
   private void analyzeFile(FileInputStream fileStream){

      //Declare and initialize local variables
      int tokenType = 0;
      String word = "";
      
      //Instantiate the stream tokenizer
      InputStreamReader reader = new InputStreamReader(fileStream);
      StreamTokenizer tokens = new StreamTokenizer(reader); 
      
      //Treat periods as whitespace      
      tokens.whitespaceChars  ('.', '.');    

      try{
      
         //Read and process the tokens
         tokens.nextToken();
         while (tokens.ttype != StreamTokenizer.TT_EOF){
         
            if (tokens.ttype == StreamTokenizer.TT_WORD){
               word = tokens.sval;
               wordCount++;
               if (word.length() > longestWord.length())
                  longestWord = word;
            }
            tokens.nextToken();
            
          }
          
      }catch (IOException e){
         messageBox("Data not read properly " + e.toString());
      }
   }

   //Print the results of the analysis
   private void printStatistics(){
      outputArea.setText("");
      outputArea.append ("Word count = " + wordCount + "\n");
      outputArea.append("Longest word = " + longestWord + "\n");
      outputArea.append("Length of longest word = " 
                        + longestWord.length() + "\n");
   }

   public static void main (String[] args){
      TextAnalyzer tpo = new TextAnalyzer();
      tpo.setSize (250, 200);
      tpo.setVisible(true);
   }
}
