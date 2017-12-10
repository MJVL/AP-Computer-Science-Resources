import java.io.*;

/**

   Provides a simple interface for standard text reading operations
   from an input stream.  Converts all IOExceptions to
   RuntimeExceptions, so it should be used only with stable input
   streams like System.in.  Uses lazy evaluation (delaying the reading
   of the beginning of a new line until necessary) so as to function
   properly when used with console input.  Unlike the standard
   java.io.BufferedReader, it returns a correct value for ready() with
   console reading (returning false only if the user enters an
   end-of-file from the console).  Includes a one-character peek ahead
   facility.<p>

   TextReader's behavior is similar to that of Pascal's standard
   input.  It has three utilities for processing the input one token
   at a time (one for words and two for numbers).  As in Pascal, these
   token-processing methods skip leading whitespace, but unlike
   Pascal, they also skip trailing whitespace on the current input
   line.  Skipping the trailing space allows one to write
   token-processing programs without worrying about trailing
   whitespace at the end of a line that might imply that more input is
   present when it really isn't.<p>

   TextReader also provides utilities for reading the input one
   character at a time or one line at a time, in which case whitespace
   is preserved.  As noted above, it also has a utility for peeking
   one character ahead in the stream.  There is also a method for
   skipping whitespace.  A programmer can combine these utilities to
   define specialized behavior.<p>

   To simplify differences between operating systems, TextReader skips
   a "\r" character if it sees it so that the two-character sequence
   "\r\n" becomes a single "\n" character.  It also inserts a virtual
   "\n" before end-of-file if one is not present.<p>

   Below is an example of how to construct and manipulate a reader for
   interactive console input.

   <blockquote><pre>
   * TextReader console = new TextReader(System.in);
   * System.out.print("What is your name? ");
   * String name = console.readLine();
   * System.out.print("Give me two positive numbers, " + name + " --> ");
   * double x = console.readDouble();
   * double y = console.readDouble();
   * System.out.println(x + " to the " + y + " power = " + Math.pow(x, y));
   * System.out.print("Give me a positive integer, " + name + " --> ");
   * int n = console.readInt();
   * System.out.println("2 to the " + n + " = " + (int)Math.pow(2, n));
   </pre></blockquote>

   The code above would execute something like this (user input underlined):

   <blockquote><pre>
   * What is your name? <u>John Boy</u>
   * Give me two positive numbers, John Boy --> <u>3.4 2.9</u>
   * 3.4 to the 2.9 power = 34.77673927667935
   * Give me a positive integer, John Boy --> <u>14</u>
   * 2 to the 14 = 16384
   </pre></blockquote>

   By default the token-processing methods like readInt consume the
   newline character at the end of each line, which assumes that
   newline characters are not significant.  There is a utility that
   allows a programmer to specify that newline characters are
   significant, in which case they are left in the input stream.  For
   example, the code below reads exactly one line of input, adding up
   the integers on the line.<p>

   <blockquote><pre>
   * input.eolIsSignificant(true);
   * input.skipWhite(false);  // in case the line contains just whitespace
   * sum = 0;
   * while (input.peek() != '\n')
   *     sum += input.readInt();
   * input.readChar();  // to skip past the newline
   </pre></blockquote>

   To construct a reader using a file, the potential I/O error must be
   caught because, for example, the specified file might not exist.
   Below is an example of how to construct and manipulate a file
   reader using a specific file name (in this case, project.dat).  If
   an error occurs, the program exits with a runtime exception.

   <blockquote><pre>
   * TextReader input;
   * try {
   *     input = new TextReader(new FileInputStream("project.dat"));
   * } catch (IOException e) {
   *     throw new RuntimeException(e.toString());
   * }
   * // if you made it to here, then the file exists and is readable
   * int sum = 0;
   * while (input.ready())
   *     sum += input.readInt();
   </pre></blockquote>

   Below is a variation where the user is prompted for a file name and
   the program loops until a legal file name is given.

   <blockquote><pre>
   * TextReader console = new TextReader(System.in);
   * TextReader input;
   * boolean done = false;
   * do {
   *     System.out.print("input file name? ");
   *     String name = console.readLine();
   *     try {
   *         input = new TextReader(new FileInputStream(name));
   *         done = true;
   *     } catch (IOException e) {
   *         System.out.println(e + ", try again");
   *     }
   * } while (!done);
   * int sum = 0;
   * while (input.ready())
   *     sum += input.readInt();
   </pre></blockquote>

   The previous two code examples use FileInputStream and IOException,
   which would require you to include the line below at the beginning
   of your class file.

   <blockquote><pre>
   * import java.io.*;
   </pre></blockquote>

   Questions about this class can be sent to <a
   href="http://www.cs.arizona.edu/people/reges">Stuart Reges</a> (<a
   href="mailto:reges@cs.arizona.edu">reges@cs.arizona.edu</a>).

   @version 1.1,  1/7/01
   @author Stuart Reges */

public class TextReader
{
    /**
       Create a text-reading stream.
       @param input the input stream to read from
    */
    public TextReader(InputStream input)
    {
        myInput = input;
        myUndefinedState = true;
    }

    private int read()
    // post: reads a character from the stream, converting \r\n into \n
    {
        try {
            int result = myInput.read();
            if (result == '\r')
                result = myInput.read();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }   

    private void checkState()
    // post: used to implement lazy input; defines state if it is
    //       currently undefined (should be called as late as possible)
    {
        if (myUndefinedState) {
            myUndefinedState = false;
            myNextChar = read();
        }
    }

    /**
       Read a single character (including whitespace).
       @return next character in the stream
       @throws RuntimeException if end-of-file
    */
    public char readChar()
    {
        checkState();
        if (myNextChar == -1)
            throw new RuntimeException("Attempt to read past end of file");
        else {
            char result = (char)myNextChar;
            if (myNextChar == '\n')  // lazy input, don't read yet
                myUndefinedState = true;
            else {
                myNextChar = read();
                if (myNextChar == -1)   // unexpected end-of-file 
                    myNextChar = '\n';  // insert virtual \n
            }
            return result;
        }
    }

    /**
       Peek ahead one character.
       @return the character that a subsequent call on readChar() would return
       @throws RuntimeException if end-of-file
    */
    public char peek()
    {
        checkState();
        if (myNextChar == -1)
            throw new RuntimeException("Attempt to peek past end of file");
        return (char)myNextChar;
    }

    /**
       Tell whether this stream is ready to be read.
       @return true if ready, false if not
    */
    public boolean ready()
    {
        checkState();
        return myNextChar != -1;
    }

    /**
       Reads a line of text.
       @return the line of text without the '\n'
       @throws RuntimeException if end-of-file
    */
    public String readLine()
    {
        checkState();
        String result = "";
        for(;;) {
            char next = readChar();
            if (next == '\n')
                break;
            result += next;
        }
        return result;
    }

    /**
       Determine whether or not ends of line are treated as tokens.
       This method affects only the token-reading methods (readWord,
       readInt, readDouble).
       @param flag whether end-of-line characters should be left in stream */
    public void eolIsSignificant(boolean flag)

    {
        mySignificantEol = flag;
    }

    /**
       Skip whitespace.
       @param skipEoln whether or not to skip end-of-line characters
    */
    public void skipWhite(boolean skipEoln)
    {
        checkState();
        while (ready() && Character.isWhitespace(peek()) &&
               (skipEoln || peek() != '\n'))
            readChar();
    }

    /**
       Read the next token delineated by whitespace.
       @return the word read (empty string if end-of-file reached)
    */
    public String readWord()
    {
        String result = "";
        skipWhite(true);
        while (ready() && !Character.isWhitespace(peek()))
            result += readChar();
        skipWhite(false);
        if (ready() && peek() == '\n' && !mySignificantEol)
            readChar();
        return result;
    }

    /**
       Read the next token as an integer.
       @return next integer in input stream
       @throws RuntimeException if no token or next token not an integer
    */
    public int readInt()
    {
        return Integer.parseInt(readWord());
    }

    /**
       Read the next token as a double.
       @return next double in input stream
       @throws RuntimeException if no token or next token not a double
    */
    public double readDouble()
    {
        return Double.parseDouble(readWord());
    }

    private InputStream myInput;       // input stream
    private int myNextChar;            // one-char lookahead
    private boolean myUndefinedState;  // for lazy input; if true,
                                       // then myNextChar is undefined
    private boolean mySignificantEol;  // whether or not newline should be
                                       // kept in stream when reading tokens
}
